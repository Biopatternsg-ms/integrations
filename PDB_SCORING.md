# Criterio de Ponderación y Ordenamiento de Complejos PDB (Servicio de Integraciones)

Este documento describe las reglas y el algoritmo de puntuación (scoring) utilizado en el microservicio de **`integrations`** para clasificar y ordenar los complejos proteicos recuperados desde la API gráfica de PDBe (`https://www.ebi.ac.uk/pdbe/graph-api/uniprot/complex/{uniprotId}`).

El objetivo del algoritmo es clasificar los complejos resultantes priorizando aquellos con mayor relevancia biológica, validación científica (curación) y calidad experimental.

---

## 1. Reglas y Puntajes del Algoritmo

Para cada complejo devuelto por la API, se inicia con un puntaje base de `0.0`. A continuación, se aplican los siguientes criterios acumulativos:

### A. Curación en Complex Portal (`complex_portal_id`)
* **Regla**: Si el complejo está registrado en la base de datos *Complex Portal* (es decir, el campo `complex_portal_id` no es nulo), se considera de altísima confianza biológica.
* **Puntuación**: **`+100` puntos**.

### B. Organismo de Origen (`source_organism`)
* **Regla**: Si el complejo proviene de un organismo de interés específico para el usuario.
* **Configuración**: Se compara con el valor de la propiedad `pdbe.target-organism` configurada en `application.properties` (por defecto `"Homo sapiens"`). La comparación ignora mayúsculas y minúsculas.
* **Puntuación**: **`+30` puntos** si coincide.

### C. Evidencia Estructural (`assemblies`)
Por cada ensamblaje (*assembly*) registrado que de soporte estructural al complejo, se realiza una suma de los siguientes tres sub-criterios:

#### C.1. Ensamblajes Preferidos (`preferred_assembly`)
* **Regla**: Valida si el ensamblaje es la representación oligomérica recomendada por el PDB.
* **Puntuación**:
  - **`+10` puntos** si `preferred_assembly` es `true`.
  - **`+2` puntos** si es `false` (o nulo).

#### C.2. Calidad de Resolución (`resolution`)
* **Regla**: La resolución en Ångstroms (Å) determina el nivel de detalle físico de la estructura (a menor valor, mejor resolución). Se bonifica si la resolución es mejor (menor) a `5.0 Å`.
* **Fórmula**: `Puntos Extra = (5.0 - resolution) * 2` (solo si la resolución es > 0 y < 5.0).
* **Ejemplos**:
  - Resolución de `1.5 Å` (excelente): `(5.0 - 1.5) * 2` = **`+7.0` puntos**.
  - Resolución de `4.0 Å` (baja): `(5.0 - 4.0) * 2` = **`+2.0` puntos**.

#### C.3. Método Experimental (`experimental_method`)
* **Regla**: Prioriza métodos de alta fiabilidad estructural para complejos macromoleculares (Rayos X y Crio-EM).
* **Puntuación**:
  - **`+5` puntos** si el método contiene `"diffraction"` (Difracción de Rayos X) o `"microscopy"` (Criomicroscopía Electrónica / Cryo-EM).
  - **`+2` puntos** si el método contiene `"nmr"` o `"magnetic resonance"` (Resonancia Magnética Nuclear).

---

## 2. Configuración y Personalización

El organismo objetivo se puede cambiar editando la siguiente propiedad en el archivo `application.properties` del servicio `integrations`:

```properties
pdbe.target-organism=Homo sapiens
```

---

## 3. Implementación en el Código

El criterio y el cálculo de la ponderación están programados en las siguientes clases del servicio de integraciones:

- **Interfaz del puerto de salida**: [PdbRepository.java](file:///home/yacson/Proyectos/biopatternsg-ms/services/integrations/src/main/java/com/biopatternsg/domain/ports/out/PdbRepository.java)
- **Implementación del adaptador**: [PdbRepositoryImpl.java](file:///home/yacson/Proyectos/biopatternsg-ms/services/integrations/src/main/java/com/biopatternsg/infrastructure/adapters/PdbRepositoryImpl.java) (Método `calculateScore`).
- **Modelo de dominio**: [Complex.java](file:///home/yacson/Proyectos/biopatternsg-ms/services/integrations/src/main/java/com/biopatternsg/domain/model/Complex.java).

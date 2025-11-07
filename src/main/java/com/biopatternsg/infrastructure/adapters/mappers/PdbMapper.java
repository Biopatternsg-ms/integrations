package com.biopatternsg.infrastructure.adapters.mappers;

import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class PdbMapper {

    public static Object graphqlMapper(String pdbId){
        String graphQLQueryString = """
            query FindEntryById($entryId: String!) {
              entry(entry_id: $entryId) {
                entry {
                  id
                }
                polymer_entities {
                  rcsb_polymer_entity_container_identifiers {
                    entry_id
                    auth_asym_ids
                  }
                  entity_poly {
                    rcsb_entity_polymer_type
                    rcsb_sample_sequence_length
                  }
                  rcsb_polymer_entity {
                    pdbx_description
                    pdbx_fragment
                    pdbx_ec
                    pdbx_mutation
                    details
                    formula_weight
                  }
                  rcsb_entity_source_organism {
                    ncbi_scientific_name
                    ncbi_taxonomy_id
                  }
                  rcsb_polymer_entity_name_com {
                    name
                  }
                  uniprots {
                    rcsb_uniprot_container_identifiers {
                      uniprot_id
                    }
                    rcsb_uniprot_protein {
                      name {
                        value
                      }
                    }
                  }
                }
              }
            }
        """;

        Map<String, Object> variables = Map.of("entryId", pdbId);

        return Map.of(
                "query", graphQLQueryString,
                "variables", variables
        );

    }

}

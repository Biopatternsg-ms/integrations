/*
 * Copyright © 2026 biopatternsg (biopatternsg@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.biopatternsg.infrastructure.delivery.controllers;

import com.biopatternsg.domain.model.Complex;
import com.biopatternsg.domain.ports.in.FindPdbComplexes;
import com.biopatternsg.domain.ports.in.FindPdbIds;
import com.biopatternsg.domain.ports.in.SearchByPdbId;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ApplicationScoped
@Path("/integrations/pdb")
@RequiredArgsConstructor
public class PdbController {

    private final FindPdbIds findPdbIds;
    private final SearchByPdbId searchByPdbId;
    private final FindPdbComplexes findPdbComplexes;


    @GET
    @Path("find-ids")
    public Object findIds(@QueryParam("symbol") @NotBlank(message = "The symbol cannot be empty") String symbol) {
        return findPdbIds.execute(symbol);
    }


    @GET
    @Path("search")
    public Object searchById(@QueryParam("pdbId") @NotBlank(message = "The pdbId cannot be empty") String pdbId) {
        return searchByPdbId.execute(pdbId);
    }

    @GET
    @Path("complexes/{uniprotId}")
    public List<Complex> getComplexes(@PathParam("uniprotId") @NotBlank(message = "The uniprotId cannot be empty") String uniprotId) {
        return findPdbComplexes.execute(uniprotId);
    }

}

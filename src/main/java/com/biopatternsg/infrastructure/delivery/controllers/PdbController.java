package com.biopatternsg.infrastructure.delivery.controllers;

import com.biopatternsg.domain.ports.in.FindPdbIds;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@Path("/integrations/pdb")
@RequiredArgsConstructor
public class PdbController {

    private final FindPdbIds findPdbIds;

    @GET
    @Path("find-ids")
    public Object findIds(@QueryParam("symbol") String symbol){
        return findPdbIds.execute(symbol);
    }

}

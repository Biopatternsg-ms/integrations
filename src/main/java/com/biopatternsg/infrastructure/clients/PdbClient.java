package com.biopatternsg.infrastructure.clients;

import com.biopatternsg.infrastructure.clients.model.PdbIdsRequest;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "pdb-api")
public interface PdbClient {

    @POST
    @Path("rcsbsearch/v2/query")
    @Produces(MediaType.APPLICATION_JSON)
    Object findPdbIds(@RequestBody PdbIdsRequest request);
}

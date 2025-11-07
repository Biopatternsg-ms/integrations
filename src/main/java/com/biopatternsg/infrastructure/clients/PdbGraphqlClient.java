package com.biopatternsg.infrastructure.clients;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "pdb-graphql")
public interface PdbGraphqlClient {

    @POST
    @Path("/graphql")
    @Produces(MediaType.APPLICATION_JSON)
    Object searchByPdbId(@RequestBody Object request);
}

package com.biopatternsg.infrastructure.adapters;

import com.biopatternsg.domain.ports.out.PdbRepository;
import com.biopatternsg.infrastructure.clients.PdbClient;
import com.biopatternsg.infrastructure.clients.PdbGraphqlClient;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import static com.biopatternsg.infrastructure.adapters.mappers.PdbMapper.graphqlMapper;
import static com.biopatternsg.infrastructure.clients.model.PdbIdsRequest.buildPdbIdsRequest;

@Slf4j
@ApplicationScoped
public class PdbRepositoryImpl implements PdbRepository {

    private final PdbClient pdbClient;
    private final PdbGraphqlClient pdbGraphqlClient;

    public PdbRepositoryImpl(@RestClient PdbClient pdbClient,
                             @RestClient PdbGraphqlClient pdbGraphqlClient) {
        this.pdbClient = pdbClient;
        this.pdbGraphqlClient = pdbGraphqlClient;
    }

    @Override
    public Object findPdbIds(String symbol) {
        return pdbClient.findPdbIds(buildPdbIdsRequest(symbol));
    }

    @Override
    public Object searchByPdbId(String pdbId) {
        return pdbGraphqlClient.searchByPdbId(graphqlMapper(pdbId));
    }


}

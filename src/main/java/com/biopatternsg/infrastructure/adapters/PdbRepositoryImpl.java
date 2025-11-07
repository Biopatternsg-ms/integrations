package com.biopatternsg.infrastructure.adapters;

import com.biopatternsg.domain.ports.out.PdbRepository;
import com.biopatternsg.infrastructure.clients.PdbClient;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import static com.biopatternsg.infrastructure.clients.model.PdbIdsRequest.buildPdbIdsRequest;

@Slf4j
@ApplicationScoped
public class PdbRepositoryImpl implements PdbRepository {

    private final PdbClient pdbClient;

    public PdbRepositoryImpl(@RestClient PdbClient pdbClient) {
        this.pdbClient = pdbClient;
    }

    @Override
    public Object findPdbIds(String symbol) {
        return pdbClient.findPdbIds(buildPdbIdsRequest(symbol));
    }
}

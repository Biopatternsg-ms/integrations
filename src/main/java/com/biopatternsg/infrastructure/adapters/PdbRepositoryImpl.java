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

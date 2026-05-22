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
package com.biopatternsg.infrastructure.clients.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PdbIdsRequest {

    @JsonProperty("query")
    private Query query;

    @JsonProperty("return_type")
    private String returnType;

    public static PdbIdsRequest buildPdbIdsRequest(String symbol){

        var parameters = Parameters.builder()
                .value(symbol)
                .build();

        var nodes = Node.builder()
                .type("terminal")
                .service("full_text")
                .parameters(parameters)
                .build();

        var query = Query.builder()
                .type("group")
                .logicalOperator("and")
                .nodes(Collections.singletonList(nodes))
                .build();

         return PdbIdsRequest.builder()
                 .query(query)
                 .returnType("entry")
                 .build();
    }
}


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Query {
    @JsonProperty("type")
    private String type;

    @JsonProperty("logical_operator")
    private String logicalOperator;

    @JsonProperty("nodes")
    private List<Node> nodes;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Node {
    @JsonProperty("type")
    private String type;

    @JsonProperty("service")
    private String service;

    @JsonProperty("parameters")
    private Parameters parameters;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Parameters {
    @JsonProperty("value")
    private String value;
}

/*
 * Copyright 2018 flow.ci
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.flowci.core.flow.domain;

import com.flowci.core.common.domain.Mongoable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author yang
 */
@Getter
@Setter
@Document(collection = "flow_yml")
@NoArgsConstructor
@CompoundIndex(
        name = "index_flow_id_and_yaml_name",
        def = "{'flowId': 1, 'name': 1}",
        unique = true
)
public class Yml extends Mongoable {

    public final static String DEFAULT_NAME = "default";

    @Indexed(name = "index_flow_id")
    private String flowId;

    private String name;

    private String raw;

    public Yml(String flowId, String name, String raw) {
        this.flowId = flowId;
        this.name = name;
        this.raw = raw;
    }
}

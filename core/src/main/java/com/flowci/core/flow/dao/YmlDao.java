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

package com.flowci.core.flow.dao;

import com.flowci.core.flow.domain.Yml;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author yang
 */
@Repository
public interface YmlDao extends YmlCustomDao, MongoRepository<Yml, String> {

    Optional<Yml> findByFlowIdAndName(String flowId, String name);

    void deleteAllByFlowId(String flowId);

    void deleteByFlowIdAndName(String flowId, String name);
}

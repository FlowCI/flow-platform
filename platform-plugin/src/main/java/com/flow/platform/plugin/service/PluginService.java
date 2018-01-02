/*
 * Copyright 2017 flow.ci
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

package com.flow.platform.plugin.service;

import com.flow.platform.plugin.domain.Plugin;
import com.flow.platform.plugin.domain.PluginStatus;
import java.util.Collection;
import java.util.Set;

/**
 * @author yh@firim
 */
public interface PluginService {

    /**
     * find plugin by name
     * @param name
     * @return
     */
    Plugin find(String name);


    /**
     * show plugin list
     */
    Collection<Plugin> list(Set<PluginStatus> status, String keyword, Set<String> labels);

    /**
     * List unique labels
     */
    Collection<String> labels();

    /**
     * install plugin
     */
    Plugin install(String name);

    /**
     * stop plugin
     * @param name
     */
    Plugin stop(String name);

    /**
     * exec install
     * @param plugin
     */
    void execInstallOrUpdate(Plugin plugin);

    /**
     * uninstall plugin
     */
    Plugin uninstall(String pluginName);

    /**
     * Sync plugin list from github
     */
    void syncTask();
}

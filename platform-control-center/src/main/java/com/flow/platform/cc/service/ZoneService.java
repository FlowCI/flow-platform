package com.flow.platform.cc.service;

import java.util.List;

/**
 * Handle on zone level
 *
 * Created by gy@fir.im on 17/05/2017.
 * Copyright fir.im
 */

public interface ZoneService {

    /**
     * Create zk node for agent zone
     *
     * @param zoneName
     * @return zk node path of zone
     */
    String createZone(String zoneName);

    /**
     * Get zone list from zk
     *
     * @return zone list
     */
    List<String> getZones();

    /**
     * Scheduler task, periodically, every 1 min to check available agent in zone
     * It will start instance if num of available agent not enough
     */
    void keepIdleAgent();
}

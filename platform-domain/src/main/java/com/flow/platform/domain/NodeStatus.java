package com.flow.platform.domain;

import java.io.Serializable;

/**
 * Created by gy@fir.im on 03/05/2017.
 *
 * @copyright fir.im
 */
public enum NodeStatus implements Serializable {

    IDLE("IDLE"),

    BUSY("BUSY");

    private String name;

    NodeStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}

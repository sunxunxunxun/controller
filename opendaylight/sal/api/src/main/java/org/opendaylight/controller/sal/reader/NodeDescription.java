
/*
 * Copyright (c) 2013 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.controller.sal.reader;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Represents the switch description information
 *
 *
 *
 */
public class NodeDescription {
    private String manufacturer;
    private String hardware;
    private String software;
    private String serialNumber;
    private String sdnProtocolDescription;

    public NodeDescription() {

    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getHardware() {
        return hardware;
    }

    public void setHardware(String hardware) {
        this.hardware = hardware;
    }

    public String getSoftware() {
        return software;
    }

    public void setSoftware(String software) {
        this.software = software;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSdnProtocolDescription() {
        return sdnProtocolDescription;
    }

    public void setSdnProtocolDescription(String sdnProtocolDescription) {
        this.sdnProtocolDescription = sdnProtocolDescription;
    }

    @Override
    public String toString() {
        return "HwDescription[" + ReflectionToStringBuilder.toString(this)
                + "]";
    }
}

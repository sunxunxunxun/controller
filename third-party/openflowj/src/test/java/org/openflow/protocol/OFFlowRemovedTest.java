
/*
 * Copyright (c) 2013 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.openflow.protocol;

import java.nio.ByteBuffer;

import junit.framework.TestCase;

import org.openflow.protocol.OFFlowRemoved.OFFlowRemovedReason;
import org.openflow.util.OFTestCase;

public class OFFlowRemovedTest extends OFTestCase {
    public void testWriteRead() throws Exception {
        OFFlowRemoved msg = (OFFlowRemoved) messageFactory
                .getMessage(OFType.FLOW_REMOVED);
        msg.setMatch(new OFMatch());
        byte[] hwAddr = new byte[6];
        msg.getMatch().setDataLayerDestination(hwAddr);
        msg.getMatch().setDataLayerSource(hwAddr);
        msg.setReason(OFFlowRemovedReason.OFPRR_DELETE);
        ByteBuffer bb = ByteBuffer.allocate(1024);
        bb.clear();
        msg.writeTo(bb);
        bb.flip();
        msg.readFrom(bb);
        TestCase.assertEquals(OFType.FLOW_REMOVED, msg.getType());
        TestCase.assertEquals(OFFlowRemovedReason.OFPRR_DELETE, msg.getReason());
    }
}

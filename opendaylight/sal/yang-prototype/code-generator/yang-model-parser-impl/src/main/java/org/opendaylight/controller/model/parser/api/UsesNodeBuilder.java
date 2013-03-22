/*
 * Copyright (c) 2013 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.controller.model.parser.api;

import org.opendaylight.controller.yang.model.api.UsesNode;

/**
 * Interface for builders of 'uses' statement.
 */
public interface UsesNodeBuilder {

    void addAugment(AugmentationSchemaBuilder builder);

    UsesNode build();

}

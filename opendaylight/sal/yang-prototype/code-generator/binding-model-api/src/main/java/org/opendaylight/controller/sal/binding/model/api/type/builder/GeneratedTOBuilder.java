/*
 * Copyright (c) 2013 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.controller.sal.binding.model.api.type.builder;

import org.opendaylight.controller.sal.binding.model.api.GeneratedTransferObject;

/**

 */
public interface GeneratedTOBuilder extends GeneratedTypeBuilder {

    public GeneratedPropertyBuilder addProperty(final String name);

    public boolean addEqualsIdentity(final GeneratedPropertyBuilder property);

    public boolean addHashIdentity(final GeneratedPropertyBuilder property);

    public boolean addToStringProperty(final GeneratedPropertyBuilder property);

    @Override
    public GeneratedTransferObject toInstance();
}

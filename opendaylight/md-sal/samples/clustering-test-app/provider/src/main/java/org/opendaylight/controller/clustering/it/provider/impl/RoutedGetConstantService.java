/*
 * Copyright (c) 2017 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.controller.clustering.it.provider.impl;

import com.google.common.util.concurrent.CheckedFuture;
import com.google.common.util.concurrent.Futures;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.opendaylight.controller.md.sal.dom.api.DOMRpcException;
import org.opendaylight.controller.md.sal.dom.api.DOMRpcIdentifier;
import org.opendaylight.controller.md.sal.dom.api.DOMRpcImplementation;
import org.opendaylight.controller.md.sal.dom.api.DOMRpcImplementationRegistration;
import org.opendaylight.controller.md.sal.dom.api.DOMRpcProviderService;
import org.opendaylight.controller.md.sal.dom.api.DOMRpcResult;
import org.opendaylight.controller.md.sal.dom.spi.DefaultDOMRpcResult;
import org.opendaylight.mdsal.binding.dom.codec.api.BindingNormalizedNodeSerializer;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.data.api.YangInstanceIdentifier;
import org.opendaylight.yangtools.yang.data.api.schema.ContainerNode;
import org.opendaylight.yangtools.yang.data.api.schema.LeafNode;
import org.opendaylight.yangtools.yang.data.api.schema.NormalizedNode;
import org.opendaylight.yangtools.yang.data.impl.schema.builder.impl.ImmutableContainerNodeBuilder;
import org.opendaylight.yangtools.yang.data.impl.schema.builder.impl.ImmutableLeafNodeBuilder;
import org.opendaylight.yangtools.yang.model.api.SchemaPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RoutedGetConstantService implements DOMRpcImplementation {

    private static final Logger LOG = LoggerFactory.getLogger(RoutedGetConstantService.class);

    private static final QName OUTPUT =
            QName.create("tag:opendaylight.org,2017:controller:yang:lowlevel:target","2017-02-15", "output");
    private static final QName CONSTANT =
            QName.create("tag:opendaylight.org,2017:controller:yang:lowlevel:target","2017-02-15", "constant");
    private static final QName CONTEXT =
            QName.create("tag:opendaylight.org,2017:controller:yang:lowlevel:target","2017-02-15", "context");
    private static final QName GET_CONTEXTED_CONSTANT =
            QName.create("tag:opendaylight.org,2017:controller:yang:lowlevel:target","2017-02-15",
                    "get-contexted-constant");

    private final String constant;

    private RoutedGetConstantService(final String constant) {
        this.constant = constant;
    }

    public static DOMRpcImplementationRegistration<RoutedGetConstantService> registerNew(
            final BindingNormalizedNodeSerializer codec, final DOMRpcProviderService rpcProviderService,
            final String constant, final InstanceIdentifier<?> context) {

        LOG.debug("Registering get-contexted-constant on context: {}, with value: {}", context, constant);

        final YangInstanceIdentifier yid = codec.toYangInstanceIdentifier(context);
        final DOMRpcIdentifier id = DOMRpcIdentifier.create(SchemaPath.create(true, GET_CONTEXTED_CONSTANT), yid);

        return rpcProviderService.registerRpcImplementation(new RoutedGetConstantService(constant), id);
    }

    @Nonnull
    @Override
    public CheckedFuture<DOMRpcResult, DOMRpcException> invokeRpc(@Nonnull final DOMRpcIdentifier rpc,
                                                                  @Nullable final NormalizedNode<?, ?> input) {
        LOG.debug("get-contexted-constant invoked, current value: {}", constant);

        final LeafNode<Object> value = ImmutableLeafNodeBuilder.create()
                .withNodeIdentifier(new YangInstanceIdentifier.NodeIdentifier(CONSTANT))
                .withValue(constant)
                .build();

        final ContainerNode result = ImmutableContainerNodeBuilder.create()
                .withNodeIdentifier(new YangInstanceIdentifier.NodeIdentifier(OUTPUT))
                .withChild(value)
                .build();

        return Futures.immediateCheckedFuture(new DefaultDOMRpcResult(result));
    }
}

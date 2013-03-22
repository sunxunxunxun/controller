/*
 * Copyright (c) 2013 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.controller.model.parser.builder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.opendaylight.controller.model.parser.api.ChildNodeBuilder;
import org.opendaylight.controller.model.parser.api.DataSchemaNodeBuilder;
import org.opendaylight.controller.model.parser.api.GroupingBuilder;
import org.opendaylight.controller.model.parser.api.SchemaNodeBuilder;
import org.opendaylight.controller.model.parser.api.TypeDefinitionAwareBuilder;
import org.opendaylight.controller.model.parser.api.TypeDefinitionBuilder;
import org.opendaylight.controller.model.parser.api.UsesNodeBuilder;
import org.opendaylight.controller.yang.common.QName;
import org.opendaylight.controller.yang.model.api.ContainerSchemaNode;
import org.opendaylight.controller.yang.model.api.ExtensionDefinition;
import org.opendaylight.controller.yang.model.api.GroupingDefinition;
import org.opendaylight.controller.yang.model.api.RpcDefinition;
import org.opendaylight.controller.yang.model.api.SchemaPath;
import org.opendaylight.controller.yang.model.api.Status;
import org.opendaylight.controller.yang.model.api.TypeDefinition;

public class RpcDefinitionBuilder implements ChildNodeBuilder,
        SchemaNodeBuilder, TypeDefinitionAwareBuilder {

    private final RpcDefinitionImpl instance;
    private final QName qname;
    private ContainerSchemaNodeBuilder inputBuilder;
    private ContainerSchemaNodeBuilder outputBuilder;
    private final Set<TypeDefinitionBuilder> addedTypedefs = new HashSet<TypeDefinitionBuilder>();
    private final Set<GroupingBuilder> addedGroupings = new HashSet<GroupingBuilder>();

    RpcDefinitionBuilder(QName qname) {
        this.qname = qname;
        this.instance = new RpcDefinitionImpl(qname);
    }

    @Override
    public RpcDefinition build() {
        final ContainerSchemaNode input = inputBuilder.build();
        final ContainerSchemaNode output = outputBuilder.build();
        instance.setInput(input);
        instance.setOutput(output);

        // TYPEDEFS
        Set<TypeDefinition<?>> typedefs = new HashSet<TypeDefinition<?>>();
        for (TypeDefinitionBuilder entry : addedTypedefs) {
            typedefs.add(entry.build());
        }
        instance.setTypeDefinitions(typedefs);

        // GROUPINGS
        final Set<GroupingDefinition> groupings = new HashSet<GroupingDefinition>();
        for (GroupingBuilder entry : addedGroupings) {
            groupings.add(entry.build());
        }
        instance.setGroupings(groupings);

        return instance;
    }

    void setInput(ContainerSchemaNodeBuilder inputBuilder) {
        this.inputBuilder = inputBuilder;
    }

    void setOutput(ContainerSchemaNodeBuilder outputBuilder) {
        this.outputBuilder = outputBuilder;
    }

    @Override
    public void addTypedef(TypeDefinitionBuilder type) {
        addedTypedefs.add(type);
    }

    @Override
    public void setPath(SchemaPath schemaPath) {
        instance.setPath(schemaPath);
    }

    @Override
    public void setDescription(String description) {
        instance.setDescription(description);
    }

    @Override
    public void setReference(String reference) {
        instance.setReference(reference);
    }

    @Override
    public void setStatus(Status status) {
        instance.setStatus(status);
    }

    @Override
    public QName getQName() {
        return null;
    }

    @Override
    public void addChildNode(DataSchemaNodeBuilder childNode) {
        throw new UnsupportedOperationException(
                "Can not add child node to rpc definition: rpc can not contains child nodes.");
    }

    @Override
    public void addGrouping(GroupingBuilder grouping) {
        addedGroupings.add(grouping);
    }

    @Override
    public void addUsesNode(UsesNodeBuilder usesBuilder) {
        throw new UnsupportedOperationException(
                "Can not add uses node to rpc definition: rpc can not contains uses nodes.");
    }

    @Override
    public int hashCode() {
        return qname.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof RpcDefinitionBuilder)) {
            return false;
        }
        RpcDefinitionBuilder other = (RpcDefinitionBuilder) obj;
        if (other.qname == null) {
            if (this.qname != null) {
                return false;
            }
        } else if (!other.qname.equals(this.qname)) {
            return false;
        }
        return true;
    }

    private static class RpcDefinitionImpl implements RpcDefinition {

        private final QName qname;
        private SchemaPath path;
        private String description;
        private String reference;
        private Status status;

        private ContainerSchemaNode input;
        private ContainerSchemaNode output;

        private Set<TypeDefinition<?>> typeDefinitions;
        private Set<GroupingDefinition> groupings;

        private RpcDefinitionImpl(QName qname) {
            this.qname = qname;
        }

        @Override
        public QName getQName() {
            return qname;
        }

        @Override
        public SchemaPath getPath() {
            return path;
        }

        private void setPath(SchemaPath path) {
            this.path = path;
        }

        @Override
        public String getDescription() {
            return description;
        }

        private void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String getReference() {
            return reference;
        }

        private void setReference(String reference) {
            this.reference = reference;
        }

        @Override
        public Status getStatus() {
            return status;
        }

        private void setStatus(Status status) {
            this.status = status;
        }

        @Override
        public ContainerSchemaNode getInput() {
            return input;
        }

        private void setInput(ContainerSchemaNode input) {
            this.input = input;
        }

        @Override
        public ContainerSchemaNode getOutput() {
            return output;
        }

        private void setOutput(ContainerSchemaNode output) {
            this.output = output;
        }

        @Override
        public Set<TypeDefinition<?>> getTypeDefinitions() {
            return typeDefinitions;
        }

        private void setTypeDefinitions(Set<TypeDefinition<?>> typeDefinitions) {
            this.typeDefinitions = typeDefinitions;
        }

        @Override
        public Set<GroupingDefinition> getGroupings() {
            return groupings;
        }

        private void setGroupings(Set<GroupingDefinition> groupings) {
            this.groupings = groupings;
        }

        @Override
        public List<ExtensionDefinition> getExtensionSchemaNodes() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(
                    RpcDefinitionImpl.class.getSimpleName() + "[");
            sb.append("qname=" + qname);
            sb.append(", path=" + path);
            sb.append(", description=" + description);
            sb.append(", reference=" + reference);
            sb.append(", status=" + status);
            sb.append(", input=" + input);
            sb.append(", output=" + output + "]");
            return sb.toString();
        }
    }

}

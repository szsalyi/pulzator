package com.github.szsalyi.pulzator.common.mapping;

import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;

import java.util.IdentityHashMap;
import java.util.Map;

public class CycleAvoidingContextMapping {
    private final Map<Object, Object> knownInstances = new IdentityHashMap<Object, Object>();

    @BeforeMapping
    public <T> T getMappedInstance(final Object source, @TargetType final Class<T> targetType) {
        return (T) knownInstances.get(source);
    }

    @BeforeMapping
    public void storeMappedInstance(final Object source, @MappingTarget final Object target) {
        knownInstances.put(source, target);
    }
}

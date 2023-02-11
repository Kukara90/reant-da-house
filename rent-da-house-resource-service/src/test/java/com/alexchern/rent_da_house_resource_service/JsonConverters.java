package com.alexchern.rent_da_house_resource_service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.test.web.servlet.ResultActions;

public interface JsonConverters {

    @SneakyThrows
    default String toJson(Object object) {
        return getObjectMapper().writeValueAsString(object);
    }

    @SneakyThrows
    default <T> T fromJson(ResultActions resultActions, Class<T> clazz) {
        return getObjectMapper().readValue(
                resultActions.andReturn().getResponse().getContentAsString(), clazz
        );
    }

    @SneakyThrows
    default <T> T fromJson(ResultActions resultActions, TypeReference<T> typeReference) {
        return getObjectMapper().readValue(
                resultActions.andReturn().getResponse().getContentAsString(), typeReference
        );
    }

    ObjectMapper getObjectMapper();
}

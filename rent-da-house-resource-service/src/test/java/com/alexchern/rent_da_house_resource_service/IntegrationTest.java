package com.alexchern.rent_da_house_resource_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("integration")
@Testcontainers
public class IntegrationTest implements JsonConverters {

    static final DockerComposeContainer<?> TEST_CONTAINER;

    static {
        TEST_CONTAINER = new DockerComposeContainer<>(
                new File("src/test/resources/docker-compose.yml")
        ).withLocalCompose(true);

        TEST_CONTAINER.start();
    }

    @Autowired
    protected MockMvc mockMvc;

    @Getter
    @Autowired
    private ObjectMapper objectMapper;
}

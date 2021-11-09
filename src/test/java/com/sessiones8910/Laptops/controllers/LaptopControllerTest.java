package com.sessiones8910.Laptops.controllers;

import com.sessiones8910.Laptops.entities.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private final String ROOT = "/api/v1/laptops/";
    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp(){
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }


    @Test
    void findAll() {
        ResponseEntity<Laptop[]> response =
                testRestTemplate.getForEntity(ROOT, Laptop[].class);

        assertEquals(200, response.getStatusCodeValue());

        List<Laptop> laptops = Arrays.asList(response.getBody());
    }

    @Test
    void findOneById() {
        ResponseEntity<Laptop> response =
                testRestTemplate.getForEntity(ROOT + "1", Laptop.class);

        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void create() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "model": "Ultra-X",
                    "fabricante": "Positivo",
                    "hd": 514,
                    "ram": 4,
                    "screen": 17
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json, headers);

        ResponseEntity<Laptop> response = testRestTemplate.exchange(ROOT, HttpMethod.POST, request, Laptop.class);
        Laptop result = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Ultra-X", result.getModel());
    }

    @Test
    void updateBook() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "id": 1,
                    "model": "Ultra-X",
                    "fabricante": "Positivo",
                    "hd": 514,
                    "ram": 4,
                    "screen": 17
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json, headers);

        ResponseEntity<Laptop> response = testRestTemplate.exchange(ROOT, HttpMethod.PUT, request, Laptop.class);
        Laptop result = response.getBody();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void delete() {
        ResponseEntity response =
                testRestTemplate.exchange(ROOT + "5", HttpMethod.DELETE, new HttpEntity<>(""), Laptop.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteAll() {
        ResponseEntity response =
                testRestTemplate.exchange(ROOT + "restartDB/", HttpMethod.DELETE, new HttpEntity<>(""), Laptop.class);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
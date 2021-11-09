package com.sessiones8910.Laptops.controllers;

import com.sessiones8910.Laptops.entities.Laptop;
import com.sessiones8910.Laptops.services.LaptopService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
public class LaptopController {

    private final String ROOT = "/api/v1/laptops/";
    private final Logger log = LoggerFactory.getLogger(LaptopController.class);
    private LaptopService laptopService;

    public LaptopController(LaptopService laptopService){
        this.laptopService = laptopService;
    }

    @Value("${app.message}")
    String message;

    @GetMapping("/")
    public void getMessage(){
        System.out.println(message);
    }


    @GetMapping(ROOT)
    @ApiOperation("Busca todos los laptops de la DB")
    public List<Laptop> findAll(){
        return laptopService.findAll();
    }

    @GetMapping(ROOT + "{id}")
    @ApiOperation("Busca un laptop por clave primaria ID")
    public ResponseEntity<Laptop> findOneById(@PathVariable Long id){
        return laptopService.findOneById(id);
    }

    @PostMapping(ROOT)
    @ApiOperation("Crea un objeto laptop a partir de un JSON")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop){
        ResponseEntity<Laptop> result = laptopService.create(laptop);

        if (result.getStatusCode().equals(HttpStatus.BAD_REQUEST))
            log.warn("Trying to create a laptop with ID");

        return result;
    }

    @PutMapping(ROOT)
    @ApiOperation("Actualiza un objeto laptop a partir de un JSON")
    public ResponseEntity<Laptop> updateBook(@RequestBody Laptop laptop){
        ResponseEntity<Laptop> result = laptopService.update(laptop);

        if (result.getStatusCode().equals(HttpStatus.BAD_REQUEST))
            log.warn("Trying to update a laptop without ID");

        if (result.getStatusCode().equals(HttpStatus.NOT_FOUND))
            log.warn("Trying to update a laptop with a non existing ID");

        return result;
    }

    @DeleteMapping(ROOT + "{id}")
    @ApiOperation("Borra un laptop a partir del ID pasado como parametro en la URL")
    public ResponseEntity delete(@PathVariable Long id){
        ResponseEntity result = laptopService.delete(id);

        if (result.getStatusCode().equals(HttpStatus.NOT_FOUND))
            log.warn("Trying to delete a laptop with a non existing ID");

        return result;
    }

    @ApiIgnore
    @DeleteMapping(ROOT + "restartDB/")
    public ResponseEntity deleteAll(@RequestHeader HttpHeaders headers){
        ResponseEntity result = laptopService.deleteAll();

        if (result.getStatusCode().equals(HttpStatus.NOT_FOUND))
            log.warn("The DB is already empty");
        else
            log.warn("Deleting all by request of " + headers.get("User-Agent"));

        return result;
    }
}

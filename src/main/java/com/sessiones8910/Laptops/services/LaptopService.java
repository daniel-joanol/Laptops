package com.sessiones8910.Laptops.services;

import com.sessiones8910.Laptops.entities.Laptop;
import com.sessiones8910.Laptops.repositories.LaptopRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaptopService {

    private LaptopRepository laptopRepository;

    public LaptopService(LaptopRepository laptopRepository) {
        this.laptopRepository =  laptopRepository;
    }

    public List<Laptop> findAll(){
        return laptopRepository.findAll();
    }

    public ResponseEntity<Laptop> findOneById(Long id){
        Optional<Laptop> laptopOpt = laptopRepository.findById(id);

        if (laptopOpt.isPresent())
            return ResponseEntity.ok(laptopOpt.get());
        else
            return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Laptop> create(Laptop laptop){

        if (laptop.getId() != null)
            return ResponseEntity.badRequest().build();

        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<Laptop> update(Laptop laptop){

        if (laptop.getId() == null)
            return ResponseEntity.badRequest().build();

        if (!laptopRepository.existsById(laptop.getId()))
            return ResponseEntity.notFound().build();

        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity delete(Long id){

        if (!laptopRepository.existsById(id))
            return ResponseEntity.notFound().build();

        laptopRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity deleteAll(){

        if (laptopRepository.count() == 0)
            return ResponseEntity.notFound().build();

        laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}

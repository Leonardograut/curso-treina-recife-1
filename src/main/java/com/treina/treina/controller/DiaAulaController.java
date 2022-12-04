package com.treina.treina.controller;

import com.treina.treina.model.DiaAula;
import com.treina.treina.repository.DiaaulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/diaaulas")
public class DiaAulaController {


    @Autowired
    private DiaaulaRepository diaaulaRepository;


    @PostMapping("/")
    public ResponseEntity<DiaAula> save(@RequestBody DiaAula diaAula) {
        return ResponseEntity.status(HttpStatus.CREATED).body(diaaulaRepository.save(diaAula));
    }

    @GetMapping("/")
    public ResponseEntity<List<DiaAula>> listAll() {
        return ResponseEntity.status(HttpStatus.OK).body(diaaulaRepository.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<DiaAula> diaAulaOptional = diaaulaRepository.findById(id);
        if (diaAulaOptional.isPresent()) {
            diaaulaRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID is not exist");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<DiaAula>> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(diaaulaRepository.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiaAula> update(@PathVariable Long id, @RequestBody DiaAula diaAula) {
        Optional<DiaAula> diaAulaOptional = diaaulaRepository.findById(id);
        if (diaAulaOptional.isPresent()) {
            DiaAula d = new DiaAula();
            d.setId(id);
            d.setDataAula(diaAula.getDataAula());
            d.setSala(diaAula.getSala());
            d.setTurma(diaAula.getTurma());
            d.setLocalDateTime(LocalDateTime.now());
            d.setTurma(diaAula.getTurma());
            return ResponseEntity.status(HttpStatus.OK).body(diaaulaRepository.save(d));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
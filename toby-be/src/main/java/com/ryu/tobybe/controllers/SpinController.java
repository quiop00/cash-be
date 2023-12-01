package com.ryu.tobybe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ryu.tobybe.models.SpinDto;
import com.ryu.tobybe.services.SpinService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

// TODO add role admin for create/update
@RestController
@RequestMapping("/api/spin")
public class SpinController {

    @Autowired
    private SpinService spinService;

    @GetMapping(value = "")
    public ResponseEntity<?> getAllSpin() {
        List<SpinDto> spins = spinService.getAllSpin();
        return new ResponseEntity<List<SpinDto>>(spins, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getSpin(@PathVariable long id) {
        try {
            SpinDto spin = spinService.getSpin(id);

            return new ResponseEntity<>(spin, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> create(@RequestBody SpinDto spinDto) {
        SpinDto spin = spinService.createSpin(spinDto);
        if (spin != null) {
            return new ResponseEntity<>(spin, HttpStatus.OK);
        }

        return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody SpinDto spinDto) {
        try {
            SpinDto spin = spinService.updateSpin(id, spinDto);

            return new ResponseEntity<>(spin, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/roll")
    public ResponseEntity<?> spin() {
        try {
            int spin = spinService.spin(0);
            return new ResponseEntity<>(spin, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("SPIN ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

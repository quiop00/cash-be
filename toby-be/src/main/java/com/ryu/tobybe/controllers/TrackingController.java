package com.ryu.tobybe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryu.tobybe.models.TrackResDto;
import com.ryu.tobybe.services.TrackService;

@RestController
@RequestMapping("/api/tracking")
public class TrackingController {

    @Autowired
    private TrackService trackService;
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getTracks(@PathVariable long id) {
        try {
            TrackResDto data = trackService.getTrackData(id);
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

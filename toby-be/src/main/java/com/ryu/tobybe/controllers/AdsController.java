package com.ryu.tobybe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ryu.common.entity.AdsEntity;
import com.ryu.tobybe.services.AdsService;

@RestController("/ads")
public class AdsController {

    @Autowired
    private AdsService adsService;
    
    @GetMapping(value = "")
    public ResponseEntity<?> getAdsSetting() {
        AdsEntity data = adsService.getAdsConfig();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PutMapping(value = "")
    public ResponseEntity<?> updateAdsSetting(@RequestBody AdsEntity dto) {
        AdsEntity data = adsService.updateAdsConfig(dto);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}

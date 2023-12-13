package com.ryu.tobybe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryu.common.entity.AdsEntity;
import com.ryu.common.models.BaseResponse;
import com.ryu.tobybe.services.AdsService;

@RestController
@RequestMapping("/api/ads")
public class AdsController {

    @Autowired
    private AdsService adsService;

    @GetMapping("")
    public ResponseEntity<?> getAdsSetting() {
        AdsEntity data = adsService.getAdsConfig();
        BaseResponse<AdsEntity> res = new BaseResponse<>(data, 200, "");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<?> updateAdsSetting(@RequestBody AdsEntity dto) {
        AdsEntity data = adsService.updateAdsConfig(dto);
        BaseResponse<AdsEntity> res = new BaseResponse<>(data, 200, "");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}

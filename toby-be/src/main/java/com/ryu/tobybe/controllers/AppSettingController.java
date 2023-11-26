package com.ryu.tobybe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ryu.common.entity.AppSecurityEntity;
import com.ryu.tobybe.models.OfferwallDto;
import com.ryu.tobybe.models.RewardDto;
import com.ryu.tobybe.services.SettingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController("/settings")
public class AppSettingController {
    
    @Autowired
    private SettingService settingService;

    @GetMapping(value="/reward")
    public ResponseEntity<?> getRewardSetting() {
        RewardDto data = settingService.getRewardConfig();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PutMapping(value="/reward")
    public ResponseEntity<?> updateReward(@RequestBody RewardDto dto) {
        RewardDto data = settingService.updateRewardConfig(dto);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping(value="/offerwall")
    public ResponseEntity<?> getOfferwallSetting() {
        OfferwallDto data = settingService.getOfferwallConfig();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PutMapping(value="/offerwall")
    public ResponseEntity<?> updateOfferwallSetting(@RequestBody OfferwallDto dto) {
        boolean data = settingService.updateOfferwallConfig(dto);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping(value="/secure")
    public ResponseEntity<?> getSecureSetting() {
        AppSecurityEntity data = settingService.getSecureConfig();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PutMapping(value="/secure")
    public ResponseEntity<?> updateSecureSetting(@RequestBody AppSecurityEntity dto) {
        boolean data = settingService.updateSecureConfig(dto);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    
}

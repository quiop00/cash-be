package com.ryu.tobybe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ryu.common.entity.AppSecurityEntity;
import com.ryu.common.models.BaseResponse;
import com.ryu.tobybe.models.OfferwallDto;
import com.ryu.tobybe.models.RewardDto;
import com.ryu.tobybe.services.SettingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/settings")
public class AppSettingController {

    @Autowired
    private SettingService settingService;

    @GetMapping(value = "/reward")
    public ResponseEntity<?> getRewardSetting() {
        RewardDto data = settingService.getRewardConfig();
        BaseResponse<RewardDto> res = new BaseResponse<RewardDto>(data, 200, "");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping(value = "/reward")
    public ResponseEntity<?> updateReward(@RequestBody RewardDto dto) {
        RewardDto data = settingService.updateRewardConfig(dto);
        BaseResponse<RewardDto> res = new BaseResponse<RewardDto>(data, 200, "");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping(value = "/offerwall")
    public ResponseEntity<?> getOfferwallSetting() {
        OfferwallDto data = settingService.getOfferwallConfig();
        BaseResponse<OfferwallDto> res = new BaseResponse<OfferwallDto>(data, 200, "");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping(value = "/offerwall")
    public ResponseEntity<?> updateOfferwallSetting(@RequestBody OfferwallDto dto) {
        boolean data = settingService.updateOfferwallConfig(dto);
        BaseResponse<Boolean> res = new BaseResponse<Boolean>(data, 200, "");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping(value = "/secure")
    public ResponseEntity<?> getSecureSetting() {
        AppSecurityEntity data = settingService.getSecureConfig();
        BaseResponse<AppSecurityEntity> res = new BaseResponse<AppSecurityEntity>(data, 200, "");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping(value = "/secure")
    public ResponseEntity<?> updateSecureSetting(@RequestBody AppSecurityEntity dto) {
        boolean data = settingService.updateSecureConfig(dto);
        BaseResponse<Boolean> res = new BaseResponse<Boolean>(data, 200, "");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}

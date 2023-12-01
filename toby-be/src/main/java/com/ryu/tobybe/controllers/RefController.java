package com.ryu.tobybe.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.ryu.common.sercurity.AuthUserDetail;
import com.ryu.tobybe.models.ReferDto;
import com.ryu.tobybe.models.ReferQuestDto;
import com.ryu.tobybe.services.ReferService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/refers")
public class RefController {

    @Autowired
    private ReferService referService;

    @GetMapping("/list")
    public ResponseEntity<?> getReferQuests() {
        List<ReferQuestDto> list = referService.getReferQuests();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReferQuest(@PathVariable long id) {
        try {
            ReferQuestDto data = referService.getReferQuest(id);
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> createQuest(@RequestBody ReferQuestDto questDto) {
        ReferQuestDto data = referService.createReferQuest(questDto);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<?> updateQuest(@RequestBody ReferQuestDto questDto) {
        ReferQuestDto data = referService.updateReferQuest(questDto);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuest(@PathVariable long id) {
        boolean data = referService.delete(id);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    
    @GetMapping(value="")
    public ResponseEntity<?> getReferData(@AuthenticationPrincipal AuthUserDetail user) {
        try {
            ReferDto referDto = referService.getReferData(user.getId());
            return new ResponseEntity<>(referDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // TODO Fix parameters
    @PutMapping(value = "/invite-success/{id}")
    public ResponseEntity<?> inviteSuccess(@PathVariable long id,@AuthenticationPrincipal AuthUserDetail user) {
        try {
            boolean result = referService.referUser(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

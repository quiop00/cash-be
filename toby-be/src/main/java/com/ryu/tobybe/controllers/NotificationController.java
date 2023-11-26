package com.ryu.tobybe.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryu.common.entity.SystemNotification;
import com.ryu.common.sercurity.AuthUserDetail;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController("/notification")
public class NotificationController {
    
    @GetMapping("/list")
    public ResponseEntity<?> getNotifications() {

        return new ResponseEntity<>(null);
    }

    @GetMapping("/")
    public ResponseEntity<?> getNotifications(@AuthenticationPrincipal AuthUserDetail user) {

        return new ResponseEntity<>(null);
    }

    @PostMapping(value="")
    public ResponseEntity<?> createNewNotifiation(@RequestBody SystemNotification entity) {
        //TODO: process POST request
        
        return new ResponseEntity<>(null);
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody SystemNotification entity) {
        return new ResponseEntity<>(null);
    }

    @DeleteMapping("")
    public ResponseEntity<?> delete(@RequestBody SystemNotification entity) {
        return new ResponseEntity<>(null);
    }
    
}

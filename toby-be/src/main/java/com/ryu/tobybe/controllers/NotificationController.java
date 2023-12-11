package com.ryu.tobybe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ryu.common.entity.SystemNotification;
import com.ryu.common.models.BaseResponse;
import com.ryu.common.sercurity.AuthUserDetail;
import com.ryu.tobybe.services.NotificationService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/list")
    public ResponseEntity<?> getNotifications() {
        List<SystemNotification> notifications = notificationService.getSysNotifications();
        BaseResponse<List<SystemNotification>> res = new BaseResponse<>(notifications, 200, "");

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> getNotifications(@AuthenticationPrincipal AuthUserDetail user) {

        return new ResponseEntity<>(null);
    }

    @PostMapping("")
    public ResponseEntity<?> createNewNotifiation(@RequestBody SystemNotification entity) {
        SystemNotification notification = notificationService.createSysNotification(entity);
        BaseResponse<SystemNotification> res = new BaseResponse<>(notification, 200, "");

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{noticeId}")
    public ResponseEntity<?> updateNotification(@PathVariable long noticeId, @RequestBody SystemNotification entity) {
        SystemNotification notification = notificationService.updateSystemNotification(entity);
        BaseResponse<SystemNotification> res = new BaseResponse<>(notification, 200, "");

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/{noticeId}")
    public ResponseEntity<?> deleteNotification(@PathVariable long noticeId) {
        boolean result;
        BaseResponse<Boolean> res;
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        try {
            result = notificationService.deleteSystemNotification(noticeId);
            httpStatus = HttpStatus.OK;
            res = new BaseResponse<>(result, 200, "");
        } catch (Exception e) {
            res = new BaseResponse<>(null, 500, e.getMessage());
        }

        return new ResponseEntity<>(res, httpStatus);
    }

}

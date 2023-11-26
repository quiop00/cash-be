package com.ryu.tobybe.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryu.common.entity.SystemNotification;
import com.ryu.common.entity.UserNotification;
import com.ryu.tobybe.repositories.SystemNotificationRepository;
import com.ryu.tobybe.services.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private SystemNotificationRepository systemNotificationRepository;

    @Override
    public List<SystemNotification> getSysNotifications() {
        return systemNotificationRepository.findAll();
    }

    @Override
    public List<UserNotification> getUserNotifications(long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserNotifications'");
    }

    @Override
    public SystemNotification createSysNotification(SystemNotification notification) {
        notification = systemNotificationRepository.save(notification);
        return notification;
    }

    @Override
    public SystemNotification updateSystemNotification(SystemNotification notification) {
        systemNotificationRepository.save(notification);
        
        return notification;
    }

    @Override
    public SystemNotification getNotification(long id) {
        return systemNotificationRepository.findById(id).get();
    }

    @Override
    public boolean deleteSystemNotification(long id) throws Exception{
        Optional<SystemNotification> notification = systemNotificationRepository.findById(id);
        if(notification.isPresent()) {
            systemNotificationRepository.deleteById(id);
            return true;
        }
        
        throw new Exception("NOTIFICATION NOT FOUND");
    }
    
    // TODO handle send system message on time
    private void prepareBatchJob() {

    }
}

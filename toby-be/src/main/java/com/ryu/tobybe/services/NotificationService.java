package com.ryu.tobybe.services;

import java.util.List;

import com.ryu.common.entity.SystemNotification;
import com.ryu.common.entity.UserNotification;

public interface NotificationService {
    
    public List<SystemNotification> getSysNotifications();

    public List<UserNotification> getUserNotifications(long userId);

    public SystemNotification createSysNotification(SystemNotification notification);
    
    public SystemNotification updateSystemNotification(SystemNotification notification);

    public SystemNotification getNotification(long id);

    public boolean deleteSystemNotification(long id) throws Exception;

}

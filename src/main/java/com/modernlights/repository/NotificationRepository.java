package com.modernlights.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.modernlights.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {



}

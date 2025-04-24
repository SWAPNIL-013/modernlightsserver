package com.modernlights.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.modernlights.model.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {
	PasswordResetToken findByToken(String token);
}

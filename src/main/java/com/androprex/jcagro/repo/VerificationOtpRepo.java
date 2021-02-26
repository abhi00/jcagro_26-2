package com.androprex.jcagro.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.androprex.jcagro.model.VerificationOTP;

@Repository
public interface VerificationOtpRepo extends JpaRepository<VerificationOTP, Long> {
	

}

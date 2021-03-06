package com.hcl.inghackathon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.inghackathon.repository.CommissionRepository;
import com.hcl.inghackathon.service.CommissionService;

@Service
public class CommissionServiceImpl implements CommissionService {

	@Autowired
	CommissionRepository commissionRepo;
	
	@Override
	public Double getCalculatedCommission(Long partyId, Long activityId, Long productId) {
		return commissionRepo.getCommission(productId, partyId, activityId);
	}

}
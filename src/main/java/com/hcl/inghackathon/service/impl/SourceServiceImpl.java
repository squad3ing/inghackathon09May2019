package com.hcl.inghackathon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.inghackathon.entities.Source;
import com.hcl.inghackathon.repository.SourceRepository;
import com.hcl.inghackathon.service.SourceService;

@Service
public class SourceServiceImpl implements SourceService {

	@Autowired
	SourceRepository sourceRepo;
	
	@Override
	public List<Source> getAllPendingTransactions(Long partyId, Integer actualStatus) {
		return sourceRepo.getListOfServiceTransactions(partyId, actualStatus);
	}
	
	public Integer getActivityCount(Long productId, String party, String activity, Integer actualStatus) {
		return sourceRepo.getActivityCount(productId, party, activity, actualStatus);
	}

}
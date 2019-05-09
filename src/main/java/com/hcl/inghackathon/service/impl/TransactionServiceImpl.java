package com.hcl.inghackathon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.hcl.inghackathon.entities.Transaction;
import com.hcl.inghackathon.repository.TransactionRepository;
import com.hcl.inghackathon.service.TransactionService;

public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionRepository transactionRepository;
	
	@Override
	public Transaction getTransaction(Long partyId, String approvalStatus) {
		return transactionRepository.findByPartyIdAndApprovalStatus(partyId,approvalStatus);
	}

}
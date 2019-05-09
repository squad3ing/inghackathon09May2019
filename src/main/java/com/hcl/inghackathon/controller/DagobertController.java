package com.hcl.inghackathon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.inghackathon.entities.Source;
import com.hcl.inghackathon.entities.Transaction;
import com.hcl.inghackathon.service.CommissionService;
import com.hcl.inghackathon.service.PaymentApprovalServcice;
import com.hcl.inghackathon.service.PaymentService;
import com.hcl.inghackathon.service.SourceService;

@RestController
@RequestMapping("/dagobert")
public class DagobertController {

	@Autowired
	SourceService sourceService;

	@Autowired
	CommissionService commissionService;

	@Autowired
	PaymentService paymentService;
	
	@Autowired
	PaymentApprovalServcice paymentservice;
	
	/*
	 * @GetMapping("/viewAllServiceTransactions") public List<Source>
	 * getAllServiceTransactions(@RequestParam("partyId") Long partyId,
	 * 
	 * @RequestParam("actualStatus") Integer actualStatus) { List<Source>
	 * allPendingTransactions = sourceService.getAllPendingTransactions(partyId,
	 * actualStatus); return allPendingTransactions; }
	 */
  
  @GetMapping("/getActivityCount")
	public ResponseEntity<List<?>> getActivityCount(@RequestParam("partyId") Long partyId,
			@RequestParam("transactionStatus") String transactionStatus) {
		List<?> allPendingTransactions = sourceService.getAllActivityCounts(partyId, transactionStatus);
		return new ResponseEntity<List<?>>(allPendingTransactions, HttpStatus.OK);
	}

	@GetMapping("/getServicesProvided")
	public ResponseEntity<List<?>> getServicesProvided(@RequestParam("partyId") Long partyId,
			@RequestParam("transactionStatus") String transactionStatus) {
		List<?> allPendingTransactions = sourceService.getAllActivityCounts(partyId, transactionStatus);
		return new ResponseEntity<List<?>>(allPendingTransactions, HttpStatus.OK);
	}

	@GetMapping("/getSuccessfulProviders")
	public List<Source> getSuccessfulProviders() {
		return sourceService.getSuccessfulTransactions();
	}

	@GetMapping("/getCommission")
	public ResponseEntity<Double> retrieveCommission(@RequestParam("partyId") Long partyId,
			@RequestParam("activityCode") Long activityCode, @RequestParam("productCode") Long productId) {
		
		Double calculatedCommission = 2.0+3.8;
		Double commissionAmount = commissionService.getCommission(partyId, activityCode, productId);
		System.out.println("commissionAmount: " + commissionAmount);
		Integer activityCounts = sourceService.getActivityCount(productId, partyId, activityCode, "V");
		System.out.println("activityCounts: " + activityCounts);

		if (activityCounts != null && commissionAmount != null) {
			calculatedCommission = activityCounts * commissionAmount;
			commissionService.updateProcessingStatus(partyId, activityCode, productId);
		}

		return new ResponseEntity<Double>(calculatedCommission, HttpStatus.OK);
  }

	@GetMapping("/calculateCommission")
	public Double calculateCommission(@RequestParam("partyId") Long partyId,
			@RequestParam("activityCode") Long activityCode, @RequestParam("productCode") Long productCode) {
		return commissionService.getCommission(partyId, activityCode, productCode);
	}
	
	@GetMapping("/getPendingPayments")
	public List<Transaction> getPaymentToApprove(@RequestParam("partyId") Long partyId) {
		return paymentservice.getPendingPayments(partyId);
	}
	
	@PostMapping("/approvePayment")
	public boolean approvePayment(@RequestBody Transaction transaction) {
		return paymentservice.approvePayments(transaction);
	}

	@PostMapping("/makePayment")
	public ResponseEntity<String> makePayment(@RequestParam Long partyId, @RequestParam String approvalStatus,
			@RequestParam String bankAcccount) {
		String msg = paymentService.doPayment(partyId, approvalStatus, bankAcccount);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

}
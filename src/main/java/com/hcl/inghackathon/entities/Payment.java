package com.hcl.inghackathon.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payment")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
	private Long paymentId;

	@Column(name = "party_id")
	private Long partyId;

	@Column(name = "payment_status")
	private String paymentStatus;

	@Column(name = "bank_account")
	private String bankAccount;

	@Column(name = "amount_paid")
	private Integer amountPaid;

	public Payment() {
	}

	public Payment(Long paymentId, Long partyId, String paymentStatus, String bankAccount, Integer amountPaid) {
		super();
		this.paymentId = paymentId;
		this.partyId = partyId;
		this.paymentStatus = paymentStatus;
		this.bankAccount = bankAccount;
		this.amountPaid = amountPaid;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public Integer getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(Integer amountPaid) {
		this.amountPaid = amountPaid;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", partyId=" + partyId + ", paymentStatus=" + paymentStatus
				+ ", bankAccount=" + bankAccount + ", amountPaid=" + amountPaid + "]";
	}

}
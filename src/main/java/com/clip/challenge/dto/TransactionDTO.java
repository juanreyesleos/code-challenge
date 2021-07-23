package com.clip.challenge.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TransactionDTO {

	private int id;

	@NotNull(message = "amount parameter is mandatory")
	private Double amount;

	@NotBlank(message = "clipUser parameter is mandatory")
	private String clipUser;

	@NotBlank(message = "carddata parameter is mandatory")
	private String carddata;

	private Timestamp date;

	private boolean paid;

	private DisbursementDTO disbursement;

	public boolean isPaid() {
		return paid;
	}

	public DisbursementDTO getDisbursement() {
		return disbursement;
	}

	public void setDisbursement(DisbursementDTO disbursement) {
		this.disbursement = disbursement;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getClipUser() {
		return clipUser;
	}

	public void setClipUser(String clipUser) {
		this.clipUser = clipUser;
	}

	public String getCarddata() {
		return carddata;
	}

	public void setCarddata(String carddata) {
		this.carddata = carddata;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", amount=" + amount + ", clipUser=" + clipUser + ", carddata=" + carddata
				+ ", date=" + date + ", paid=" + paid + "]";
	}

}

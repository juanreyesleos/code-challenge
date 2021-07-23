package com.clip.challenge.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transactions")
public class TransactionEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "amount")
	private Double amount;

	@Column(name = "clip_user")
	private String clipUser;

	@Column(name = "card_data", length = 16)
	private String carddata;

	@Column(name = "date")
	private Timestamp date;

	@Column(name = "paid")
	private boolean paid;

	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "id_disbursement", nullable = false)
	private DisbursementEntity disbursementEntity;

	@Column(columnDefinition = "boolean default false")
	public boolean isPaid() {
		return paid;
	}

	public DisbursementEntity getDisbursementEntity() {
		return disbursementEntity;
	}

	public void setDisbursementEntity(DisbursementEntity disbursementEntity) {
		this.disbursementEntity = disbursementEntity;
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

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", amount=" + amount + ", clipUser=" + clipUser + ", carddata=" + carddata
				+ ", date=" + date + ", paid=" + paid + "]";
	}

}

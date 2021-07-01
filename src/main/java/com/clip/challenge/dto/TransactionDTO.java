package com.clip.challenge.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "transaction")
public class TransactionDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "amount")
	@NotNull(message = "amount parameter is mandatory")
	private Double amount;

	@Column(name = "clip_user")
	@NotBlank(message = "clipUser parameter is mandatory")
	private String clipUser;

	@Column(name = "card_data", length = 16)
	@NotBlank(message = "carddata parameter is mandatory")
	private String carddata;

	@Column(name = "date")
	private Timestamp date;

	@Column(name = "paid")
	private boolean paid;

	@Column(name = "id_disbursement")
	private Integer iddisbursement;

	@Column(columnDefinition = "boolean default false")
	public boolean isPaid() {
		return paid;
	}

	public Integer getIddisbursement() {
		return iddisbursement;
	}

	public void setIddisbursement(Integer iddisbursement) {
		this.iddisbursement = iddisbursement;
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

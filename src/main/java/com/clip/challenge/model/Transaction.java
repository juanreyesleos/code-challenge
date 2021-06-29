package com.clip.challenge.model;


import java.sql.Timestamp;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity
@Table (name = "transaction")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private int id;
	
	@Column(name="amount")
	private Double amount;
	
	@Column(name= "clip_user")
	private String clipUser;
	
	@Column(name= "card_data",length = 16)
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

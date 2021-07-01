package com.clip.challenge.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "disbursement")
public class DisbursementDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private int id;
	
	@Column(name="totalammount")
	private Double totalamount;
	
	@Column(name= "clip_user")
	private String clipUser;
	
	@Column(name = "date")
	private Timestamp date;
	
	public DisbursementDTO() {	}

	public DisbursementDTO(String clip_user, Double totalamount) {
		this.clipUser=clip_user;
		this.totalamount=totalamount; 
		this.date=new Timestamp(System.currentTimeMillis());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(Double totalamount) {
		this.totalamount = totalamount;
	}

	public String getClipUser() {
		return clipUser;
	}

	public void setClipUser(String clipUser) {
		this.clipUser = clipUser;
	}
	
	public Timestamp getDate() {
		return date;
	}
	
	public void setDate(Timestamp date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "DisbursementDTO [id=" + id + ", totalamount=" + totalamount + ", clipUser=" + clipUser + ", date="
				+ date + "]";
	}
}
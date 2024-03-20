package com.jsp.hsp_mgmt_systm.dto;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Medorder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mid;
	@CreationTimestamp
	private Date date;
	@NotNull(message = "Doctor cannot be null")
	private String doctor;
	
	@ManyToOne
	private Encounter encounter;
}

package com.jsp.hsp_mgmt_systm.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Meditem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mid;
	@NotNull(message = "Name cannot be null")
	private String name;
	@NotNull(message = "cost cannot be null")
	private double cost;
	
	@ManyToOne
	private Medorder medorder;
}

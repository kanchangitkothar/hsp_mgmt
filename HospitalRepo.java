package com.jsp.hsp_mgmt_systm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.hsp_mgmt_systm.dto.Hospital;

public interface HospitalRepo extends JpaRepository<Hospital, Integer>{

	@Query("select h from Hospital h where h.email=?1")
	public Hospital findHospitalByEmail(String email);
}
package com.jsp.hsp_mgmt_systm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.hsp_mgmt_systm.dto.Encounter;

public interface EncounterRepo extends JpaRepository<Encounter, Integer>{

}

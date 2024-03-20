package com.jsp.hsp_mgmt_systm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.hsp_mgmt_systm.dto.Meditem;

public interface MeditemsRepo extends JpaRepository<Meditem, Integer>{

}

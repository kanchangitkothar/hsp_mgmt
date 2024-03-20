package com.jsp.hsp_mgmt_systm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.hsp_mgmt_systm.dto.Person;

public interface PersonRepo extends JpaRepository<Person, Integer>{

}

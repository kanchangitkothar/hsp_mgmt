package com.jsp.hsp_mgmt_systm.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.hsp_mgmt_systm.dto.Person;
import com.jsp.hsp_mgmt_systm.repo.PersonRepo;

@Repository
public class PersonDao {
	@Autowired
	private PersonRepo personRepo;
	
	public Person savePerson(Person person) {
		return personRepo.save(person);
	}
	
	public Person updatePerson(int pid, Person person) {
		if(personRepo.findById(pid).isPresent()) {
			person.setPid(pid);
			return personRepo.save(person);
		}else {
			return null;
		}
	}
	
	public Person deletePerson(int pid) {
		if(personRepo.findById(pid).isPresent()) {
			Person person = personRepo.findById(pid).get();
			personRepo.deleteById(pid);
			return person;
		}else {
			return null;
		}
	}
	
	public Person getPersonById(int pid) {
		if(personRepo.findById(pid).isPresent()) {
			return personRepo.findById(pid).get();
		}else {
			return null;
		}
	}
}

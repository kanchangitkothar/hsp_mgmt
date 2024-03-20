package com.jsp.hsp_mgmt_systm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.hsp_mgmt_systm.dao.PersonDao;
import com.jsp.hsp_mgmt_systm.dto.Person;
import com.jsp.hsp_mgmt_systm.exception.IdNotFoundException;
import com.jsp.hsp_mgmt_systm.util.ResponseStructure;

@Service
public class PersonService {
	@Autowired
	private PersonDao personDao;
	
	ResponseStructure<Person> responseStructure = new ResponseStructure<Person>();
	
	public ResponseEntity<ResponseStructure<Person>> savePerson(Person person){
		responseStructure.setMessage("Person Data saved successfully");
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setData(personDao.savePerson(person));
		return new ResponseEntity<ResponseStructure<Person>>(responseStructure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Person>> updatePerson(int pid, Person person){
		Person dbPerson = personDao.updatePerson(pid, person);
		if(dbPerson != null) {
			responseStructure.setMessage("Person Data updated successfully");
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setData(person);
			return new ResponseEntity<ResponseStructure<Person>>(responseStructure, HttpStatus.OK);
		}else {
			throw new IdNotFoundException();
		}
	}
	
	public ResponseEntity<ResponseStructure<Person>> deletePersonById(int pid){
		Person dbPerson = personDao.deletePerson(pid);
		if(dbPerson != null) {
			responseStructure.setMessage("Person Data deleted successfully");
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setData(dbPerson);
			return new ResponseEntity<ResponseStructure<Person>>(responseStructure, HttpStatus.OK);
		}else {
			throw new IdNotFoundException();
		}
	}
	
	public ResponseEntity<ResponseStructure<Person>> getPersonById(int pid){
		Person dbPerson = personDao.getPersonById(pid);
		if(dbPerson != null) {
			responseStructure.setMessage("Person Data found");
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setData(dbPerson);
			return new ResponseEntity<ResponseStructure<Person>>(responseStructure, HttpStatus.FOUND);
		}else {
			throw new IdNotFoundException();
		}
	}
}

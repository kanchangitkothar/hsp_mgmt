package com.jsp.hsp_mgmt_systm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.hsp_mgmt_systm.dao.BranchDao;
import com.jsp.hsp_mgmt_systm.dao.EncounterDao;
import com.jsp.hsp_mgmt_systm.dao.PersonDao;
import com.jsp.hsp_mgmt_systm.dto.Branch;
import com.jsp.hsp_mgmt_systm.dto.Encounter;
import com.jsp.hsp_mgmt_systm.dto.Person;
import com.jsp.hsp_mgmt_systm.exception.IdNotFoundException;
import com.jsp.hsp_mgmt_systm.util.ResponseStructure;

@Service
public class EncounterService {
	@Autowired
	private EncounterDao encounterDao;
	@Autowired
	private BranchDao branchDao;
	@Autowired
	private PersonDao personDao;
	
	ResponseStructure<Encounter> responseStructure = new ResponseStructure<Encounter>();
	
	public ResponseEntity<ResponseStructure<Encounter>> saveEncounter(Encounter encounter, int pid, int bid){
		Person dbPerson = personDao.getPersonById(pid);
		Branch dbBranch = branchDao.getBranchById(bid);
		encounter.setPerson(dbPerson);
		
		List<Branch> list = new ArrayList<Branch>();
		list.add(dbBranch);
		
		encounter.setList(list);
		responseStructure.setMessage("Encounter saved successfully");
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setData(encounterDao.saveEncounter(encounter));
		
		return new ResponseEntity<ResponseStructure<Encounter>>(responseStructure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Encounter>> updateEncounter(Encounter encounter, int bid, int eid){
		Encounter dbEncounter = encounterDao.getEncounterById(eid);
		Branch dbBranch = branchDao.getBranchById(bid);
		
		List<Branch> list = dbEncounter.getList();
		encounter.setList(list);
		encounter.setPerson(dbEncounter.getPerson());
		
		responseStructure.setMessage("Encounter updated successfully");
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setData(encounterDao.updateEncounter(eid, dbEncounter));
		return new ResponseEntity<ResponseStructure<Encounter>>(responseStructure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Encounter>> deleteEncounter(int eid){
		Encounter dbEncounter = encounterDao.deleteEncounter(eid);
		if(dbEncounter != null) {
			responseStructure.setMessage("Encounter details deleted successfully");
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setData(dbEncounter);
			return new ResponseEntity<ResponseStructure<Encounter>>(responseStructure, HttpStatus.OK);
		}else {
			throw new IdNotFoundException();
		}
	}
	
	public ResponseEntity<ResponseStructure<Encounter>> getEncounterById(int eid){
		Encounter dbEncounter = encounterDao.getEncounterById(eid);
		if(dbEncounter != null) {
			responseStructure.setMessage("Encounter details found");
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setData(dbEncounter);
			return new ResponseEntity<ResponseStructure<Encounter>>(responseStructure, HttpStatus.FOUND);
		}else {
			throw new IdNotFoundException();
		}
	}
}
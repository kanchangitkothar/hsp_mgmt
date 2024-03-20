package com.jsp.hsp_mgmt_systm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.hsp_mgmt_systm.dao.MedorderDao;
import com.jsp.hsp_mgmt_systm.dto.Medorder;
import com.jsp.hsp_mgmt_systm.exception.IdNotFoundException;
import com.jsp.hsp_mgmt_systm.util.ResponseStructure;

@Service
public class MedorderService {
	@Autowired
	private MedorderDao medorderDao;
	
	ResponseStructure<Medorder> responseStructure = new ResponseStructure<Medorder>();
	
	public ResponseEntity<ResponseStructure<Medorder>> saveMedorder(Medorder medorder, int eid){
		responseStructure.setMessage("Medorder saved successfully");
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setData(medorderDao.saveMedorder(medorder, eid));
		return new ResponseEntity<ResponseStructure<Medorder>>(responseStructure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Medorder>> updateMedorder(int mid, Medorder medorder){
		Medorder dbMedorder = medorderDao.getMedorderById(mid);
		medorder.setEncounter(dbMedorder.getEncounter());
		
		Medorder medorder2 = medorderDao.updateMedorder(mid, medorder);
		
		responseStructure.setMessage("Medorder updated successfully");
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setData(medorder2);
		
		return new ResponseEntity<ResponseStructure<Medorder>>(responseStructure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Medorder>> deleteMedorder(int mid){
		Medorder dbMedorder = medorderDao.deleteMedorder(mid);
		
		if(dbMedorder != null) {
			responseStructure.setMessage("Medorder deleted successfully");
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setData(dbMedorder);
			
			return new ResponseEntity<ResponseStructure<Medorder>>(responseStructure, HttpStatus.OK);
		}else {
			throw new IdNotFoundException();
		}
	}
	
	public ResponseEntity<ResponseStructure<Medorder>> getMedorderById(int mid){
		Medorder dbMedorder = medorderDao.getMedorderById(mid);
		
		if(dbMedorder != null) {
			responseStructure.setMessage("Medorder deleted found");
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setData(dbMedorder);
			
			return new ResponseEntity<ResponseStructure<Medorder>>(responseStructure, HttpStatus.FOUND);
		}else {
			throw new IdNotFoundException();
		}
	}
}

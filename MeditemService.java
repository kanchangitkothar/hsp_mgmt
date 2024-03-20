package com.jsp.hsp_mgmt_systm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.hsp_mgmt_systm.dao.MeditemsDao;
import com.jsp.hsp_mgmt_systm.dto.Meditem;
import com.jsp.hsp_mgmt_systm.exception.IdNotFoundException;
import com.jsp.hsp_mgmt_systm.util.ResponseStructure;

@Service
public class MeditemService {
	@Autowired
	private MeditemsDao meditemsDao;
	
	ResponseStructure<Meditem> responseStructure = new ResponseStructure<Meditem>();
	
	public ResponseEntity<ResponseStructure<Meditem>> saveMeditem(Meditem meditem, int mid){
		responseStructure.setMessage("meditems saved successfully");
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setData(meditemsDao.saveMeditem(meditem, mid));
		return new ResponseEntity<ResponseStructure<Meditem>>(responseStructure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Meditem>> updateMeditem(int mid, Meditem meditem){
		Meditem dbMeditem = meditemsDao.getMeditemById(mid);
		meditem.setMedorder(dbMeditem.getMedorder());
		Meditem meditem2 = meditemsDao.updateMeditem(mid, meditem);
		
		if(meditem2 != null) {
			responseStructure.setMessage("Meditem updated successfully");
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setData(meditem2);
			return new ResponseEntity<ResponseStructure<Meditem>>(responseStructure, HttpStatus.OK);
		}else {
			throw new IdNotFoundException();
		}
	}
	
	public ResponseEntity<ResponseStructure<Meditem>> deleteMeditem(int mid){
		Meditem meditem = meditemsDao.deleteMeditem(mid);
		if(meditem != null) {
			responseStructure.setMessage("Meditem deleted successfully");
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setData(meditem);
			return new ResponseEntity<ResponseStructure<Meditem>>(responseStructure, HttpStatus.OK);
		}else {
			throw new IdNotFoundException();
		}
	}
	
	public ResponseEntity<ResponseStructure<Meditem>> getMeditemById(int mid){
		Meditem meditem = meditemsDao.getMeditemById(mid);
		if(meditem != null) {
			responseStructure.setMessage("Meditem data found");
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setData(meditem);
			return new ResponseEntity<ResponseStructure<Meditem>>(responseStructure, HttpStatus.FOUND);
		}else {
			throw new IdNotFoundException();
		}
	}
}

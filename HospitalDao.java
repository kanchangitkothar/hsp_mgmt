package com.jsp.hsp_mgmt_systm.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.hsp_mgmt_systm.dto.Hospital;
import com.jsp.hsp_mgmt_systm.repo.HospitalRepo;

@Repository
public class HospitalDao {
	@Autowired
	private HospitalRepo hospitalRepo;
	
	public Hospital saveHospital(Hospital hospital) {
		return hospitalRepo.save(hospital);
	}
	
	public Hospital updateHospital(int hid, Hospital hospital) {
		if(hospitalRepo.findById(hid).isPresent()) {
			hospital.setHid(hid);
			return hospitalRepo.save(hospital);
		}else {
			return null;
		}
	}
	
	public Hospital deleteHospital(int hid) {
		if(hospitalRepo.findById(hid).isPresent()) {
			Hospital hospital = hospitalRepo.findById(hid).get();
			hospitalRepo.deleteById(hid);
			return hospital;
		}else {
			return null;
		}
	}
	
	public Hospital getHospitalById(int hid) {
		Hospital hospital = hospitalRepo.findById(hid).get();
		return hospital;
	}
	
	public Hospital getHospitalByEmail(String email) {
		return hospitalRepo.findHospitalByEmail(email);
	}
}

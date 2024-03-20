package com.jsp.hsp_mgmt_systm.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.hsp_mgmt_systm.dto.Address;
import com.jsp.hsp_mgmt_systm.dto.Branch;
import com.jsp.hsp_mgmt_systm.dto.Hospital;
import com.jsp.hsp_mgmt_systm.repo.BranchRepo;

@Repository
public class BranchDao {
	@Autowired
	private BranchRepo branchRepo;
	
	@Autowired
	private HospitalDao hospitalDao;
	
	@Autowired
	private AddressDao addressDao;
	
	
	public Branch saveBranch(int hid, int aid,Branch branch) {
		Hospital hospital = hospitalDao.getHospitalById(hid);
		branch.setHospital(hospital);
		Address address = addressDao.getAddressById(aid);
		branch.setAddress(address);
		return branchRepo.save(branch);
	}
	
	public Branch updateBranch(int bid, Branch branch) {
		Branch dbbranch = branchRepo.findById(bid).get();
		if(dbbranch != null) {
			branch.setBid(bid);
			branch.setHospital(dbbranch.getHospital());
			branch.setAddress(dbbranch.getAddress());
			return branchRepo.save(branch);
		}else {
			return null;
		}
	}
	
	public Branch deleteBranch(int bid) {
		if(branchRepo.findById(bid).isPresent()) {
			Branch dbbranch = branchRepo.findById(bid).get();
			branchRepo.deleteById(bid);
			return dbbranch;
		}else {
			return null;
		}
	}
	
	public Branch getBranchById(int bid) {
		if(branchRepo.findById(bid).isPresent()) {
			return branchRepo.findById(bid).get();
		}else {
			return null;
		}
	}
	
	public List<Branch> getBranchByHospitalId(int hid){
		Hospital hospital = hospitalDao.getHospitalById(hid);
		return branchRepo.findBranchByHospitalId(hospital);
	}
}
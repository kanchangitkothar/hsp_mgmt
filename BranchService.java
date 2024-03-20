package com.jsp.hsp_mgmt_systm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.hsp_mgmt_systm.dao.BranchDao;
import com.jsp.hsp_mgmt_systm.dto.Branch;
import com.jsp.hsp_mgmt_systm.exception.IdNotFoundException;
import com.jsp.hsp_mgmt_systm.util.ResponseStructure;

@Service
public class BranchService {
	@Autowired
	private BranchDao branchDao;
	
	ResponseStructure<Branch> responseStructure = new ResponseStructure<Branch>();
	
	public ResponseEntity<ResponseStructure<Branch>> saveBranch(int hid, int aid, Branch branch){
		responseStructure.setMessage("Branch details saved successfully");
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setData(branchDao.saveBranch(hid, aid, branch));
		return new ResponseEntity<ResponseStructure<Branch>>(responseStructure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Branch>> updateBranch(int bid, Branch branch){
		Branch dbBranch = branchDao.updateBranch(bid, branch);
		if(dbBranch != null) {
			responseStructure.setMessage("Branch details updated successfully");
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setData(dbBranch);
			return new ResponseEntity<ResponseStructure<Branch>>(responseStructure, HttpStatus.OK);
		}else {
			throw new IdNotFoundException();
		}
	}
	
	public ResponseEntity<ResponseStructure<Branch>> deleteBranch(int bid){
		Branch branch = branchDao.deleteBranch(bid);
		if(branch != null) {
			responseStructure.setMessage("Branch details deleted successfully");
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setData(branch);
			return new ResponseEntity<ResponseStructure<Branch>>(responseStructure, HttpStatus.OK);
		}else {
			throw new IdNotFoundException();
		}
	}
	
	public ResponseEntity<ResponseStructure<Branch>> getBranchById(int bid){
		Branch branch = branchDao.getBranchById(bid);
		if(branch != null) {
			responseStructure.setMessage("Branch details found");
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setData(branch);
			return new ResponseEntity<ResponseStructure<Branch>>(responseStructure, HttpStatus.FOUND);
		}else {
			throw new IdNotFoundException();
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Branch>>> getBranchByHospitalId(int hid){
		ResponseStructure<List<Branch>> responseStructure = new ResponseStructure<List<Branch>>();
		List<Branch> branches = branchDao.getBranchByHospitalId(hid);
		if(branches != null) {
			responseStructure.setMessage("Branch details found");
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setData(branches);
			return new ResponseEntity<ResponseStructure<List<Branch>>>(responseStructure, HttpStatus.FOUND);
		}else {
			throw new IdNotFoundException();
		}
 	}
}

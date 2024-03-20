package com.jsp.hsp_mgmt_systm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.hsp_mgmt_systm.dao.AddressDao;
import com.jsp.hsp_mgmt_systm.dto.Address;
import com.jsp.hsp_mgmt_systm.exception.IdNotFoundException;
import com.jsp.hsp_mgmt_systm.util.ResponseStructure;

@Service
public class AddressService {
	@Autowired
	private AddressDao addressDao;
	
	ResponseStructure<Address> responseStructure = new ResponseStructure<Address>();
	
	public ResponseEntity<ResponseStructure<Address>> saveAddress(Address address){
		responseStructure.setMessage("Address data saved successfully");
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setData(addressDao.saveAddress(address));
		return new ResponseEntity<ResponseStructure<Address>>(responseStructure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Address>> updateAddress(int aid, Address address){
		Address dbAddress = addressDao.updateAddress(aid, address);
		if(dbAddress != null) {
			responseStructure.setMessage("Address data updated successfully");
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setData(dbAddress);
			return new ResponseEntity<ResponseStructure<Address>>(responseStructure, HttpStatus.OK);
		}else {
			throw new IdNotFoundException();
		}
	}
	
	public ResponseEntity<ResponseStructure<Address>> deleteAddress(int aid){
		Address dbAddress = addressDao.deleteAddress(aid);
		if(dbAddress != null) {
			responseStructure.setMessage("Address data deleted successfully");
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setData(dbAddress);
			return new ResponseEntity<ResponseStructure<Address>>(responseStructure, HttpStatus.OK);
		}else {
			throw new IdNotFoundException();
		}
	}
	
	public ResponseEntity<ResponseStructure<Address>> getAddressById(int aid){
		Address dbAddress = addressDao.getAddressById(aid);
		if(dbAddress != null) {
			responseStructure.setMessage("Address Data found");
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setData(dbAddress);
			return new ResponseEntity<ResponseStructure<Address>>(responseStructure, HttpStatus.FOUND);
		}else {
			throw new IdNotFoundException();
		}
	}
}

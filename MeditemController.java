package com.jsp.hsp_mgmt_systm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.hsp_mgmt_systm.dto.Meditem;
import com.jsp.hsp_mgmt_systm.service.MeditemService;
import com.jsp.hsp_mgmt_systm.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/meditem")
public class MeditemController {
	@Autowired
	private MeditemService meditemService;
	
	@ApiOperation(value = "save meditems", notes = "api is used to save meditems")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "successfully saved")})
	@PostMapping
	public ResponseEntity<ResponseStructure<Meditem>> saveMeditem(@RequestBody Meditem meditem, @RequestParam int mid){
		return meditemService.saveMeditem(meditem, mid);
	}
	
	@ApiOperation(value = "update meditems", notes = "api is used to update meditems")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "successfully updated"),
			@ApiResponse(code = 404, message = "id not found")})
	@PutMapping
	public ResponseEntity<ResponseStructure<Meditem>> updateMeditem(@RequestParam int mid, @RequestBody Meditem meditem){
		return meditemService.updateMeditem(mid, meditem);
	}
	
	@ApiOperation(value = "delete meditems", notes = "api is used to delete meditems")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "successfully deleted"),
			@ApiResponse(code = 404, message = "Id not found")})
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Meditem>> deleteMeditem(@RequestParam int mid){
		return meditemService.deleteMeditem(mid);
	}
	
	@ApiOperation(value = "get meditems by id", notes = "api is used to fetch meditems")
	@ApiResponses(value = {@ApiResponse(code = 302, message = "successfully found"),
			@ApiResponse(code = 404, message = "Id not found")})
	@GetMapping
	public ResponseEntity<ResponseStructure<Meditem>> getMeditemsById(@RequestParam int mid){
		return meditemService.getMeditemById(mid);
	}
}
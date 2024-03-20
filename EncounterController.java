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

import com.jsp.hsp_mgmt_systm.dto.Encounter;
import com.jsp.hsp_mgmt_systm.service.EncounterService;
import com.jsp.hsp_mgmt_systm.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/encounter")
public class EncounterController {
	@Autowired
	private EncounterService encounterService;

	@ApiOperation(value = "save encounter", notes = "api is used to save encounter")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "successfully saved")})
	@PostMapping
	public ResponseEntity<ResponseStructure<Encounter>> saveEncounter(@RequestBody Encounter encounter,@RequestParam int pid, @RequestParam int bid){
		return encounterService.saveEncounter(encounter, pid, bid);
	}
	
	@ApiOperation(value = "update encounter", notes = "api is used to update encounter")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "successfully updated"),
			@ApiResponse(code = 404, message = "id not found")})
	@PutMapping
	public ResponseEntity<ResponseStructure<Encounter>> updateEncounter(@RequestParam int eid,@RequestParam int bid, @RequestBody Encounter encounter){
		return encounterService.updateEncounter(encounter, bid, eid);
	}
	
	@ApiOperation(value = "delete encounter", notes = "api is used to delete encounter")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "successfully deleted"),
			@ApiResponse(code = 404, message = "Id not found")})
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Encounter>> deleteEncounter(@RequestParam int eid){
		return encounterService.deleteEncounter(eid);
	}
	
	@ApiOperation(value = "get encounter by id", notes = "api is used to fetch encounter")
	@ApiResponses(value = {@ApiResponse(code = 302, message = "successfully found"),
			@ApiResponse(code = 404, message = "Id not found")})
	@GetMapping
	public ResponseEntity<ResponseStructure<Encounter>> getEncounterById(@RequestParam int eid){
		return encounterService.getEncounterById(eid);
	}
}

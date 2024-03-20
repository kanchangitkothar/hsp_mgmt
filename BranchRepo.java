package com.jsp.hsp_mgmt_systm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.hsp_mgmt_systm.dto.Branch;
import com.jsp.hsp_mgmt_systm.dto.Hospital;

public interface BranchRepo extends JpaRepository<Branch, Integer>{

	@Query("select b from Branch b where b.hospital=?1")
	public List<Branch> findBranchByHospitalId(Hospital hospital);
}

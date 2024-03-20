package com.jsp.hsp_mgmt_systm.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.hsp_mgmt_systm.dto.Meditem;
import com.jsp.hsp_mgmt_systm.dto.Medorder;
import com.jsp.hsp_mgmt_systm.repo.MeditemsRepo;

@Repository
public class MeditemsDao {
	@Autowired
	private MeditemsRepo meditemsRepo;
	
	@Autowired
	private MedorderDao medorderDao;
	
	public Meditem saveMeditem(Meditem meditem, int mid) {
		Medorder medorder = medorderDao.getMedorderById(mid);
		meditem.setMedorder(medorder);
		return meditemsRepo.save(meditem);
	}
	
	public Meditem updateMeditem(int mid, Meditem meditem) {
		if(meditemsRepo.findById(mid).isPresent()) {
			meditem.setMid(mid);
			return meditemsRepo.save(meditem);
		}else {
			return null;
		}
	}
	
	public Meditem deleteMeditem(int mid) {
		if(meditemsRepo.findById(mid).isPresent()) {
			Meditem meditem = meditemsRepo.findById(mid).get();
			meditemsRepo.deleteById(mid);
			return meditem;
		}else {
			return null;
		}
	}
	
	public Meditem getMeditemById(int mid) {
		return meditemsRepo.findById(mid).get();
	}
}

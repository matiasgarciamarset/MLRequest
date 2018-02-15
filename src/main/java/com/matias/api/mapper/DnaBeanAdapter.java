package com.matias.api.mapper;

import com.matias.api.dto.DnaDTO;
import com.matias.service.dto.DnaBean;

public class DnaBeanAdapter extends DnaBean {
	
	public DnaBeanAdapter(DnaDTO dnaDto) {
		super(dnaDto.getDna());
	}

}

package com.matias.service;

import com.matias.service.dto.DnaBean;
import com.matias.service.error.ServiceException;
import com.matias.service.type.TipoDna;

public interface AnalizadorService {

	Boolean esMutante(DnaBean dnaBean) throws ServiceException;

	Long cantidadMutantes() throws ServiceException;

	Long cantidadHumanos() throws ServiceException;
	
	TipoDna tipoDna(DnaBean dnaBean) throws ServiceException;

}

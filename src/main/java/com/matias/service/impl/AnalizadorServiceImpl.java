package com.matias.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matias.dao.DnaDAO;
import com.matias.dao.error.DBException;
import com.matias.service.AnalizadorService;
import com.matias.service.dto.DnaBean;
import com.matias.service.error.ServiceException;
import com.matias.service.mapper.DnaMapper;
import com.matias.service.type.TipoDna;
import com.matias.service.util.AnalizadorConsecutivos;

@Service
public class AnalizadorServiceImpl implements AnalizadorService {
	
	@Autowired
	private List<DnaDAO> dnaDao;
	
	@Override
	public Boolean esMutante(DnaBean dnaBean) throws ServiceException {
		TipoDna tipo = tipoDna(dnaBean);
		registroDna(tipo, dnaBean);
		return tipo == TipoDna.MUTANTE;
	}

	@Override
	public Long cantidadMutantes() throws ServiceException {
		try {
			return getDnaContext(TipoDna.MUTANTE).count();
		} catch (DBException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Long cantidadHumanos() throws ServiceException {
		try {
			return getDnaContext(TipoDna.HUMANO).count();
		} catch (DBException e) {
			throw new ServiceException(e);
		}
	}
	
	private void registroDna(TipoDna tipo, DnaBean dnaBean) throws ServiceException {
		dnaBean.setTipo(tipo);
		DnaMapper mapper = new DnaMapper();
		try {
			getDnaContext(tipo).save(mapper.toEntity(dnaBean, tipo));
		} catch (DBException e) {
			throw new ServiceException(e);
		}
	}

	private DnaDAO getDnaContext(TipoDna tipo) {
		return dnaDao.stream().filter(dao -> dao.support() == tipo).findAny().get();
	}

	public TipoDna tipoDna(DnaBean dnaBean) throws ServiceException {
		// NIVEL 1
		if (dnaBean == null || dnaBean.getDna() == null) {
			throw new ServiceException("ADN no puede ser nulo");
		}
		AnalizadorConsecutivos analizador = new AnalizadorConsecutivos(dnaBean.getDna(), 4);
		return analizador.poseeConsecutivos() ? TipoDna.MUTANTE : TipoDna.HUMANO;
	}

}

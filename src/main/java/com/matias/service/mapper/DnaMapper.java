package com.matias.service.mapper;

import java.util.Arrays;
import java.util.List;

import com.matias.model.Dna;
import com.matias.model.impl.DnaHumano;
import com.matias.model.impl.DnaMutante;
import com.matias.service.dto.DnaBean;
import com.matias.service.error.ServiceException;
import com.matias.service.type.TipoDna;

public class DnaMapper {
	
	private final List<Class<? extends Dna> > ENTIDADES = Arrays.asList(DnaHumano.class, DnaMutante.class); 

	public Dna toEntity(DnaBean dnaBean, TipoDna tipo) throws ServiceException {
		Dna obj = null;
		try {
			obj = (Dna) ENTIDADES.stream().filter(clase -> clase.getName().toLowerCase().endsWith(tipo.toString().toLowerCase())).findAny().get().newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new ServiceException("Error instanciando objeto");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		} catch (NullPointerException e) {
			throw new ServiceException("No se encontro entidad en ENTIDADES terminada en "+tipo.toString());
		}
		obj.setDna(aplanar(dnaBean.getDna()));
		return obj;
	}
	
	public String aplanar(String[] lista) {
		if (lista.length == 0) {
			return "";
		} else if (lista.length == 1) {
			return lista[0];
		}
		StringBuffer buffer = new StringBuffer();
		for (String palabra : lista) {
			buffer.append(palabra).append(",");
		}
		
		return buffer.substring(0, buffer.length()-1).toString();
	}
}

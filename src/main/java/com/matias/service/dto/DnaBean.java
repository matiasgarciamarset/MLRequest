package com.matias.service.dto;

import com.matias.service.type.TipoDna;

public class DnaBean {

	protected String[] dna;

	protected TipoDna tipo;
	
	public DnaBean(String[] dna) {
		this.dna = dna;
		this.tipo = TipoDna.INDEFINIDO;
	}
	
	public String[] getDna() {
		return dna;
	}
	public void setDna(String[] dna) {
		this.dna = dna;
	}
	public TipoDna getTipo() {
		return tipo;
	}
	public void setTipo(TipoDna tipo) {
		this.tipo = tipo;
	}

}

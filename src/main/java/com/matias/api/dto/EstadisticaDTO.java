package com.matias.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EstadisticaDTO {

	@JsonProperty("count_mutant_dna")
	private Long cantidadMutantes;
	
	@JsonProperty("count_human_dna")
	private Long cantidadHumanos;
	
	@JsonProperty("ratio")
	private Float proporcion;

	public Long getCantidadHumanos() {
		return cantidadHumanos;
	}

	public void setCantidadHumanos(Long cantidadHumano) {
		this.cantidadHumanos = cantidadHumano;
	}

	public Long getCantidadMutantes() {
		return cantidadMutantes;
	}

	public void setCantidadMutantes(Long cantidadMutante) {
		this.cantidadMutantes = cantidadMutante;
	}

	public Float getProporcion() {
		return proporcion;
	}

	public void setProporcion(Float proporcion) {
		this.proporcion = proporcion;
	}
	
}

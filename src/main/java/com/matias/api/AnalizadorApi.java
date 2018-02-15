package com.matias.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matias.api.dto.DnaDTO;
import com.matias.api.dto.EstadisticaDTO;
import com.matias.api.mapper.DnaBeanAdapter;
import com.matias.api.util.ApiUtil;
import com.matias.service.AnalizadorService;
import com.matias.service.error.ServiceException;

@RestController
@RequestMapping("/analyze")
@Produces("application/json")
@Consumes("application/json")
public class AnalizadorApi {
	
	@Autowired
	private AnalizadorService analizadorService;
	
	// NIVEL 2
	@POST
	@RequestMapping(value = "/mutant")
	public ResponseEntity<String> esMutante(@RequestBody DnaDTO dna) { 
		try {
			if (analizadorService.esMutante(new DnaBeanAdapter(dna))) {
				return ResponseEntity.ok("");
			} else { 
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("");
			}
		} catch (ServiceException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No se pudo procesar la solicitud");
		}
	}

	// NIVEL 3
	@GET
	@RequestMapping(value = "/stats")
	public ResponseEntity<EstadisticaDTO> estadisticas() {
		EstadisticaDTO estadistica = new EstadisticaDTO();
		try {
			estadistica.setCantidadMutantes(analizadorService.cantidadMutantes());
			estadistica.setCantidadHumanos(analizadorService.cantidadHumanos());
		} catch (ServiceException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		ApiUtil.setRatio(estadistica);
		return ResponseEntity.ok(estadistica);
	}
	
}

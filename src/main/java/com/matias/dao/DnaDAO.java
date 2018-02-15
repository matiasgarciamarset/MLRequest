package com.matias.dao;

import com.matias.dao.error.DBException;
import com.matias.model.Dna;
import com.matias.service.type.TipoDna;

public interface DnaDAO {

	/**
	 * Guarda un Dna en la base de datos
	 * @param dna a guardar
	 * @return true si fue guardado con exito. false en caso contrario
	 * @throws DBException si hay error al guardar
	 */
	void save(Dna dna) throws DBException;
	
	/**
	 * Retorna la cantidad de entradas que tiene la tabla.
	 * @return Long que representa cantidad de entradas.
	 * @throws DBException si hay error al acceder a la BD
	 */
	Long count() throws DBException;
	
	/**
	 * Define el tipo de Dna que suporta el DAO
	 * @return TipoDna soportado
	 */
	TipoDna support();
}

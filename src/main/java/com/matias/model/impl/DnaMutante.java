package com.matias.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.matias.model.Dna;

/* Query para crear tabla
 CREATE TABLE matias.dna_humano (
  id int(20) NOT NULL AUTO_INCREMENT,
  dna varchar(255) NULL,
  PRIMARY KEY (id)
);
 */
@Entity
@Table(name = "dna_mutante")
public class DnaMutante implements Dna {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "dna")
	private String dna;
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setDna(String dna) {
		this.dna = dna;
	}

}

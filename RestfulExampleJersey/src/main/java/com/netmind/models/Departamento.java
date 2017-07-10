package com.netmind.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Departamento")
public class Departamento {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Id")	// Columna en la base de datos
	public int did;
	
	@Column(name = "Nombre")
	public String nombre;
	
	@Column(name = "Localizacion")
	public String localizacion;
	
	
	public Departamento() {
	
	}	
	
	
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getLocalizacion() {
		return localizacion;
	}
	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}
	
	
	

}

package com.netmind.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "proyectos")
public class proyectos {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "pid" )
	private int pid;
	
	@ForeignKey(name = "usuarios.uid")
	private int uid;
	
	@Column
	private String codigo;
	
	@Column
	private String titulo;
	
	@Column
	private Date fechainicio;
	
	@Column
	private Date fechafin;
	
	@Column
	private boolean estado ;
	
	public proyectos(int pid,int uid, String codigo,  String titulo,Date fechainicio, Date fechafin, boolean estado) {
		super();
		this.pid =  pid;
		this.uid =  uid;
		this.codigo = codigo;
		this.titulo = titulo;
		this.fechainicio = fechainicio;
		this.fechafin = fechafin;
		this.estado = estado;
	}
	
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String gettitulo() {
		return titulo;
	}

	public void settitulo(String titulo) {
		this.titulo = titulo;
	}
	public Date getFechainicio() {
		return fechainicio;
	}
	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}
	public Date getFechafin() {
		return fechafin;
	}
	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}
	
	public boolean getestado() {
		return estado;
	}
	public void setestado(boolean estado) {
		this.estado = estado;
	}
}
	
	
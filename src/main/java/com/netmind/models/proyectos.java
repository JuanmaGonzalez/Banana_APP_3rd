package com.netmind.models;

import java.util.Date;

public class proyectos {
	
	private int pid;
	private int uid;
	private String codigo;
	private String titulo;
	private Date fechainicio;
	private Date fechafin;
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
	
	
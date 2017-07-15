package com.netmind.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tareas")
public class tareas {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "tid" )
	private int tid;
	
	@Column
	private int uid;
	
	@Column
	private int pid;
	
	@Column
	private String tarea;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date fechafin;
	
		
	public tareas() {
		super();
	}

	public tareas(int tid, int uid, int pid, String tarea, Date fechafin) {
		super();
		this.tid = tid;
		this.uid = uid;
		this.pid = pid;
		this.tarea = tarea;
		this.fechafin = fechafin;
	}
	
	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getTarea() {
		return tarea;
	}

	public void setTarea(String tarea) {
		this.tarea = tarea;
	}

	public Date getFechafin() {
		return fechafin;
	}

	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}
	
}
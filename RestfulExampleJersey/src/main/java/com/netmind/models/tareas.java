package com.netmind.models;

import java.util.Date;

public class tareas {
	private int tid;
	private int uid;
	private int pid;
	private String tarea;
	private Date fechafin;
	
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
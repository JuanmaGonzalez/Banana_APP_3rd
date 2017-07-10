package com.netmind.db;

import java.util.List;

import com.netmind.models.proyectos;

public abstract class ProyectoDAO extends DAO {
	public abstract proyectos getProyecto(int pid);
	public abstract List<proyectos> getUserProyectos(int uid);
	public abstract List<proyectos> getProyectos();
	public abstract boolean delProyecto(int pid);
	public abstract boolean insertProyecto(proyectos proyecto);
	public abstract boolean updateProyecto(proyectos proyecto);
	
	
}

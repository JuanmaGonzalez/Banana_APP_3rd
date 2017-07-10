package com.netmind.db;

import java.util.List;

import com.netmind.models.tareas;

public abstract class TareaDAO extends DAO {
	public abstract List<tareas> getTareas(int pid);
	public abstract boolean delTarea(int tid);
	public abstract boolean insertarTarea(tareas tarea);
	public abstract boolean updateTarea(tareas tarea);
	
}

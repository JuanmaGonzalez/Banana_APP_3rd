package com.netmind.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.netmind.db.ProyectoDAO;
import com.netmind.db.ProyectoDAOImpl;
import com.netmind.db.TareaDAO;
import com.netmind.db.TareaDAOImpl;
import com.netmind.models.Message;
import com.netmind.models.User;
import com.netmind.models.proyectos;
import com.netmind.models.tareas;

@Path("/tareas")
public class TareaResource {
	private static List<tareas> tareasUser;
	private static tareas UnaTarea;

	/* GET|POST /Lista de tareas del Usuario solicitado */
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<tareas> getTareas(@PathParam("pid") int pid) {

		// acceso a la clase devolver TAREAS del usuario

		TareaDAO tarPers = (TareaDAO) TareaDAOImpl.getInstance();
		TareaResource.tareasUser = tarPers.getTareasProyecto(pid);

		return TareaResource.tareasUser;
	}
	
	//borrar tarea
	@Path("/{tid}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message delTarea(@PathParam("tid") int tid) {
		boolean OkInsertP = false;
		String Mensage = "";

		TareaDAO tarPers = (TareaDAO) TareaDAOImpl.getInstance();
		OkInsertP = tarPers.delTarea(tid);
		
		if(OkInsertP) {
			Mensage  = "Tarea Borrado Correctamente";			
		}else {
			Mensage  = "Tarea No BORRADO";
		}
				
	return new Message(Mensage);
	}
	
	
	/* POST Insertar un tarea */
	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message insertarTarea(tareas nuevoOeditTarea) {
		boolean Oktarea = false;

		TareaDAO tarPers = (TareaDAO) TareaDAOImpl.getInstance();
          Oktarea =  tarPers.insertarTarea(nuevoOeditTarea);

		return new Message("Tarea Añadida");
	}


	//Modificar tarea
	@Path("/")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateTarea(tareas modificarTarea ) {
		
		boolean Oktarea = false;

		TareaDAO tarPers = (TareaDAO) TareaDAOImpl.getInstance();
          Oktarea =  tarPers.updateTarea(modificarTarea);


		return new Message("Tarea modificada");
	}
}
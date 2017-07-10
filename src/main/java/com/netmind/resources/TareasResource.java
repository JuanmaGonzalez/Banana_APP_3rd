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

import com.netmind.models.Message;
import com.netmind.models.User;
import com.netmind.models.proyectos;
import com.netmind.models.tareas;

@Path("/tareas")
public class TareasResource {
	private static List<tareas> tareasUser;
	private static tareas UnaTarea;
	
	/* GET|POST /Lista de tareas del Usuario solicitado */
	@Path("/usuario/{uid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<tareas> getTareasListUser(@PathParam("uid") int uid) {		
		
		// acceso a la clase devolver TAREAS del usuario
		
		return this.tareasUser;
	}	
	
	
	/* POST Insertar o Actualizar tarea */
	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message insertTarea(tareas nuevoOeditTarea) {
				
		// añadiriamos un proyecto si este id de TAREA no existe lo creamos si existe lo modificamos
		
		
		return new Message("Tarea Añadida");
	}

	/* GET|PUT|DELETE /tareas/{tid} */
	@Path("/{tid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public tareas getTareaUser(@PathParam("tid") int tid) {

	   /*	User unUser = new User();
		for (User user : misUsuarios) {
			if(user.getUid()==uid){
				unUser=user;
				break;
			}
		}
		*/
		
		// acceso a la Bdatos para devolver una tarea.

		return this.UnaTarea;
	}
	
	@Path("/{tid}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message deleteTarea(@PathParam("tid") int tid) {
		
		/*
		for (User user : misUsuarios) {
			if(user.getUid()==uid){
				misUsuarios.remove(user);
				break;
			}
		}
		*/

		return new Message("Tarea Borrado");
	}	
	
}

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
import com.netmind.models.Usuario;
import com.netmind.models.proyectos;

@Path("/proyectos")
public class ProyectoResource {
	private static List<proyectos> proyectosUser;
	private static proyectos Unproyecto;
	
	/* GET|POST /Lista de proyectos del Usuario solicitado */
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<proyectos> getProyectosList(@PathParam("uid") int uid) {		
		
		/*
		Usuario elUsuario = (Usuario) misession.getAttribute("usuario");
		
		ProyectoDAO pDAO = (ProyectoDAO) ProyectoDAOImpl.getInstance();
		TareaDAO tDAO = (TareaDAO) TareaDAOImpl.getInstance();
		*/
		
		ProyectoDAO proPers = (ProyectoDAO) ProyectoDAOImpl.getInstance();
		this.proyectosUser = proPers.getUserProyectos(uid); 
		
		
		// acceso a la clase devolver proyectos del usuario
		
		return this.proyectosUser;
	}	
	
	
	/* POST Insertar o Actualizar proyecto */
	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message insertUsuario(proyectos nuevoOedit) {
		
		
		// añadiriamos un proyecto si este id de proyecto no existe lo creamos si existe lo modificamos
		
		
		return new Message("Proyecto Añadido");
	}

	/* GET|PUT|DELETE /proyectos/{pid} */
	@Path("/{pid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public proyectos getProyectoUser(@PathParam("pid") int pid) {

	   /*	User unUser = new User();
		for (User user : misUsuarios) {
			if(user.getUid()==uid){
				unUser=user;
				break;
			}
		}
		*/
		
		// acceso a la Bdatos para devolver un proyecto.

		return this.Unproyecto;
	}
	
	@Path("/{pid}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message deleteProyecto(@PathParam("pid") int pid) {
		
		/*
		for (User user : misUsuarios) {
			if(user.getUid()==uid){
				misUsuarios.remove(user);
				break;
			}
		}
		*/

		return new Message("Proyecto Borrado");
	}	
	
}

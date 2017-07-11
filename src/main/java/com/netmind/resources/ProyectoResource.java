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
	
	/*
	/* GET|POST /Lista de proyectos del Usuario solicitado 
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<proyectos> getProyectosList(@PathParam("uid") int uid) {		
		
		// acceso a la clase devolver proyectos del usuario
		ProyectoDAO proPers = (ProyectoDAO) ProyectoDAOImpl.getInstance();
		this.proyectosUser = proPers.getUserProyectos(uid); 
		
		return this.proyectosUser;
	}	
	*/
	
	/* GET|PUT|DELETE /proyectos/{pid} */
	@Path("/usuario/{uid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<proyectos> getProyectosUser(@PathParam("uid") int uid) {

		ProyectoDAO proPers = (ProyectoDAO) ProyectoDAOImpl.getInstance();
		this.proyectosUser = proPers.getUserProyectos(uid); 		
		
		return this.proyectosUser;
	}
	
	/* GET  /proyectos/{pid} */
	@Path("/{pid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public proyectos getProyectoUno(@PathParam("pid") int pid) {

		ProyectoDAO proPers = (ProyectoDAO) ProyectoDAOImpl.getInstance();
		this.Unproyecto = proPers.getProyecto(pid); 		
		
		return this.Unproyecto;
	}

	
	/* POST Insertar un Proyecto */
	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message insertProyecto(proyectos nuevoProyecto) {
		boolean OkInsertP = false;
		String Mensage = "";
		
		ProyectoDAO proPers = (ProyectoDAO) ProyectoDAOImpl.getInstance();
		OkInsertP = proPers.insertProyecto(nuevoProyecto); 
		
		if(OkInsertP) {
			Mensage  = "Proyecto Insertado Correctamente";			
		}else {
			Mensage  = "Proyecto No insertado ERROR";
		}
					
		return new Message(Mensage);
	}
	
	/* PUT MODIFICAR un Proyecto */
	@Path("/")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message modifProyecto(proyectos ModifProyecto) {
		boolean OkInsertP = false;
		String Mensage = "";
		
		ProyectoDAO proPers = (ProyectoDAO) ProyectoDAOImpl.getInstance();
		OkInsertP = proPers.updateProyecto(ModifProyecto); 
		
		if(OkInsertP) {
			Mensage  = "Proyecto Insertado Correctamente";			
		}else {
			Mensage  = "Proyecto No insertado ERROR";
		}
					
		return new Message(Mensage);
	}
	
	
	@Path("/{pid}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message deleteProyecto(@PathParam("pid") int pid) {boolean OkInsertP = false;
		String Mensage = "";
	
		ProyectoDAO proPers = (ProyectoDAO) ProyectoDAOImpl.getInstance();
		OkInsertP = proPers.delProyecto(pid); 
	
		if(OkInsertP) {
			Mensage  = "Proyecto Borrado Correctamente";			
		}else {
			Mensage  = "Proyecto No BORRADO";
		}
				
	return new Message(Mensage);
	}
	
	

	
	
}

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
	public List<tareas> getTareasListUser(@PathParam("pid") int pid) {

		// acceso a la clase devolver TAREAS del usuario

		TareaDAO tarPers = (TareaDAO) TareaDAOImpl.getInstance();
		this.tareasUser = tarPers.getTareas(pid);

		return this.tareasUser;
	}

	/* POST Insertar un tarea */
	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message insertTarea(tareas nuevoOeditTarea) {

		// añadiriamos un proyecto si este id de TAREA no existe lo creamos si
		// existe lo modificamos

		TareaDAO tarPers = (TareaDAO) TareaDAOImpl.getInstance();
		this.tareasUser.add(nuevoOeditTarea);

		return new Message("Tarea Añadida");
	}

	/* GET|PUT|DELETE /tareas/{tid} */
	@Path("/{tid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public tareas getTareaUser(@PathParam("tid") int tid) {

		TareaDAO tarPers = (TareaDAO) TareaDAOImpl.getInstance();
		tareas unaTarea = new tareas(tid, tid, tid, null, null);
		for (tareas tarea : tareasUser) {
			if (tarea.getTid() == tid) {
				unaTarea = tarea;
				break;
			}
		}

		// acceso a la Bdatos para devolver una tarea.

		return this.UnaTarea;
	}

	@Path("/{tid}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateTarea(@PathParam("tid") int tid, tareas unaTarea) {

		TareaDAO tarPers = (TareaDAO) TareaDAOImpl.getInstance();
		for (tareas aTarea : tareasUser) {
			if (aTarea.getUid() == tid) {
				tareasUser.remove(aTarea);
				tareasUser.add(unaTarea);
				break;
			}
		}

		return new Message("Tareas modificadas");
	}

	@Path("/{tid}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message deleteTarea(@PathParam("tid") int tid) {

		TareaDAO tarPers = (TareaDAO) TareaDAOImpl.getInstance();
		for (tareas tarea : tareasUser) {
			if (tarea.getTid() == tid) {
				tareasUser.remove(tarea);
				break;
			}
		}

		return new Message("Tarea Borrado");
	}

}

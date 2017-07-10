package com.netmind.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.netmind.db.ProyectoDAO;
import com.netmind.db.ProyectoDAOImpl;
import com.netmind.db.TareaDAO;
import com.netmind.db.TareaDAOImpl;
import com.netmind.models.Usuario;
import com.netmind.models.proyectos;
import com.netmind.models.tareas;;

@WebServlet("/detalle_proyecto")
public class DetalleProyectosServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger("DetalleProyectosServlets");

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession misession = (HttpSession) request.getSession();

		ArrayList<tareas> lisTareasUser = new ArrayList<tareas>();
		String idProyecto = request.getParameter("pid");

		Usuario elUsuario = (Usuario) misession.getAttribute("usuario");
		ProyectoDAO pDAO = (ProyectoDAO) ProyectoDAOImpl.getInstance();
		TareaDAO tDAO = (TareaDAO) TareaDAOImpl.getInstance();

		logger.info("Entrada en Detalle Proyecto: ID PROYECTO " + idProyecto);

		if (idProyecto != null) {

			proyectos proyecto = pDAO.getProyecto(Integer.parseInt(idProyecto));

			logger.info("Entrada en Detalle Proyecto: Hay Proyecto encontrado: " + proyecto.gettitulo());

			if (proyecto != null) {

				logger.info("Entrada en Detalle Proyecto: Hay Proyecto encontrado: " + idProyecto);

				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

				List<tareas> listaTareas = tDAO.getTareas(proyecto.getPid());

				request.setAttribute("proyectoUserAMostrar", proyecto);

				request.setAttribute("listaTareasMostrar", listaTareas);

				request.getRequestDispatcher("detalle_proyecto.jsp").forward(request, response);

			} else {

				misession.invalidate();
				response.sendRedirect("login");

			}

		} else {
			logger.info("Entrada en Detalle Proyecto: PID  " + idProyecto);
			request.getRequestDispatcher("plantilla_proyecto.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

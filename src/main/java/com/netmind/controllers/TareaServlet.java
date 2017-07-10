package com.netmind.controllers;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.netmind.db.TareaDAO;
import com.netmind.db.TareaDAOImpl;
import com.netmind.db.ProyectoDAO;
import com.netmind.db.ProyectoDAOImpl;
import com.netmind.models.tareas;
import com.netmind.models.proyectos;
import com.netmind.models.Usuario;

@WebServlet("/tarea")
public class TareaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession misession = (HttpSession) request.getSession();

		if (misession.getAttribute("usuario") != null) {
			
			ProyectoDAO mDAO=(ProyectoDAO)ProyectoDAOImpl.getInstance();
			
			List<proyectos> listaProyectos = mDAO.getProyectos();
			
			request.setAttribute("listaTareasAMostrar", listaProyectos);

			request.getRequestDispatcher("tareas.jsp").forward(request, response);
		} else {
			misession.invalidate();
			response.sendRedirect("login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession misession = (HttpSession) request.getSession();		
		
		if (misession.getAttribute("usuario") != null) {

			int pid = request.getParameter("proyecto") != null
					? Integer.parseInt(request.getParameter("proyecto")) : 0;

			if (pid > 0) {

				Usuario elUsuario = (Usuario) misession.getAttribute("usuario");
				ProyectoDAO PDAO=(ProyectoDAO)ProyectoDAOImpl.getInstance();
				TareaDAO TDAO=(TareaDAO)TareaDAOImpl.getInstance();

				Calendar today = Calendar.getInstance();
				Date todayDate = today.getTime();
				
				proyectos unProy= PDAO.getProyecto(pid);
				
				int cantidad = 0;

				
				tareas nuevaTarea = new tareas((Integer) null, elUsuario.getUid() , pid, "Tarea 1" ,  todayDate);

				if (!TDAO.insertarTarea(nuevaTarea)) {
					request.setAttribute("error", "No se ha podido terminar el proceso :-(. Vuelve a intentarlo...");
					doGet(request, response);
				} else {
					request.getRequestDispatcher("lista_proyectos").forward(request, response);
				}
				
			} else {
				request.setAttribute("error", "Selecciona un cosmético e indica una cantidad igual o mayor a uno");
				doGet(request, response);
			}
		} else {
			misession.invalidate();
			response.sendRedirect("login");
		}
	}

}

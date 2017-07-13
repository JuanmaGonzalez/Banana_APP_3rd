package com.netmind.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.netmind.models.tareas;
import com.netmind.models.proyectos;

public final class TareaDAOImpl extends TareaDAO {
	private static Logger logger = Logger.getLogger("TareaDAOImpl");

	private static TareaDAOImpl instance = null;

	public static TareaDAOImpl getInstance() {
		if (instance == null) {
			instance = new TareaDAOImpl();
		}
		return instance;
	}
	
	@Override
	public tareas getUnaTarea(int tid) {		
		tareas tarea = null;		

		try {
			Connection conn = this.datasource.getConnection();
			// ordenes sql
			String sql = "SELECT t.* FROM tareas t WHERE t.tid= ? ";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, tid);

			ResultSet rs = pstm.executeQuery();
			
			if(rs.next() == true ) {
				   tarea =   new tareas(rs.getInt("tid"), rs.getInt("uid"),  rs.getInt("pid"), rs.getString("tarea"),	
						rs.getDate("fechafin"));
			}

			pstm.close();
			conn.close();

			logger.info("Conexi�n exitosa");

		} catch (Exception e) {
			logger.severe("Error en la conexi�n de BBDD:" + e);
			tarea = null;
		}

		return tarea;
	}

	@Override
	public List<tareas> getTareasProyecto(int pid) {		
		List<tareas> listTADevolver = new ArrayList<tareas>();		

		try {
			Connection conn = this.datasource.getConnection();
			// ordenes sql
			String sql = "SELECT t.* FROM tareas t WHERE t.pid= ? ";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, pid);

			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()) {
				listTADevolver.add(new tareas(rs.getInt("tid"), rs.getInt("uid"),  rs.getInt("pid"), rs.getString("tarea"),	
						rs.getDate("fechafin")));
			}

			pstm.close();
			conn.close();

			logger.info("Conexi�n exitosa");

		} catch (Exception e) {
			logger.severe("Error en la conexi�n de BBDD:" + e);
			listTADevolver = null;
		}

		return listTADevolver;
	}
	
	@Override
	public List<tareas> getTareasTodas() {		
		List<tareas> listTADevolver = new ArrayList<tareas>();		

		try {
			Connection conn = this.datasource.getConnection();
			// ordenes sql
			String sql = "SELECT t.* FROM tareas t ";
			PreparedStatement pstm = conn.prepareStatement(sql);
			//pstm.setInt(1, pid);

			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()) {
				listTADevolver.add(new tareas(rs.getInt("tid"), rs.getInt("uid"),  rs.getInt("pid"), rs.getString("tarea"),	
						rs.getDate("fechafin")));
			}

			pstm.close();
			conn.close();

			logger.info("Conexi�n exitosa");

		} catch (Exception e) {
			logger.severe("Error en la conexi�n de BBDD:" + e);
			listTADevolver = null;
		}

		return listTADevolver;
	}

	
	
	
	

	@Override
	public boolean delTarea(int tid) {
		boolean exito;
		try {

			Connection conn = this.datasource.getConnection();

			try {
				conn.setAutoCommit(false);
				
				// Borrar Proyecto uno
				String sql = "DELETE tareas WHERE tid = ? ";
				PreparedStatement pstm = conn.prepareStatement(sql);
				pstm.setInt(1, tid);				

				int rows = pstm.executeUpdate();

				pstm.close();
				
				conn.commit();

				conn.close();

				logger.info("Inserci�n exitosa");
				exito = rows > 0 ? true : false;

			} catch (Exception e) {
				conn.rollback();
				logger.severe("Transacci�n fallida:" + e.getMessage());
				exito = false;
			}

		} catch (Exception e) {
			logger.severe("Error en la conexi�n de BBDD:" + e.getMessage());
			exito = false;
		}

		return exito;
	}

	@Override
	public boolean insertarTarea(tareas nuevaTarea) {
		boolean exito = false;

		try {

			Connection conn = this.datasource.getConnection();

			try {
				conn.setAutoCommit(false);
				
				// INSERTAR EN TAREA
				String sql = "INSERT INTO tareas (tid, uid, pid, tarea, fechafin )   VALUES (NULL,? ,? ,? ,? )";
				PreparedStatement pstm = conn.prepareStatement(sql);
				pstm.setInt(1, nuevaTarea.getUid());
				pstm.setInt(2, nuevaTarea.getPid());
				pstm.setString(3, nuevaTarea.getTarea());
				pstm.setDate(4, (java.sql.Date) nuevaTarea.getFechafin());

				int rows = pstm.executeUpdate();

				pstm.close();
				
				conn.commit();

				conn.close();

				logger.info("Inserci�n exitosa");
				exito = rows > 0 ? true : false;

			} catch (Exception e) {
				conn.rollback();
				logger.severe("Transacci�n fallida:" + e.getMessage());
				exito = false;
			}

		} catch (Exception e) {
			logger.severe("Error en la conexi�n de BBDD:" + e.getMessage());
			exito = false;
		}

		return exito;
	}

	@Override
	public boolean updateTarea(tareas tarea) {
		boolean exito = false;
		
		try {

			Connection conn = this.datasource.getConnection();

			try {
				conn.setAutoCommit(false);
				
				// INSERTAR EN PROYECTO
				String sql = "UPDATE tareas SET  uid = ? , pid = ? , tarea = ? , fechafin = ? WHERE tid = ?";
				PreparedStatement pstm = conn.prepareStatement(sql);				
				pstm.setInt(1, tarea.getUid());
				pstm.setInt(2, tarea.getPid());
				pstm.setString(3, tarea.getTarea());				
				pstm.setDate(4, (java.sql.Date) tarea.getFechafin());				
				pstm.setInt(5, tarea.getTid());

				int rows = pstm.executeUpdate();

				pstm.close();
				
				conn.commit();

				conn.close();

				logger.info("Inserci�n exitosa");
				exito = rows > 0 ? true : false;

			} catch (Exception e) {
				conn.rollback();
				logger.severe("Transacci�n fallida:" + e.getMessage());
				exito = false;
			}

		} catch (Exception e) {
			logger.severe("Error en la conexi�n de BBDD:" + e.getMessage());
			exito = false;
		}

		return exito;
	}
}

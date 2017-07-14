package com.netmind.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.netmind.models.tareas;

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

			if (rs.next() == true) {
				tarea = new tareas(rs.getInt("tid"), rs.getInt("uid"), rs.getInt("pid"), rs.getString("tarea"),
						rs.getDate("fechafin"));
			}

			pstm.close();
			conn.close();

			logger.info("Conexión exitosa");

		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD:" + e);
			tarea = null;
		}

		return tarea;
	}

	
	@Override
	public List<tareas> getTareasProyecto(int pid) {

		logger.severe("Entorno HIBERNATE: acceso Lista Tareas: getTareasProyecto");

		ManageEmployee FactoryPersit = ManageEmployee.getInstance();
		Session session = FactoryPersit.factory.openSession();
		Transaction trans = session.beginTransaction();

		logger.info("Entorno HIBERNATE: ACCESO A LA SESION: Inicio Transaccion");
		
		List<tareas> listTADevolver  = (List<tareas>) session.createQuery("from tareas WHERE pid = " + pid).getResultList();

		trans.commit();
		session.close();

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
			// pstm.setInt(1, pid);

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				listTADevolver.add(new tareas(rs.getInt("tid"), rs.getInt("uid"), rs.getInt("pid"),
						rs.getString("tarea"), rs.getDate("fechafin")));
			}

			pstm.close();
			conn.close();

			logger.info("Conexión exitosa");

		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD:" + e);
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
				String sql = "DELETE FROM tareas WHERE tid = ? ";
				PreparedStatement pstm = conn.prepareStatement(sql);
				pstm.setInt(1, tid);

				int rows = pstm.executeUpdate();

				pstm.close();

				conn.commit();

				conn.close();

				logger.info("DELETE TAREA OK");
				exito = rows > 0 ? true : false;

			} catch (Exception e) {
				conn.rollback();
				logger.severe("DELETE TAREA: Transacción fallida:" + e.getMessage());
				exito = false;
			}

		} catch (Exception e) {
			logger.severe("DELETE TAREA: Error en la conexión de BBDD:" + e.getMessage());
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
				pstm.setDate(4, new java.sql.Date(nuevaTarea.getFechafin().getTime()));

				int rows = pstm.executeUpdate();

				pstm.close();

				conn.commit();

				conn.close();

				logger.info("Inserción exitosa");
				exito = rows > 0 ? true : false;

			} catch (Exception e) {
				conn.rollback();
				logger.severe("Transacción fallida:" + e.getMessage());
				exito = false;
			}

		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD:" + e.getMessage());
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
				pstm.setDate(4, new java.sql.Date(tarea.getFechafin().getTime()));
				pstm.setInt(5, tarea.getTid());

				int rows = pstm.executeUpdate();

				pstm.close();

				conn.commit();

				conn.close();

				logger.info("Inserción exitosa");
				exito = rows > 0 ? true : false;

			} catch (Exception e) {
				conn.rollback();
				logger.severe("Transacción fallida:" + e.getMessage());
				exito = false;
			}

		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD:" + e.getMessage());
			exito = false;
		}

		return exito;
	}
}

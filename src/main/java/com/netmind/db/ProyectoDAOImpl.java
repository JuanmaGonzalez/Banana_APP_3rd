package com.netmind.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.netmind.models.proyectos;

public final class ProyectoDAOImpl extends ProyectoDAO {
	private static Logger logger = Logger.getLogger("ProyectoDaoImpl");
	
	private static ProyectoDAOImpl instance = null;

	public static ProyectoDAOImpl getInstance() {
		if (instance == null) {
			instance = new ProyectoDAOImpl();
		}
		return instance;
	}
	
	@Override
	public proyectos getProyecto(int pid) {
		proyectos ProyectoADevolver = null;

		try {
			Connection conn = this.datasource.getConnection();
			// ordenes sql
			String sql = "SELECT p.* FROM proyectos p WHERE p.pid=? LIMIT 1";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, pid);

			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {
				
				ProyectoADevolver = new proyectos(rs.getInt("pid"), rs.getInt("uid"),  rs.getString("codigo"), rs.getString("titulo"),	rs.getDate("fechainicio") , rs.getDate("fechafin") , rs.getBoolean("estado"));
			}

			pstm.close();
			conn.close();

			logger.info("Conexión exitosa");

		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD:" + e);
			ProyectoADevolver = null;
		}

		return ProyectoADevolver;
	}

	@Override
	public boolean delProyecto(int pid) {
		boolean exito = false;
		
		try {

			Connection conn = this.datasource.getConnection();

			try {
				conn.setAutoCommit(false);
				
				// Borrar Proyecto uno
				String sql = "DELETE proyectos WHERE pid = ? ";
				PreparedStatement pstm = conn.prepareStatement(sql);
				pstm.setInt(1, pid);				

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

	
     /* Prueba de actualizacion */
	@Override
	public boolean updateProyecto(proyectos proyecto) {		
		boolean exito = false;
		
		try {

			Connection conn = this.datasource.getConnection();

			try {
				conn.setAutoCommit(false);
				
				// INSERTAR EN PROYECTO
				String sql = "UPDATE proyectos SET  uid = ? , codigo = ? , titulo = ? , fechainicio = ? , fechafin = ? , estado = ? WHERE pid = ?";
				PreparedStatement pstm = conn.prepareStatement(sql);
				//pstm.setInt(1, proyecto.getPid());
				pstm.setInt(1, proyecto.getUid());
				pstm.setString(2, proyecto.getCodigo());
				pstm.setString(3, proyecto.gettitulo());
				pstm.setDate(4, (java.sql.Date) proyecto.getFechainicio());
				pstm.setDate(5, (java.sql.Date) proyecto.getFechafin());				
				pstm.setBoolean(6, proyecto.getestado());
				pstm.setInt(7, proyecto.getPid());

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
	public List<proyectos> getUserProyectos(int uid) {
		List<proyectos> listADevolver = new ArrayList<proyectos>();

		try {
			
			Connection conn = this.datasource.getConnection();

			// ordenes sql
			String sql = "SELECT p.* FROM proyectos p INNER JOIN tareas t ON t.tid = p.pid WHERE p.uid = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, uid);

			ResultSet rs = pstm.executeQuery();			
			
			while (rs.next()) {
				listADevolver.add(new proyectos(rs.getInt("pid"), rs.getInt("uid"),  rs.getString("codigo"), rs.getString("titulo"),	
						rs.getDate("fechainicio") , rs.getDate("fechafin") , rs.getBoolean("estado")));
			}

			pstm.close();

			conn.close();

			logger.info("Conexión exitosa");

		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD:" + e);
			listADevolver = null;
		}

		return listADevolver;
	}

	@Override
	public List<proyectos> getProyectos() {
		List<proyectos> listADevolver = new ArrayList<proyectos>();

		try {
			Connection conn = this.datasource.getConnection();

			// ordenes sql
			String sql = "SELECT p.* FROM proyectos p WHERE 1";
			PreparedStatement pstm = conn.prepareStatement(sql);

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				listADevolver.add(new proyectos(rs.getInt("pid"), rs.getInt("uid"),  rs.getString("codigo"), rs.getString("titulo"),	rs.getDate("fechainicio") , rs.getDate("fechafin") , rs.getBoolean("estado")));
			}

			pstm.close();
			conn.close();

			logger.info("Conexión exitosa");

		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD:" + e);
			listADevolver = null;
		}

		return listADevolver;
	}

		

	@Override
	public boolean insertProyecto(proyectos proyecto) {
		boolean exito = false;
		
		logger.info("ENTRADA EN ( InsertProyecto:");
		
		try {

			Connection conn = this.datasource.getConnection();

			try {
				conn.setAutoCommit(false);
				
				// INSERTAR EN PROYECTO
				String sql = "INSERT INTO proyectos ( pid, uid, codigo, titulo, fechainicio, fechafin, estado ) VALUES(NULL, ?, ?, ?, ?, ? ,? )";
				PreparedStatement pstm = conn.prepareStatement(sql);
				//pstm.setInt(1, proyecto.getPid());
				pstm.setInt(1, proyecto.getUid());
				pstm.setString(2, proyecto.getCodigo());
				pstm.setString(3, proyecto.gettitulo());
				pstm.setDate(4, (java.sql.Date) proyecto.getFechainicio());
				pstm.setDate(5, (java.sql.Date) proyecto.getFechafin());				
				pstm.setBoolean(6, proyecto.getestado());

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

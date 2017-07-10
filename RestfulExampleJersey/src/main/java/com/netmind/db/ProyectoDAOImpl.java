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
	public boolean delProyecto(int mid) {
		// TODO Auto-generated method stub
		return false;
	}

	

	@Override
	public boolean updateProyecto(proyectos proyecto) {
		// TODO Auto-generated method stub
		return false;
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
	public boolean insertProyecto(proyectos cosmetico) {
		// TODO Auto-generated method stub
		return false;
	}


	

}

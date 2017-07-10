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
	public List<tareas> getTareas(int pid) {		
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

			logger.info("Conexión exitosa");

		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD:" + e);
			listTADevolver = null;
		}

		return listTADevolver;
	}

	@Override
	public boolean delTarea(int mid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertarTarea(tareas nuevaTarea) {
		boolean exito = false;

		try {

			Connection conn = this.datasource.getConnection();

			try {
				conn.setAutoCommit(false);
				
				// INSERTAR EN TAREA
				String sql = "INSERT INTO tareas VALUES(NULL,?,?,?,?)";
				PreparedStatement pstm = conn.prepareStatement(sql);
				pstm.setInt(1, nuevaTarea.getUid());
				pstm.setInt(2, nuevaTarea.getPid());
				pstm.setString(3, nuevaTarea.getTarea());
				pstm.setDate(4, (java.sql.Date) nuevaTarea.getFechafin());

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
	public boolean updateTarea(tareas compra) {
		// TODO Auto-generated method stub
		return false;
	}
}

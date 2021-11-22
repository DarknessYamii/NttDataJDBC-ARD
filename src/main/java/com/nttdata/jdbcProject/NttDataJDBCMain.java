package com.nttdata.jdbcProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class NttDataJDBCMain {
	public static void main(String[] args) {
		stablishConnection(); 
	
	}

	private static void stablishConnection() {
		try {
			// BBDD Connection
			Class.forName("com.mysql.cj.jdbc.Driver");
			final Connection jdbcCon = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/nttdata_javajdbc", "root", "root");
			
			// Consulta
			final Statement sentence = jdbcCon.createStatement();
			final String query = "SELECT sp.name AS playerName,sp.first_rol AS rol1, sp.second_rol AS rol2 ,st.name AS teamName, st.stadium AS stadiumTeam FROM nttdata_mysql_soccer_player sp JOIN nttdata_mysql_soccer_team st ON sp.id_soccer_team = st.id_soccer_team";
			final ResultSet queryResult = sentence.executeQuery(query);
			
			// String builder. Organization of parameters in text
			StringBuilder builder = new StringBuilder();
			while(queryResult.next()) {
				builder.append("Nombre: ");
				builder.append(queryResult.getString("playerName"));
				builder.append(" | Rol Principal: ");
				builder.append(queryResult.getString("rol1"));
				builder.append(" | Rol Secundario: ");
				builder.append(queryResult.getString("rol2"));
				builder.append(" | Equipo: ");
				builder.append(queryResult.getString("teamName"));
				builder.append(" | Estadio: ");
				builder.append(queryResult.getString("stadiumTeam"));
				builder.append("\n");
			}
			System.out.println(builder.toString());
			// Error control
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("[ERROR] - Error en la conexion a la BBDD");
		}
	}

}

package dataBase;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.sql.Statement;



public class JDBC{
	final String db = "COWTEK";
	final String dbmsurl = "jdbc:mysql://cowtek.mooo.com:3306/"+db;
	//final String dbmsurl = "jdbc:mysql://modra.olaldiko.mooo.com:3306";
	
	String userName;
	String password;
	Statement stmt = null;
	ResultSet result = null;
	Connection conn = null;
	
	public JDBC(){
		this.userName = "gorkaram";
		this.password = "narvaja";
	}

	public ResultSet getStatements(){
		
		try{
			stmt = conn.createStatement();
			
		} catch (SQLException e) {
			System.out.println("Error en la statement:");
			System.out.println (e.getMessage());
		}
		return result;
		
	}
	
	public Connection getConnection() throws SQLException{
		
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);
		
		conn = DriverManager.getConnection(dbmsurl, connectionProps);
		return conn;
	}
	
	public int ejecutarUpdate(String sql) {
		int rowsAffected = 0;
		try {
			conn=getConnection();
			getStatements();
			rowsAffected = stmt.executeUpdate(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();										
		}finally{
			if (stmt!=null){
				try {
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					System.out.println (e.getMessage());
				}
			}
		}
		return rowsAffected;
		
	}

	public List<PlacaEstacion> getPlacasEstaciones() {
		
		String sql = "SELECT * FROM placaEstacion";
		List<PlacaEstacion> placasEstaciones = new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()){			
				int estacionID = result.getInt("EstacionID");
				int placaID = result.getInt("PlacaID");
				Date fechaInicio = result.getDate ("FechaInicio");
				Date fechaFinal = result.getDate ("FechaFinal");
				
				placasEstaciones.add(new PlacaEstacion(estacionID, placaID, fechaInicio, fechaFinal));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return placasEstaciones;
	}

	public List<PlacaVaca> getPlacasVacas() {
		
		String sql = "SELECT * FROM placaVaca";
		List<PlacaVaca> placasVacas = new ArrayList<>();
		
		try {
			conn=getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()){		
				
				int vacaID = result.getInt("VacaID");
				int placaID = result.getInt("PlacaID");
				Date fechaInicio = result.getDate ("FechaInicio");
				Date fechaFinal = result.getDate ("FechaFinal");
				
				placasVacas.add(new PlacaVaca(vacaID, placaID, fechaInicio, fechaFinal));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return placasVacas;
	}

}






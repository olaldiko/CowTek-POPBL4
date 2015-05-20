package JDBC;


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
	final String db = "cowtek";
	final String dbmsurl = "jdbc:mysql://localhost:3306/"+db;
	
	String userName, password;
	Statement stmt = null;
	ResultSet result = null;
	Connection conn = null;
	
	public JDBC(String username, String password){
		this.userName = username;
		this.password = password;
	}
	
	public void updatedatabase (int opcion){
		opcion = 1;
		System.out.println("opcion: "+ opcion);
		switch (opcion){
		case 1:
			//addVaca(1065, "homer", new SimpleDateFormat("2001-10-09"));
			
				
			break;
		case 2:
			
			break;
		}
	}

	public void addRandomTemperature() throws SQLException {
		int i = 10, value=50;
		String sql;

		conn=getConnection();
		
		sql = "DELETE	FROM	sensor " +
				"WHERE	(sensorid <= 100)";
		ejecutarUpdate(sql);
		
		for (i=0; i<100; i++){
			value = (int)Math.floor(25+Math.random()*(38-25+1));
			System.out.println(value);
			
			sql = "INSERT INTO SENSOR (SENSORID, VALOR) " +
				 "VALUES ("+i+", "+value+")";
			
			
			ejecutarUpdate(sql);			
			
		}
		conn.close();
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
	
	public List<Double> readResultTemperatures() throws SQLException{
		
		conn=getConnection();
		
		List<Double> Temperatures = new ArrayList<>();
		String sql ="SELECT * FROM SENSOR";
		try {
			getStatements();
			ResultSet result = stmt.executeQuery(sql);
			result.first();
			while (result.next()){
				Temperatures.add((double)result.getInt("valor"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn.close();
		return Temperatures;
		
	}
	
	public void readResultVacas() throws SQLException{

		conn=getConnection();
		
		String sql ="SELECT * FROM VACA";
		try {
			getStatements();
			ResultSet result = stmt.executeQuery(sql);
			result.first();
			while (result.next()){
				int codigo = result.getInt("vacaid");
				String name = result.getString("nombre");	
				Date fecha = result.getDate("jaiodata");
				
				System.out.println(codigo);
				System.out.println(name);
				System.out.println(fecha);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		conn.close();
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
}






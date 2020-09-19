package prueba.catedra.project.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import prueba.catedra.project.model.*;

public class usuarioPersistence {
	
	private static final String conexion = "jdbc:mysql://www.db4free.net:3306/catedraprueba?user=jimmycatedra&password=jimmy970126";
	
	private Connection con;
	
	private usuario usuario;
	
	/**
	 * Crea la conexión a la base de datos
	 */
	public void getConection(){
		try{
			con = DriverManager.getConnection(conexion);
		}catch(SQLException e){
			e.getMessage();
		}
	}
	
	/**
	 * Consulta todos los empleados y retorna una lista con usuarios
	 * @return
	 */
	public List<usuario> getALlUsers(){
		PreparedStatement pstmt = null;
		List<usuario> allUsers = new ArrayList();
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			getConection();
			con.setAutoCommit(false);
			String consulta = "select * from persona";
			//Crea la sentencia SQL
			pstmt = con.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			//Ejecuta la sentencia SQL
			ResultSet resultado = pstmt.executeQuery();			
			while(resultado.next()){
				usuario = new usuario(resultado.getInt("cedula"),resultado.getString("nombre"),resultado.getString("apellido"),
						resultado.getString("correo"),resultado.getString("telefono"));
				allUsers.add(usuario);
			}
			con.close();
			pstmt.close();
			resultado.close();
			return allUsers;
			
		}catch(Exception e){
			Logger.getLogger(usuarioPersistence.class.getName()).log(Level.SEVERE, null, e);
			return null;
		}
		
	}
	
	/**
	 * consulta un usuario en especifico
	 * @param cedula
	 * @return
	 */
	public usuario getUser(int cedula){
		PreparedStatement pstmt = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			getConection();
			con.setAutoCommit(false);
			String consulta = "select * from persona where cedula=?";
			//Crea la sentencia SQL
			pstmt = con.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, cedula);
			//Ejecuta la sentencia SQL
			ResultSet resultado = pstmt.executeQuery();		
			
			if(resultado.next()){
				usuario = new usuario(resultado.getInt("cedula"),resultado.getString("nombre"),resultado.getString("apellido"),
						resultado.getString("correo"),resultado.getString("telefono"));
				
			}
			con.close();
			pstmt.close();
			resultado.close();
			return usuario;
			
		}catch(Exception e){
			Logger.getLogger(usuarioPersistence.class.getName()).log(Level.SEVERE, null, e);
			return null;
		}
	}
	
	/**
	 * Crea un nuevo usuario
	 * @param user
	 */
	public void createUser(usuario user){
		Statement stmt = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			getConection();
			con.setAutoCommit(false);
			stmt = con.createStatement();
			String peticion = "INSERT into persona VALUES ("+ user.getCedula() + "," + user.getNombre() +","+ user.getApellido()
					+ "," + user.getCorreo() +"," + user.getTelefono() +")";
			stmt.execute(peticion);
			stmt.close();
			con.commit();
			con.close();
		}
		catch(Exception ex){
			Logger.getLogger(usuarioPersistence.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	/**
	 * Actualiza un usuario
	 * @param user
	 */
	public void updateUser(usuario user){
		
		Statement stmt = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			getConection();
			con.setAutoCommit(false);
			stmt = con.createStatement();
			String peticion = "update persona set nombre='"+user.getNombre()+"',apellido='" + user.getApellido()
					+"',correo='"+user.getCorreo()+"',telefono='"+user.getTelefono()+"' where cedula='"+user.getCedula()+"'";
			stmt.execute(peticion);
			stmt.close();
			con.commit();
			con.close();
		}
		
		
		catch(Exception ex){
			Logger.getLogger(usuarioPersistence.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}
	
	
	/**
	 * Elimina un usuario en especifico
	 * @param cedula
	 */
	public void deleteUser(int cedula){
		Statement stmt = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			getConection();
			con.setAutoCommit(false);
			stmt = con.createStatement();
			String peticion = "delete from persona where cedula='"+cedula+"'";
			stmt.execute(peticion);
			System.out.println("fff: " + stmt.execute(peticion));
			stmt.close();
			con.commit();
			con.close();
		}
		catch(Exception ex){
			Logger.getLogger(usuarioPersistence.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	

}

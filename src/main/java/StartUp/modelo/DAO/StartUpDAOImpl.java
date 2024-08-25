package StartUp.modelo.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import StartUp.conexion.Conexion;
import StartUp.modelo.Proveedores;
import StartUp.modelo.RolesUsuarios;
import StartUp.modelo.Usuarios;

public class StartUpDAOImpl implements IStartUpDAO{
	
	private Connection conn = null;
	
	@Override
	public void agregarUsuario(Usuarios usuarios) {
		Statement stm = null;
		
		Usuarios user = usuarios;
		
		String correo = user.getCorreo();
		String nick = user.getNick();
		String nombre = user.getNombre();
		String contrasena = user.getPassword();
		int peso = user.getPeso();
		
		String sql = "INSERT INTO usuarios (correo, created_at, nick, nombre, contrasena, peso, updated_at) VALUES ('"+correo+"', Now(),'"+nick+"','"
				+nombre+ "','"+contrasena+"','"+peso+"', Now());";
		
		
		try {
			conn = Conexion.getConnection();
			System.out.println("desde DAO: "+ conn);
			
			stm = conn.createStatement();
			
			stm.executeQuery(sql);
			
			stm.close();
			//conn.close();
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public Usuarios obtenerUsuarioPorCorreo (String correo) {
		Statement stm = null;
		ResultSet rs = null;
		
		String userCorreo = correo;
		
		String sql = "SELECT id, correo, created_at, nick, nombre, contrasena, peso, updated_at FROM usuarios WHERE correo = '"+userCorreo+"'";
		
		Usuarios usuario = null;
		
		try {
			conn = Conexion.getConnection();
			System.out.println("desde DAO: "+ conn);
			
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			System.out.println(rs);
			
			if (rs != null) {
				while (rs.next()) {
					usuario = new Usuarios(rs.getInt("id"), 
											rs.getString("correo"),
											rs.getString("created_at"),
											rs.getString("nick"),
											rs.getString("nombre"),
											rs.getString("contrasena"),
											rs.getInt("peso"),
											rs.getString("updated_at"));
				}
				if (usuario != null) {
					sql = "UPDATE usuarios SET updated_at = Now() WHERE id = '"+usuario.getId()+"'";
					stm.executeQuery(sql);
				}
			}

			rs.close();
			stm.close();
			//conn.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return usuario;
		
	}
	
	@Override
	public List<Proveedores> obtenerTodosLosProveedores() {		
		
		Statement stm = null;
		ResultSet rs = null;
		
		String sql = "select id, nombre, rut, direccion, correo, telefono, contacto, telefono_contacto from proveedores ORDER BY id ASC;";
		
		List<Proveedores> proveedor = new ArrayList<Proveedores>();
		
		try {
			conn = Conexion.getConnection();
			System.out.println("desde DAO: "+ conn);
			
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			
			while (rs.next()) {
				proveedor.add(new Proveedores(rs.getInt("id"), 
										rs.getString("nombre"),
										rs.getString("rut"),
										rs.getString("direccion"),
										rs.getString("correo"),
										rs.getString("telefono"),
										rs.getString("contacto"),
										rs.getString("telefono_contacto")));
			}
			rs.close();
			stm.close();
			//conn.close();
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		

		return proveedor;
	}
	
	public void agregarRol(int id) {
		Statement stm = null;
		
		int uId = id;
		
		String sql = "INSERT INTO roles_usuarios (usuario_id) VALUES ('"+uId+"');"; 
		
		try {
			conn = Conexion.getConnection();
			System.out.println("desde DAO: "+ conn);
			
			stm = conn.createStatement();
			
			stm.executeQuery(sql);
			
			stm.close();
			//conn.close();
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public int obtenerRolPorId(int id) {
		
		Statement stm = null;
		ResultSet rs = null;
		
		int uId = id;
		ArrayList<RolesUsuarios> roles = new ArrayList<RolesUsuarios>(); 
		
		String sql = "SELECT usuario_id, rol_id FROM roles_usuarios WHERE usuario_id = "+uId+";";
		
		try {
			conn = Conexion.getConnection();
			System.out.println("desde DAO: "+ conn);
			
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			
			while (rs.next()) {
				roles.add(new RolesUsuarios(rs.getInt("usuario_id"), rs.getInt("rol_id"))) ;
			}
			
			rs.close();
			stm.close();
			//conn.close();
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		int rolId = roles.get(0).getRol_id() ;
		
		return rolId;
	}
}

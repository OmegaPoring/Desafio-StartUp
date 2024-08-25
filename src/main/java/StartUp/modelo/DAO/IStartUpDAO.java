package StartUp.modelo.DAO;

import java.util.List;

import StartUp.modelo.Proveedores;
import StartUp.modelo.Usuarios;

public interface IStartUpDAO {
	
	public void agregarUsuario(Usuarios usuarios);
	public Usuarios obtenerUsuarioPorCorreo (String correo);
	public List<Proveedores> obtenerTodosLosProveedores();
	public void agregarRol(int id);
	public int obtenerRolPorId(int id);
	
}

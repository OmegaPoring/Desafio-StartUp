package StartUp.modelo;

public class RolesUsuarios {
	private int usuarios_id;
	private int rol_id;
	
	public RolesUsuarios(int usuarios_id, int rol_id) {
		super();
		this.usuarios_id = usuarios_id;
		this.rol_id = rol_id;
	}

	public int getUsuarios_id() {
		return usuarios_id;
	}

	public void setUsuarios_id(int usuarios_id) {
		this.usuarios_id = usuarios_id;
	}

	public int getRol_id() {
		return rol_id;
	}

	public void setRol_id(int rol_id) {
		this.rol_id = rol_id;
	}
}

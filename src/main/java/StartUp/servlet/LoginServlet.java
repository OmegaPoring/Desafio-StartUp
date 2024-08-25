package StartUp.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import StartUp.encriptacion.EncriptacionPass;
import StartUp.modelo.Usuarios;
import StartUp.modelo.Proveedores;
import StartUp.modelo.DAO.StartUpDAOImpl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String correo = request.getParameter("correo");
		correo = correo.toLowerCase();
		String contrasena = null;
		try {
			contrasena = EncriptacionPass.toHexString(EncriptacionPass.getSHA(request.getParameter("contrasena")));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StartUpDAOImpl suDAO = new StartUpDAOImpl();
		Usuarios user = null;
		int rolId = 0;
		try {
			user = suDAO.obtenerUsuarioPorCorreo(correo);
			rolId = suDAO.obtenerRolPorId(user.getId());
		}catch(Exception e) {
			request.setAttribute("mensaje", "El usuario no existe");
			getServletContext().getRequestDispatcher("/views/login.jsp").forward(request, response);
		}
		
		List<Proveedores> proveedor = suDAO.obtenerTodosLosProveedores();
		
			try {
				
					if (contrasena.equals(user.getPassword()) && rolId == 1) {
						request.setAttribute("usuario", user);
						request.setAttribute("proveedor", proveedor);
						getServletContext().getRequestDispatcher("/views/home.jsp").forward(request, response);
					}else if (contrasena.equals(user.getPassword()) && rolId ==2) {
						request.setAttribute("mensaje", "No cuentas con los permisos necesarios para acceder");
						getServletContext().getRequestDispatcher("/views/login.jsp").forward(request, response);
					}else {
						request.setAttribute("mensaje", "Usuario o contraseña incorrecta");
						getServletContext().getRequestDispatcher("/views/login.jsp").forward(request, response);
					}
			}catch(Exception e) {
				getServletContext().getRequestDispatcher("/views/login.jsp").forward(request, response);
				e.printStackTrace();
			}
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

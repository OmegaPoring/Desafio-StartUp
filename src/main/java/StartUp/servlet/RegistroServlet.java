package StartUp.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import StartUp.encriptacion.EncriptacionPass;
import StartUp.modelo.Usuarios;
import StartUp.modelo.DAO.StartUpDAOImpl;

/**
 * Servlet implementation class RegistroServlet
 */
@WebServlet("/RegistroServlet")
public class RegistroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistroServlet() {
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
		String nick = request.getParameter("nick");
		String nombre = request.getParameter("nombre");
		String contrasena = null;
		try {
			contrasena = EncriptacionPass.toHexString(EncriptacionPass.getSHA(request.getParameter("contrasena"))); //encriptado de contraseña
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int peso = Integer.parseInt(request.getParameter("peso"));
		StartUpDAOImpl suDAO = new StartUpDAOImpl();
		Usuarios user = suDAO.obtenerUsuarioPorCorreo(correo);
		if(user==null) {
			Usuarios usuario = new Usuarios(0, correo, null, nick, nombre, contrasena, peso, null);
			
			suDAO.agregarUsuario(usuario);
			user = suDAO.obtenerUsuarioPorCorreo(correo);
			int id = user.getId();
			suDAO.agregarRol(id);
			
			request.setAttribute("mensaje", "Cuenta registrada exitosamente!");
			getServletContext().getRequestDispatcher("/views/login.jsp").forward(request, response);
		}else {
			request.setAttribute("mensaje", "El correo ya está siendo utilizado por otra cuenta");
			getServletContext().getRequestDispatcher("/views/registro.jsp").forward(request, response);
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

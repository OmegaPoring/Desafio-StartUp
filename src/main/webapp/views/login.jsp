<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="StartUp.modelo.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registro</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<% String mensaje = (String) request.getAttribute("mensaje"); %>
<body>
	<section>
		<div class="text-center my-5">
			<h1>Registrarse en StartUp Project</h1>
			<p>Para continuar debe ingresar todos los campos.</p>
			<hr/>
		</div>
		<div class="d-flex justify-content-center">
			<div class="d-grid col-4">
				<form action="LoginServlet" method="post">
					<label class="form-label"
					for="nombre">Correo:</label>
					<input type="email" class="form-control" id="correo" name="correo" required><br>
					
					<label class="form-label"
					for="nombre">Contraseña:</label>
					<input type="password" class="form-control" id="contrasena" name="contrasena" required><br>
					<div id="liveAlertPlaceholder"></div>
										
					<input type="submit" class="btn btn-primary" value="Ingresar"><br>
					<a href="Signup">No tengo una cuenta, registrarme.</a><br>
					<a href="Inicio">Volver.</a>
					<div id="liveAlertPlaceholder"></div>
				</form>
			</div>
		</div>
	</section>
<script>if ("<%=mensaje%>" == "No cuentas con los permisos necesarios para acceder"){
				alert("<%=mensaje%>");
				}else if("<%=mensaje%>" == "El usuario no existe"){
					alert("<%=mensaje%>");
				}else if("<%=mensaje%>" == "Usuario o contraseña incorrecta"){
					alert("<%=mensaje%>");
				}else if("<%=mensaje%>" == "Cuenta registrada exitosamente!"){
					alert("<%=mensaje%>");
				}
			
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
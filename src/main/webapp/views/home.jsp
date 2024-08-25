<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="StartUp.modelo.*"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<% Usuarios u = (Usuarios) request.getAttribute("usuario"); %>
<head>
<meta charset="UTF-8">
<title><%=u.getNick() %></title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
	<section>
		<div class="text-center my-5">
			<h1>Bienvenid@ <%=u.getNombre() %></h1>
			<p>Actualmente está registrado con su correo <%=u.getCorreo() %></p>
			<hr/>
		</div>
		
		<div class="container">
		
		<section>
			<h1>Proveedores</h1>
			
			<table class="table">
				<thead class="table-dark">
					<tr>
						<th>Id</th>
						<th>Nombre</th>
						<th>Rut</th>
						<th>Direccion</th>
						<th>Correo</th>
						<th>Telefono</th>
						<th>Contacto</th>
						<th>Telefono de Contacto</th>
					</tr>			
				</thead>
				<tbody>
				
					<%
					
					ArrayList<Proveedores> proveedor = (ArrayList<Proveedores>) request.getAttribute("proveedor");
					if (proveedor == null){
					%>
					<h2>No se han encontrado proveedores</h2>
					<%
					}else{
					for(Proveedores p : proveedor){
						%>
							<tr>
								<td><%=p.getId() %></td>
								<td><%=p.getNombre() %></td>
								<td><%=p.getRut()  %></td>
								<td><%=p.getDireccion()  %></td>
								<td><%=p.getCorreo()  %></td>
								<td><%=p.getTelefono()  %></td>
								<td><%=p.getContacto()  %></td>
								<td><%=p.getTelefonoContacto()  %></td>
							
							</tr>
						<%
					}
				}
					%>			
				</tbody>		
			</table>		
			
			<a href="Inicio" class="btn btn-primary">Inicio</a><br>
		</section>
	</div>
	</section>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
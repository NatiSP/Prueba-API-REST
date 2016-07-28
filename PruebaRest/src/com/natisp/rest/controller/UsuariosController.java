package com.natisp.rest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;

import com.natisp.rest.dao.UsuarioDAO;
import com.natisp.rest.model.Persona;

//PruebaRest/usuario
@Path("/usuario")
public class UsuariosController {

	private static final UsuarioDAO dao = new UsuarioDAO();

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/hello")
	// PruebaRest/usuario/hello?user=x
	public String hiUsuarioText(@QueryParam("user") String user) {
		if (user == null || user.equals("")) {
			return "Hello user";
		}
		return "Hello " + user;
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON/*, MediaType.APPLICATION_XML*/})
	// PruebaRest/usuario?id=x
	public List<Persona> getUsuarios(@QueryParam("id") String user, @QueryParam("size") int size, @QueryParam("page") int page) {
		if(user == null){
			return dao.getListaUsuarios(size, page);
		}
		
		List<Persona> personas = new ArrayList<Persona>();
		personas.add(dao.getUsuario(Integer.parseInt(user)));
		return personas;
	}

	@POST
	@Path("/add")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response putUser(Persona user) {
		Response response;
		try {
//			Usuario u = user.getValue();
			dao.setUsuario(user);
			response = Response.ok().build();
		} catch (Exception ex) {
			response = Response.serverError().build();
		}
		return response;
	}
}

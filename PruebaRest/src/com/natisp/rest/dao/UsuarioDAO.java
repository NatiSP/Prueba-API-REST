package com.natisp.rest.dao;

import java.util.ArrayList;
import com.natisp.rest.model.Persona;

// Esta clase realizaria las funciones de DAO en caso de tener un acceso a BD
// En este ejemplo, para mayor simpleza, se usará como una clase Singleton que almacenará una serie de objetos de tipo Usuario.
public class UsuarioDAO {

	private ArrayList<Persona> listaUsuarios;
	
//	private static final String PERSISTENCE_UNIT_NAME = "PruebaRest";
//	EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
//
//	public EntityManager getEntityManager() {
//	    return factory.createEntityManager();
//	} 
	
	public UsuarioDAO(){
		listaUsuarios = new ArrayList<Persona>();
		for(int i = 0; i < 20; i++){
			Persona p = new Persona();
			p.setId(i);
			p.setName("Persona " + i);
			listaUsuarios.add(p);
		}
	}
	
	public ArrayList<Persona> getListaUsuarios(int size, int page){
		if(size == 0){
			size = 10;
		}
		if(page == 0){
			page = 0;
		}
		ArrayList<Persona> resultado = new ArrayList<Persona>();
		for(int i = page*size; i < size*(page+1); i++){
			resultado.add(listaUsuarios.get(i));
		}
		return resultado;
	}
	
	public Persona getUsuario(int index){
//		Query q = getEntityManager().createQuery("select t from Persona t where id = :index");
//		q.setParameter("index", index);
//		List results = q.getResultList();
//		return (Persona) results.get(0);
		for(int i = 0; i < listaUsuarios.size(); i++){
			if(listaUsuarios.get(i).getId() == index){
				return listaUsuarios.get(i);
			}
		}
		return new Persona();
	}
	
	public void setUsuario(int idUser, String usuario){
		Persona user = new Persona();
		user.setName(usuario);
		user.setId(idUser);
//		getEntityManager().persist(user);
//		getEntityManager().flush();
		listaUsuarios.add(user);
	}
	
	public void setUsuario(String usuario){
		Persona user = new Persona();
		user.setName(usuario);
//		getEntityManager().persist(user);
//		getEntityManager().flush();
		user.setId(listaUsuarios.size());
		listaUsuarios.add(user);
	}
	
	public void setUsuario(Persona user){
//		getEntityManager().persist(user);
//		getEntityManager().flush();
		listaUsuarios.add(user);
	}
}

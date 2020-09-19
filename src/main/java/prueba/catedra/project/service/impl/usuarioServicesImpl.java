package prueba.catedra.project.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import prueba.catedra.project.model.usuario;
import prueba.catedra.project.persistence.*;
import prueba.catedra.project.service.*;

@Service
public class usuarioServicesImpl implements usuarioService{
	
	usuarioPersistence usuarioPersistence;
	
	/**
	 * Crea una buena conexión a la base de datos
	 */
	public void crearConection(){
		usuarioPersistence = new usuarioPersistence();
	}

	@Override
	public List<usuario> getAllUsers() {
		crearConection();
		return usuarioPersistence.getALlUsers();
	}

	@Override
	public usuario getUser(int cedula) {
		System.out.println("Entroooooooooo  useeee");
		crearConection();
		return usuarioPersistence.getUser(cedula);
	}

	@Override
	public void updateUser(usuario user) {
		crearConection();
		usuarioPersistence.updateUser(user);		
	}

	@Override
	public void deleteUser(int cedula) {
		crearConection();		
		usuarioPersistence.deleteUser(cedula);		
	}

	@Override
	public void saveUser(usuario user) {
		crearConection();
		
		usuarioPersistence.createUser(user);		
	}

}

package prueba.catedra.project.service;

import java.util.List;

import prueba.catedra.project.model.*;

public interface usuarioService {
	
	List<usuario> getAllUsers();
	
	usuario getUser(int cedula);
	
	void updateUser(usuario user);
	
	void deleteUser(int cedula);
	
	void saveUser(usuario user);

}

package prueba.catedra.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import prueba.catedra.project.model.usuario;
import prueba.catedra.project.service.usuarioService;

@RestController
@RequestMapping(value="/")
public class usuarioController {
	
	@Autowired
	usuarioService userService;
	
	//Devuelve todos los usuarios 
	@RequestMapping(method=RequestMethod.GET,path="/all")
	public ResponseEntity<List<usuario>> allUsers(){
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	//Devuelve un usuario respecto a la cedula
	@RequestMapping(method=RequestMethod.GET,path="get/{id}")
	public ResponseEntity<usuario> getUsers(@PathVariable("id") int id){
		return ResponseEntity.ok(userService.getUser(id));
	}
	
	//Crea un nuevo usuario
	@RequestMapping(method=RequestMethod.POST,path="/save")
	public void saveUser(@RequestBody usuario user){
		userService.saveUser(user);
	}
	
	//Actualiza un usuario
	@RequestMapping(method=RequestMethod.PUT,path="/update")
	public void updateUser(@RequestBody usuario user){
		userService.updateUser(user);
	}
	
	@RequestMapping(method={RequestMethod.DELETE,RequestMethod.GET},path="/delete/{id}")
	public void deleteUser(@PathVariable("id") int id){
		userService.deleteUser(id);
	}
	
	

}

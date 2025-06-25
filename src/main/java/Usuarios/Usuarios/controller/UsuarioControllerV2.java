package Usuarios.Usuarios.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Usuarios.Usuarios.assemblers.UserModelAssembler;
import Usuarios.Usuarios.modelo.UserModel;
import Usuarios.Usuarios.service.UserService;
import io.micrometer.core.ipc.http.HttpSender.Response;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;



@RestController
@RequestMapping("/api/v2/usuarios")
public class UsuarioControllerV2 {

    @Autowired
    private UserService userService;

    @Autowired
    private UserModelAssembler assembler;

    @GetMapping (produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<UserModel>> listar(){
        List<EntityModel<UserModel>> users = userService.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(users, linkTo(methodOn(UsuarioControllerV2.class).listar()).withSelfRel());
    }

    @PostMapping
    public ResponseEntity<UserModel> guardar (@RequestBody UserModel user){
        UserModel nuevoUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUser);
    }

    //Getting users info
    @GetMapping("/rut/{rut}")
    public ResponseEntity<UserModel> buscar(@PathVariable String rut){
        try {
            UserModel user = userService.findById(rut);
            return ResponseEntity.ok(user);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/usersnombre/{nombre}")
    public ResponseEntity<Object> buscarNombres(@PathVariable String nombre){
        List<UserModel> user = userService.findByNombre(nombre);
        if (user.isEmpty()) return new ResponseEntity<Object>("No se encontro el usuario " + nombre ,HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/correo/{correo}")
    public ResponseEntity<UserModel> buscarCorreo(@PathVariable String correo){
        try {
            UserModel user = userService.findByCorreo(correo);
            return ResponseEntity.ok(user);
        } catch (Exception e) {

            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/usersinfo/{nombre}/{apaterno}")
    public ResponseEntity<Object> encontrarUsuarios(@PathVariable String nombre,@PathVariable String apaterno){
            List<UserModel> user = userService.findByNombreAndApaterno(nombre, apaterno);
            if(user.isEmpty()) return new ResponseEntity<Object>("No se a encontrado usuarios con nombre " + nombre + " y apellido "+ apaterno, HttpStatus.NOT_FOUND);
            return ResponseEntity.ok(user);
        }

    @GetMapping("/tarifas/{tarifas}")
    public ResponseEntity<Object> encontrarTarifas(@PathVariable int tarifa){
        List<UserModel> user = userService.findByTarifa(tarifa);
        if(user.isEmpty()) return new ResponseEntity<Object>("No se a encontrado usuarios con tarifa " + tarifa, HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(user);
        //Preguntar como podemos mostrar gente con tarifa de X monto para abajo.
        //filtrar por tarifa un metodo (para la yuli ;) )
    }

    @GetMapping("/profesionales/{profesional}")
    public ResponseEntity<Object> encontraarProfesionales(@PathVariable boolean profesional){
        List<UserModel> user = userService.findByProfesional(profesional);
        if(user.isEmpty()) return new ResponseEntity<Object>("No se han encontrado profesionales", HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(user);
    }
    
    

    @PutMapping("/{rut}")
    public ResponseEntity<UserModel> actualizar(@PathVariable String rut, @RequestBody UserModel user){
        try {
            UserModel newUser = userService.findById(rut);
            newUser.setNombre(user.getNombre());
            newUser.setApaterno(user.getApaterno());
            newUser.setAmaterno(user.getAmaterno());
            newUser.setCorreo(user.getCorreo());
            newUser.setDireccion(user.getDireccion());
            newUser.setTelefono(user.getTelefono());
            newUser.setTarifa(user.getTarifa());
            return ResponseEntity.ok(user);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    
    @DeleteMapping("/{rut}")
    public ResponseEntity<Void> borrar (@PathVariable String rut){
        userService.delete(rut);
        return ResponseEntity.noContent().build();
    }
    

}

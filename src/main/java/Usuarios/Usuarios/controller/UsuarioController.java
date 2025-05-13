package Usuarios.Usuarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserModel>> listar(){
        List<UserModel> users = userService.findAll();
        if(users.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<UserModel> guardar (@RequestBody UserModel user){
        UserModel nuevoUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUser);
    }

    @GetMapping("/{rut}")
    public ResponseEntity<UserModel> buscar(@PathVariable String rut){
        try {
            UserModel user = userService.findById(rut);
            return ResponseEntity.ok(user);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
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

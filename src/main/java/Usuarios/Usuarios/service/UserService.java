package Usuarios.Usuarios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Usuarios.Usuarios.modelo.UserModel;
import Usuarios.Usuarios.repository.UsuarioRepository;
import jakarta.transaction.Transactional;


@Service
@Transactional
public class UserService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UserModel> findAll(){
        return usuarioRepository.findAll();
    }

    public List<UserModel> findByNombre(String nombre){
        return usuarioRepository.findByNombre(nombre);
    }

    public UserModel save(UserModel user){
        return usuarioRepository.save(user);
    }

    public void delete(String rut){
        usuarioRepository.deleteById(rut);
    }

    public UserModel findById(String rut){
        return usuarioRepository.findById(rut).get();
    }

}

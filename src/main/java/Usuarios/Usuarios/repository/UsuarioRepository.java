package Usuarios.Usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Usuarios.Usuarios.modelo.UserModel;

import java.sql.Date;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<UserModel, String>{

    //Encuentra usuarios por rut
    List<UserModel> findByRut(String rut);

    //Encuentra usuarios por nombre
    List<UserModel> findByNombre(String nombre);

    //Encuyentra usuarios por correo electrónico
    UserModel findByCorreo(String correo);

    //Encuentra usuarios por nombre y apellido
    List<UserModel> findByNombreAndApaterno(String nombre, String apaterno);

    //Encuentra usuarios por tarifa
    List<UserModel> findByTarifa(int tarifa);

    //Encuentra usuarios por edad
    List<UserModel> findByFechaNacimiento (Date fechaNacimietno);

    List<UserModel> findByProfesional(boolean profesional);
    

}

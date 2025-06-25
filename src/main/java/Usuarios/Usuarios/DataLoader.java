package Usuarios.Usuarios;

import Usuarios.Usuarios.modelo.UserModel;
import Usuarios.Usuarios.repository.UsuarioRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import Usuarios.Usuarios.repository.UsuarioRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;


//@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();

        // Generar Usuarios
        UserModel usuario = new UserModel();
    
        for (int i = 0; i < 10; i++){
            String nueveDigitos = faker.number().digits(9);
            String digitoVerificador = faker.number().digits(1);
            String idGenerador = nueveDigitos + "-" + digitoVerificador;

            usuario.setRut(idGenerador);
            usuario.setNombre(faker.name().firstName());
            usuario.setApaterno(faker.name().lastName());
            usuario.setAmaterno(faker.name().lastName());
            usuario.setCorreo(faker.internet().emailAddress());
            usuario.setDireccion(faker.address().streetAddress());
            usuario.setTelefono(faker.number().numberBetween(100000000, 999999999));
            //Date fechaNacimiento = Date.from(faker.date().birthday(18, 80).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            Date fechaNacimiento = faker.date().birthday(18, 80);
            usuario.setFechaNacimiento(fechaNacimiento);
            usuario.setTarifa(faker.number().numberBetween(10_000, 90_000));
            usuario.setProfesional(faker.bool().bool());
            usuarioRepository.save(usuario);
        }

    }

    

    

}

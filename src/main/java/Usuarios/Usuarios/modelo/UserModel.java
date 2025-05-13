package Usuarios.Usuarios.modelo;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "usuarios")
public class UserModel {

    @Id
    @Column (unique = true, length = 13, nullable = false)
    private String rut;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apaterno;

    @Column(nullable = false)
    private String amaterno;

    @Column(nullable = false)
    private String correo;

    @Column(nullable = false)
    private String direccion;

    @Column(unique = true, nullable = false)
    private int telefono;

    @Column(nullable = false)
    private Date fechaNacimiento;

    @Column(nullable = false)
    private int tarifa;


}

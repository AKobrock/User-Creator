package Usuarios.Usuarios.assemblers;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


import Usuarios.Usuarios.controller.UsuarioControllerV2;
import Usuarios.Usuarios.modelo.UserModel;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<UserModel, EntityModel<UserModel>>  {

    @Override
    public EntityModel<UserModel> toModel(UserModel user) {
        return EntityModel.of(user,
                linkTo(methodOn(UsuarioControllerV2.class).buscar(user.getRut())).withSelfRel(),
                linkTo(methodOn(UsuarioControllerV2.class).listar()).withRel("usuarios"));
    }
}

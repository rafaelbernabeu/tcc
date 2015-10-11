package controllers;

import models.Usuario;
import models.enums.Estado;
import models.enums.TipoLogin;
import play.mvc.With;

import java.util.List;

/**
 * Created by Rafael on 18/09/2015.
 */
@Check("Administrador")
@With(Secure.class)
public class Usuarios extends CRUD {

    public static void novo(String tipo) {
        Usuario entity = new Usuario();
        entity.instituicao = TipoLogin.valueOf(tipo);
        Estado[] estados = Estado.values();
        render("/Usuarios/usuario.html", entity, estados);
    }

    public static void listar() {
        List<Usuario> usuarios = Usuario.findAll();
        render(usuarios);
    }

    public static void salvar() {

    }

    public static void editar(Long id) {
        Usuario entity = Usuario.findById(id);
        Estado[] estados = Estado.values();
        render("/Usuarios/usuario.html", entity, estados);
    }

    public static void excluir(Long id) {
        Usuario entity = Usuario.findById(id);
        entity.delete();
        listar();
    }

}

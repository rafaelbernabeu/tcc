package controllers;

import models.Usuario;
import models.enums.Estado;
import models.enums.TipoLogin;
import play.db.jpa.Blob;
import play.mvc.Before;
import play.mvc.With;

import java.util.List;

/**
 * Created by Rafael on 18/09/2015.
 */
@Check("Administrador")
@With(Secure.class)
public class Usuarios extends CRUD {

    @Before
    static void setConnectedUser() {
        if(Security.isConnected()) {
            Usuario user = Usuario.find("byEmail", Security.connected()).first();
            renderArgs.put("user", user.nome);
            renderArgs.put("email", user.email);
            renderArgs.put("userId", user.id);
            renderArgs.put("userPhoto", user.foto);
        }
    }

    public static void novo(String tipo) {
        Usuario entity = new Usuario();
        entity.foto = new Blob();
        entity.instituicao = TipoLogin.valueOf(tipo);
        Estado[] estados = Estado.values();
        render("/Usuarios/usuario.html", entity, estados);
    }

    public static void listar() {
        List<Usuario> usuarios = Usuario.findAll();
        render(usuarios);
    }

    public static void salvar(Long id, String nome, String email, String senha,
                              String instituicao, String matricula, String estado, Blob foto) {

        checkAuthenticity();
        Usuario usuario = id != null ? (Usuario) Usuario.findById(id) : new Usuario();
        usuario.nome = nome;
        usuario.email = email;
        usuario.password = senha;
        usuario.instituicao = TipoLogin.valueOf(instituicao);
        usuario.estado = Estado.getEstadoByDescricao(estado);
        usuario.matricula = matricula;
        usuario.foto = foto != null ? foto : usuario.foto;

        usuario.save();
        listar();

    }

    public static void editar(Long id) {
        Usuario entity = Usuario.findById(id);
        Estado[] estados = Estado.values();
        render("/Usuarios/usuario.html", entity, estados);
    }

    public static void excluir(Long id) {
        checkAuthenticity();
        Usuario entity = Usuario.findById(id);
        entity.delete();
        listar();
    }

}

package models;

import models.enums.Estado;
import models.enums.TipoLogin;
import play.data.validation.Email;
import play.data.validation.Password;
import play.data.validation.Required;
import play.db.jpa.Blob;
import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * Created by Rafael on 18/09/2015.
 */
@Entity
public class Usuario extends Model {

    public Usuario() {
        this.estado = Estado.Distrito_Federal;
    }

    @Email
    @Required
    public String email;

    @Password
    @Required
    public String password;

    @Required
    public String nome;

    @Required
    public String matricula;

    @Required
    public TipoLogin instituicao;

    @Required
    public Estado estado;

    public Blob foto;

    public static Usuario connect(String email, String password) {
        return find("byEmailAndPassword", email, password).first();
    }

    public String toString() {
        return nome;
    }
}
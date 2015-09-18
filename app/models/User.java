package models;

import play.data.validation.Email;
import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * Created by Rafael on 18/09/2015.
 */
@Entity
public class User extends Model {

    @Email
    @Required
    public String email;

    @Required
    public String password;
    public String nomeCompleto;
    public String matricula;
    public TipoLogin instituicao;

}
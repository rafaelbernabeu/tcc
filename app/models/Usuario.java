package models;

import play.data.validation.Email;
import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Created by Rafael on 18/09/2015.
 */
@Entity
public class Usuario extends Model {

    @Email
    @Required
    public String email;

    @Required
    public String password;
    public String nomeCompleto;
    public String matricula;
    public TipoLogin instituicao;

}
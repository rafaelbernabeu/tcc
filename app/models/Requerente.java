package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * Created by Rafael on 23/09/2015.
 */
@Entity
public class Requerente extends Model {

    public String nome;
    public String sobrenome;
    public String CPF;
    public String cargo;
    public String lotacao;

}

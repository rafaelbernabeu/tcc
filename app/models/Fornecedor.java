package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * Created by Rafael on 20/09/2015.
 */
@Entity
public class Fornecedor extends Model {

    public String nome;
    public String localDeEntrega;

}

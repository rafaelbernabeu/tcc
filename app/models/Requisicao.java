package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by Rafael on 23/09/2015.
 */
@Entity
public class Requisicao extends Model {

    @ManyToOne
    public Fornecedor fornecedor;

    public Integer ordem;

    @ManyToOne
    public Requerente requerente;

    public Integer quantidade;
    public String tipo;
    public String marca;
    public String modelo;
    public String cabibre;

    public Boolean favoravel;

}
package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by Rafael on 23/09/2015.
 */
@Entity
public class Municao extends Model {

    @ManyToOne
    public Requerente requerente;

    public Integer quantidade;
    public String tipo;
    public String marca;
    public String modelo;
    public String cabibre;

    @Override
    public String toString() {
        return marca + " " + modelo;
    }
}

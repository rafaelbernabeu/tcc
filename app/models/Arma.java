package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by Rafael on 23/09/2015.
 */
@Entity
public class Arma extends Model {

    @ManyToOne
    public Requerente requerente;

    public String tipo;
    public String marca;
    public String modelo;
    public String cabibre;
    public String nSerie;
    public String sinarm_sigma;
    public String acessorio;

    @Override
    public String toString() {
        return marca + " " + modelo;
    }
}

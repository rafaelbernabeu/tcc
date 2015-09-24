package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

/**
 * Created by Rafael on 23/09/2015.
 */
@Entity
public class Requisicao extends Model {

    public Integer ordem;
    public TipoRequisicao requisicao;

    @ManyToOne
    public Fornecedor fornecedor;

    @ManyToOne
    public Requerente requerente;

    @ManyToOne
    public Requerente alienante;

    @ManyToOne
    public Requerente adquirente;

    @OneToMany
    public List<Arma> armas;

    @OneToMany
    public List<Municao> municoes;

    public String local;
    public Date data;

    public Boolean favoravel;

}
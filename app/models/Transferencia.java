package models;

import models.enums.SituacaoRequerente;
import models.enums.TipoParecer;
import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by Rafael on 23/09/2015.
 */
@Entity
public class Transferencia extends Model {

    public Transferencia() {

        this.parecer = TipoParecer.PENDENTE;
        this.situacaoAlienante = SituacaoRequerente.INATIVO;
        this.situacaoAdquirente = SituacaoRequerente.INATIVO;
    }

    //alienante
    public String funcionalAlienante;
    public String nomeAlienante;
    public String identidadeAlienante;
    public String CPFAlienante;
    public String cargoAlienante;
    public String unidadeLotacaoAlienante;
    public String enderecoAlienante;
    public SituacaoRequerente situacaoAlienante;

    //adquirente
    public String funcionalAdquirente;
    public String nomeAdquirente;
    public String identidadeAdquirente;
    public String CPFAdquirente;
    public String cargoAdquirente;
    public String unidadeLotacaoAdquirente;
    public String enderecoAdquirente;
    public SituacaoRequerente situacaoAdquirente;

    //armas ou municoes
    public String tipo;
    public String marca;
    public String modelo;
    public String calibre;
    public String nSerie;
    public String sigmaSinarm;
    public String especificacoes;
    public String acessorios;

    public String observacao;

    public Date data;
    public String local;

    public TipoParecer parecer;
    public String resposta;
}
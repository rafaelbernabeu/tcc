package models;

import models.enums.TipoSituacao;
import models.enums.StatusRequisicao;
import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by Rafael on 23/09/2015.
 */
@Entity
public class Transferencia extends Model {

    //alienante
    public String funcionalAlienante;
    public String nomeAlienante;
    public String identidadeAlienante;
    public String CPFAlienante;
    public String cargoAlienante;
    public String unidadeLotacaoAlienante;
    public String enderecoAlienante;
    public TipoSituacao situacaoAlienante;

    //adquirente
    public String catFuncionalAdquirente;
    public String nomeAdquirente;
    public String identidadeAdquirente;
    public String CPFAdquirente;
    public String cargoAdquirente;
    public String unidadeLotacaoAdquirente;
    public String enderecoAdquirente;
    public TipoSituacao situacaoAdquirente;

    //armas ou municoes
    public String tipo;
    public String marca;
    public String modelo;
    public String calibre;
    public String nSerie;
    public String sigmaSinarm;
    public String especificacoes;
    public String acessorios;

    public Date data;
    public String local;

    public StatusRequisicao parecer = StatusRequisicao.AGUARDANDO;
    public String resposta;
}
package models;

import models.enums.StatusRequisicao;
import models.enums.TipoRequisicao;
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
public class AquisicaoRenovacao extends Model {

    public Integer nOrdem;
    public TipoRequisicao requisicao;

    public String fornecedor;
    public String localDeEntrega;

    public String nomeRequerente;
    public String cargo;
    public String unidadeLotacao;
    public String CPF;

    //armas ou municoes
    public String quantidade;
    public String tipo;
    public String marca;
    public String modelo;
    public String calibre;

    public Date data;
    public String local;

    public StatusRequisicao parecer = StatusRequisicao.AGUARDANDO;
    public String resposta;

}
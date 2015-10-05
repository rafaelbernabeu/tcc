package models;

import models.enums.TipoParecer;
import models.enums.TipoRequisicao;
import play.db.jpa.Model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Rafael on 23/09/2015.
 */
@Entity(name = "aquisicao_renovacao")
public class AquisicaoRenovacao extends Model {

    public AquisicaoRenovacao(){
        this.parecer = TipoParecer.PENDENTE;
    }

    //@GeneratedValue(strategy = GenerationType.AUTO)
    public Long nOrdem;
    public TipoRequisicao requisicao;

    //fornecedor
    public String fornecedor;
    public String localDeEntrega;

    //requerente
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

    public String observacao;

    public Date data;
    public String local;

    public TipoParecer parecer;
    public String resposta;

    @Override
    public String toString() {
        return CPF;
    }
}
package controllers;

import models.AquisicaoRenovacao;
import models.Transferencia;
import models.enums.SituacaoRequerente;
import models.enums.TipoLogin;
import models.Usuario;
import models.enums.TipoParecer;
import models.enums.TipoRequisicao;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;

import java.util.Date;
import java.util.List;

/**
 * Created by Rafael Abrão Bernabeu on 17/09/2015.
 */
@With(Secure.class)
public class PM extends Controller {

    @Before
    static void setConnectedUser() {
        if(Security.isConnected()) {
            Usuario user = Usuario.find("byEmail", Security.connected()).first();
            renderArgs.put("user", user.nome);
            renderArgs.put("email", user.email);
            if (user.instituicao.equals(TipoLogin.EXERCITO)) {
                Exercito.index();
            }
        }
    }

    public static void index() {
        Integer qtdAquisicoes = AquisicaoRenovacao.find("byRequisicao", TipoRequisicao.AQUISICAO).fetch().size();
        Integer qtdRenovacoes = AquisicaoRenovacao.find("byRequisicao", TipoRequisicao.RENOVACAO).fetch().size();
        Integer qtdTransferencias = Transferencia.findAll().size();

        render(qtdAquisicoes, qtdRenovacoes, qtdTransferencias);
    }

    //Cria nova aquisicao
    public static void aquisicao() {
        AquisicaoRenovacao entity = new AquisicaoRenovacao();
        entity.requisicao = TipoRequisicao.AQUISICAO;
        entity.data = new Date();
        render("/PM/anexo1.html", entity);
    }
    //Cria nova renovacao
    public static void renovacao() {
        AquisicaoRenovacao entity = new AquisicaoRenovacao();
        entity.requisicao = TipoRequisicao.RENOVACAO;
        entity.data = new Date();
        render("/PM/anexo1.html", entity);
    }

    public static void transferencia() {
        Transferencia entity = new Transferencia();
        entity.data = new Date();
        render("/PM/anexo2.html", entity);
    }

    //Lista as requisicoes
    public static void anexo1Pendentes() {
        List<AquisicaoRenovacao> aquisicoes = AquisicaoRenovacao.find("byParecer", TipoParecer.PENDENTE).fetch();
        render("/PM/listarAnexo1.html", aquisicoes);
    }

    public static void anexo1Respondidas() {
        List<AquisicaoRenovacao> aquisicoes = AquisicaoRenovacao.find("byParecerNotEqual", TipoParecer.PENDENTE).fetch();
        render("/PM/listarAnexo1.html", aquisicoes);
    }

    public static void anexo2Pendentes() {
        List<Transferencia> transferencias = Transferencia.find("byParecer", TipoParecer.PENDENTE).fetch();
        render("/PM/listarAnexo2.html", transferencias);
    }

    public static void anexo2Respondidas() {
        List<Transferencia> transferencias = Transferencia.find("byParecerNotEqual", TipoParecer.PENDENTE).fetch();
        render("/PM/listarAnexo2.html", transferencias);
    }

    //Seleciona uma requisicao para ser editada
    public static void editarAnexo1(Long id) {
        AquisicaoRenovacao entity = AquisicaoRenovacao.findById(id);
        render("/PM/anexo1.html", entity);
    }

    public static void excluirAnexo1(Long id) {
        AquisicaoRenovacao entity = AquisicaoRenovacao.findById(id);
        entity.delete();
        anexo1Pendentes();
    }

    public static void editarAnexo2(Long id) {
        Transferencia entity = Transferencia.findById(id);
        render("/PM/anexo2.html", entity);
    }

    public static void excluirAnexo2(Long id) {
        Transferencia entity = Transferencia.findById(id);
        entity.delete();
        anexo2Pendentes();
    }

    public static void salvarAnexo1(String id, String fornecedor, String endereco, Long nordem,
                                          String requerente, String cargo, String lotacao, String cpf, String quantidade,
                                          String tipo, String marca, String modelo, String calibre, String observacao, String requisicao) {

        AquisicaoRenovacao anexo1 = id != null && !id.equals("") ? (AquisicaoRenovacao)AquisicaoRenovacao.findById(Long.valueOf(id)) : new AquisicaoRenovacao();
        anexo1.requisicao = TipoRequisicao.valueOf(requisicao);
        anexo1.parecer = TipoParecer.PENDENTE;
        anexo1.fornecedor = fornecedor;
        anexo1.localDeEntrega = endereco;
        anexo1.nOrdem = nordem;
        anexo1.nomeRequerente = requerente;
        anexo1.cargo = cargo;
        anexo1.unidadeLotacao = lotacao;
        anexo1.CPF = cpf;
        anexo1.quantidade = quantidade;
        anexo1.tipo = tipo;
        anexo1.marca = marca;
        anexo1.modelo = modelo;
        anexo1.calibre = calibre;
        anexo1.observacao = observacao;
        anexo1.data = new Date();

        anexo1.save();

        PM.anexo1Pendentes();

    }

    public static void salvarAnexo2(String id, String funcionalAlienante, String nomeAlienante, String identidadeAlienante, String CPFAlienante,
                                    String cargoAlienante, String unidadeLotacaoAlienante, String enderecoAlienante,
                                    String situacaoAlienante, String funcionalAdquirente, String nomeAdquirente,
                                    String identidadeAdquirente, String CPFAdquirente, String cargoAdquirente,
                                    String unidadeLotacaoAdquirente, String enderecoAdquirente, String situacaoAdquirente,
                                    String tipo, String marca, String modelo, String calibre, String nSerie, String sigmaSinarm,
                                    String especificacoes, String acessorios, String observacao, String local) {

        Transferencia entity = id != null && !id.equals("") ? (Transferencia)Transferencia.findById(Long.valueOf(id)) : new Transferencia();

        entity.funcionalAlienante = funcionalAlienante;
        entity.nomeAlienante = nomeAlienante;
        entity.identidadeAlienante = identidadeAlienante;
        entity.CPFAlienante = CPFAlienante;
        entity.cargoAlienante = cargoAlienante;
        entity.unidadeLotacaoAlienante = unidadeLotacaoAlienante;
        entity.enderecoAlienante = enderecoAlienante;
        entity.situacaoAlienante = SituacaoRequerente.valueOf(situacaoAlienante);
        entity.funcionalAdquirente = funcionalAdquirente;
        entity.nomeAdquirente = nomeAdquirente;
        entity.identidadeAdquirente = identidadeAdquirente;
        entity.CPFAdquirente = CPFAdquirente;
        entity.cargoAdquirente = cargoAdquirente;
        entity.unidadeLotacaoAdquirente = unidadeLotacaoAdquirente;
        entity.enderecoAdquirente = enderecoAdquirente;
        entity.situacaoAdquirente = SituacaoRequerente.valueOf(situacaoAdquirente);
        entity.tipo = tipo;
        entity.marca = marca;
        entity.modelo = modelo;
        entity.calibre = calibre;
        entity.nSerie = nSerie;
        entity.sigmaSinarm = sigmaSinarm;
        entity.especificacoes = especificacoes;
        entity.acessorios = acessorios;
        entity.observacao = observacao;
        entity.data = new Date();
        entity.local = local;
        //entity.parecer = parecer;
        //entity.resposta = resposta;

        entity.save();

        PM.anexo2Pendentes();

    }
}

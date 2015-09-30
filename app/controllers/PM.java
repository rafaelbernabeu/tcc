package controllers;

import models.AquisicaoRenovacao;
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
 * Created by Rafael on 17/09/2015.
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
        render();
    }

    public static void aquisicao() {
        render("/PM/aquisicao.html");
    }

    public static void renovacao() {
        render("/PM/renovacao.html");
    }

    public static void existentesPendentes() {
        List<AquisicaoRenovacao> aquisicoes = AquisicaoRenovacao.find("byParecer", TipoParecer.PENDENTE).fetch();
        render("/PM/existentes.html", aquisicoes);
    }

    public static void existentesRespondidas() {
        List<AquisicaoRenovacao> aquisicoes = AquisicaoRenovacao.find("byParecerNotEqual", TipoParecer.PENDENTE).fetch();
        render("/PM/existentes.html", aquisicoes);
    }

    public static void salvarAnexo1(String fornecedor, String endereco, Long nordem,
                                          String requerente, String cargo, String lotacao, String cpf, String quantidade,
                                          String tipo, String marca, String modelo, String calibre, String observacao) {

        AquisicaoRenovacao anexo1 = new AquisicaoRenovacao();
        anexo1.requisicao = TipoRequisicao.AQUISICAO;
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

        render("/PM/aquisicao.html");

    }

    public static void salvarAnexo2(String fornecedor, String endereco, Long nordem,
                                    String requerente, String cargo, String lotacao, String cpf, String quantidade,
                                    String tipo, String marca, String modelo, String calibre, String observacao) {

        AquisicaoRenovacao anexo1 = new AquisicaoRenovacao();
        anexo1.requisicao = TipoRequisicao.RENOVACAO;
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

        render("/PM/renovacao.html");

    }

}

package controllers;

import models.AquisicaoRenovacao;
import models.Transferencia;
import models.enums.TipoParecer;
import models.enums.TipoLogin;
import models.Usuario;
import models.enums.TipoRequisicao;
import play.data.validation.Required;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;

import java.util.List;

/**
 * Created by Rafael on 17/09/2015.
 */
@With(Secure.class)
public class Exercito extends Controller {

    @Before
    static void setConnectedUser() {
        if(Security.isConnected()) {
            Usuario user = Usuario.find("byEmail", Security.connected()).first();
            renderArgs.put("user", user.nome);
            renderArgs.put("email", user.email);
            if (user.instituicao.equals(TipoLogin.POLICIAMILITAR)) {
                PM.index();
            }
        }
    }

    public static void index() {
        render();
    }

    //Seleciona para edicao
    public static void editarAnexo1(Long id) {
        AquisicaoRenovacao entity = AquisicaoRenovacao.findById(id);
        render("/Exercito/anexo1.html", entity);
    }

    public static void editarAnexo2(Long id) {
        Transferencia entity = Transferencia.findById(id);
        render("/Exercito/anexo2.html", entity);
    }

    //Salva alteracoes anexo1
    public static void salvarAnexo1(String id, String parecer, String resposta) {

        AquisicaoRenovacao anexo = AquisicaoRenovacao.findById(Long.valueOf(id));
        anexo.parecer = TipoParecer.valueOf(parecer);
        anexo.resposta = resposta;
        anexo.save();

        switch (anexo.requisicao) {
            case AQUISICAO:
                switch (anexo.parecer) {
                    case FAVORAVEL:
                        aquisicoesFavoraveis();
                        break;
                    case DESFAVORAVEL:
                        aquisicoesDesfavoraveis();
                        break;
                    case PENDENTE:
                        aquisicoesPendentes();
                        break;
                }
                break;
            case RENOVACAO:
                switch (anexo.parecer) {
                    case FAVORAVEL:
                        renovacoesFavoraveis();
                        break;
                    case DESFAVORAVEL:
                        renovacoesDesfavoraveis();
                        break;
                    case PENDENTE:
                        renovacoesPendentes();
                        break;
                }
                break;
            default:
                Exercito.index();
        }
    }

    public static void aquisicoesPendentes() {
        List<AquisicoesRenovacoes> aquisicoes = AquisicaoRenovacao.find("byRequisicaoAndParecer", TipoRequisicao.AQUISICAO, TipoParecer.PENDENTE).fetch();
        render("/Exercito/aquisicoes.html", aquisicoes);
    }

    public static void aquisicoesFavoraveis() {
        List<AquisicoesRenovacoes> aquisicoes = AquisicaoRenovacao.find("byRequisicaoAndParecer", TipoRequisicao.AQUISICAO, TipoParecer.FAVORAVEL).fetch();
        render("/Exercito/aquisicoes.html", aquisicoes);
    }

    public static void aquisicoesDesfavoraveis() {
        List<AquisicoesRenovacoes> aquisicoes = AquisicaoRenovacao.find("byRequisicaoAndParecer", TipoRequisicao.AQUISICAO, TipoParecer.DESFAVORAVEL).fetch();
        render("/Exercito/aquisicoes.html", aquisicoes);
    }

    public static void todasAquisicoes() {
        List<AquisicoesRenovacoes> aquisicoes = AquisicaoRenovacao.find("byRequisicao", TipoRequisicao.AQUISICAO).fetch();
        render("/Exercito/aquisicoes.html", aquisicoes);
    }

    public static void renovacoesPendentes() {
        List<AquisicoesRenovacoes> renovacoes = AquisicaoRenovacao.find("byRequisicaoAndParecer", TipoRequisicao.RENOVACAO, TipoParecer.PENDENTE).fetch();
        render("/Exercito/renovacoes.html", renovacoes);
    }

    public static void renovacoesFavoraveis() {
        List<AquisicoesRenovacoes> renovacoes = AquisicaoRenovacao.find("byRequisicaoAndParecer", TipoRequisicao.RENOVACAO, TipoParecer.FAVORAVEL).fetch();
        render("/Exercito/renovacoes.html", renovacoes);
    }

    public static void renovacoesDesfavoraveis() {
        List<AquisicoesRenovacoes> renovacoes = AquisicaoRenovacao.find("byRequisicaoAndParecer", TipoRequisicao.RENOVACAO, TipoParecer.DESFAVORAVEL).fetch();
        render("/Exercito/renovacoes.html", renovacoes);
    }

    public static void todasRenovacoes() {
        List<AquisicoesRenovacoes> renovacoes = AquisicaoRenovacao.find("byRequisicao", TipoRequisicao.RENOVACAO).fetch();
        render("/Exercito/renovacoes.html", renovacoes);
    }

}

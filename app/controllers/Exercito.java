package controllers;

import models.AquisicaoRenovacao;
import models.enums.TipoParecer;
import models.enums.TipoLogin;
import models.Usuario;
import models.enums.TipoRequisicao;
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

}

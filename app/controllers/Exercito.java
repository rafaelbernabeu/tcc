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
        List<AquisicaoRenovacao> aquisicoes = AquisicaoRenovacao.find("byParecer", TipoParecer.PENDENTE).fetch();
        System.out.println(aquisicoes.size());
    }

    public static void aquisicoes() {
        List<AquisicoesRenovacoes> aquisicoes = AquisicaoRenovacao.find("byRequisicao", TipoRequisicao.AQUISICAO).fetch();
        render(aquisicoes);
    }

    public static void renovacoes() {
        List<AquisicoesRenovacoes> aquisicoes = AquisicaoRenovacao.find("byRequisicao", TipoRequisicao.RENOVACAO).fetch();
        render(aquisicoes);
    }

}

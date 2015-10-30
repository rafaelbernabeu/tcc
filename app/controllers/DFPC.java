package controllers;

import models.AquisicaoRenovacao;
import models.Transferencia;
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
@Check("Diretoria de Fiscalização de Produtos Controlados")
@With(Secure.class)
public class DFPC extends Controller {

    @Before
    static void setConnectedUser() {
        if(Security.isConnected()) {
            Usuario user = Usuario.find("byEmail", Security.connected()).first();
            renderArgs.put("user", user.nome);
            renderArgs.put("email", user.email);
            renderArgs.put("userId", user.id);
            renderArgs.put("userPhoto", user.foto);
            if (user.instituicao.equals(TipoLogin.POLICIAMILITAR)) {
                PM.index();
            }
        }
    }

    public static void index() {
        Integer qtdAquisicoes = AquisicaoRenovacao.find("byRequisicao", TipoRequisicao.AQUISICAO).fetch().size();
        Integer qtdRenovacoes = AquisicaoRenovacao.find("byRequisicao", TipoRequisicao.RENOVACAO).fetch().size();
        Integer qtdTransferencias = Transferencia.findAll().size();
        Integer aquisicoesPendentes = AquisicaoRenovacao.find("byRequisicaoAndParecer", TipoRequisicao.AQUISICAO, TipoParecer.PENDENTE).fetch().size();
        Integer renovacoesPendentes = AquisicaoRenovacao.find("byRequisicaoAndParecer", TipoRequisicao.RENOVACAO, TipoParecer.PENDENTE).fetch().size();
        Integer transferenciasPendentes = Transferencia.find("byParecer", TipoParecer.PENDENTE).fetch().size();

        render(qtdAquisicoes, qtdRenovacoes, qtdTransferencias, aquisicoesPendentes, renovacoesPendentes, transferenciasPendentes);
    }

    //Seleciona para edicao
    public static void editarAnexo1(Long id) {
        AquisicaoRenovacao entity = AquisicaoRenovacao.findById(id);
        render("/DFPC/anexo1.html", entity);
    }

    public static void editarAnexo2(Long id) {
        Transferencia entity = Transferencia.findById(id);
        render("/DFPC/anexo2.html", entity);
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
                    default:
                        DFPC.index();
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
                DFPC.index();
                break;
        }
    }

    public static void salvarAnexo2(String id, String parecer, String resposta) {

        Transferencia anexo = Transferencia.findById(Long.valueOf(id));
        anexo.parecer = TipoParecer.valueOf(parecer);
        anexo.resposta = resposta;
        anexo.save();

        switch (anexo.parecer) {

            case FAVORAVEL:
                transferenciasFavoraveis();
                break;
            case DESFAVORAVEL:
                transferenciasDesfavoraveis();
                break;
            case PENDENTE:
                transferenciasPendentes();
                break;
            default:
                DFPC.index();
                break;
        }

    }

    public static void aquisicoesPendentes() {
        List<AquisicoesRenovacoes> aquisicoes = AquisicaoRenovacao.find("byRequisicaoAndParecer", TipoRequisicao.AQUISICAO, TipoParecer.PENDENTE).fetch();
        render("/DFPC/aquisicoes.html", aquisicoes);
    }

    public static void aquisicoesFavoraveis() {
        List<AquisicoesRenovacoes> aquisicoes = AquisicaoRenovacao.find("byRequisicaoAndParecer", TipoRequisicao.AQUISICAO, TipoParecer.FAVORAVEL).fetch();
        render("/DFPC/aquisicoes.html", aquisicoes);
    }

    public static void aquisicoesDesfavoraveis() {
        List<AquisicoesRenovacoes> aquisicoes = AquisicaoRenovacao.find("byRequisicaoAndParecer", TipoRequisicao.AQUISICAO, TipoParecer.DESFAVORAVEL).fetch();
        render("/DFPC/aquisicoes.html", aquisicoes);
    }

    public static void todasAquisicoes() {
        List<AquisicoesRenovacoes> aquisicoes = AquisicaoRenovacao.find("byRequisicao", TipoRequisicao.AQUISICAO).fetch();
        render("/DFPC/aquisicoes.html", aquisicoes);
    }

    public static void renovacoesPendentes() {
        List<AquisicoesRenovacoes> renovacoes = AquisicaoRenovacao.find("byRequisicaoAndParecer", TipoRequisicao.RENOVACAO, TipoParecer.PENDENTE).fetch();
        render("/DFPC/renovacoes.html", renovacoes);
    }

    public static void renovacoesFavoraveis() {
        List<AquisicoesRenovacoes> renovacoes = AquisicaoRenovacao.find("byRequisicaoAndParecer", TipoRequisicao.RENOVACAO, TipoParecer.FAVORAVEL).fetch();
        render("/DFPC/renovacoes.html", renovacoes);
    }

    public static void renovacoesDesfavoraveis() {
        List<AquisicoesRenovacoes> renovacoes = AquisicaoRenovacao.find("byRequisicaoAndParecer", TipoRequisicao.RENOVACAO, TipoParecer.DESFAVORAVEL).fetch();
        render("/DFPC/renovacoes.html", renovacoes);
    }

    public static void todasRenovacoes() {
        List<AquisicoesRenovacoes> renovacoes = AquisicaoRenovacao.find("byRequisicao", TipoRequisicao.RENOVACAO).fetch();
        render("/DFPC/renovacoes.html", renovacoes);
    }

    public static void transferenciasPendentes() {
        List<Transferencia> transferencias = Transferencia.find("byParecer", TipoParecer.PENDENTE).fetch();
        render("/DFPC/transferencias.html", transferencias);
    }

    public static void transferenciasRespondidas() {
        List<Transferencia> transferencias = Transferencia.find("byParecerNotEqual", TipoParecer.PENDENTE).fetch();
        render("/DFPC/transferencias.html", transferencias);
    }

    public static void transferenciasFavoraveis() {
        List<Transferencia> transferencias = Transferencia.find("byParecer", TipoParecer.FAVORAVEL).fetch();
        render("/DFPC/transferencias.html", transferencias);
    }

    public static void transferenciasDesfavoraveis() {
        List<Transferencia> transferencias = Transferencia.find("byParecer", TipoParecer.DESFAVORAVEL).fetch();
        render("/DFPC/transferencias.html", transferencias);
    }

    public static void todasTransferencias() {
        List<Transferencia> transferencias = Transferencia.findAll();
        render("/DFPC/transferencias.html", transferencias);
    }

}

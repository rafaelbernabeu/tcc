package controllers;

import models.AquisicaoRenovacao;

/**
 * Created by Rafael on 23/09/2015.
 */
@CRUD.For(AquisicaoRenovacao.class)
public class AquisicoesRenovacoes extends CRUD {

    public static void edit(Long id) {
        render("/AquisicoesRenovacoes/editar.html");
    }

    public static void salvar() {

    }
}

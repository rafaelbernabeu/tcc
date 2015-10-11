package controllers;

import models.AquisicaoRenovacao;
import play.mvc.With;

/**
 * Created by Rafael on 23/09/2015.
 */
@CRUD.For(AquisicaoRenovacao.class)
@Check("Administrador")
@With(Secure.class)
public class AquisicoesRenovacoes extends CRUD {


}

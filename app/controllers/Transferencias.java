package controllers;

import play.mvc.With;

/**
 * Created by Rafael on 23/09/2015.
 */
@CRUD.For(models.Transferencia.class)
@Check("Administrador")
@With(Secure.class)
public class Transferencias extends CRUD {
}

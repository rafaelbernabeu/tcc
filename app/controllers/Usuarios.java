package controllers;

import play.mvc.With;

/**
 * Created by Rafael on 18/09/2015.
 */
@Check("Administrador")
@With(Secure.class)
public class Usuarios extends CRUD {
}

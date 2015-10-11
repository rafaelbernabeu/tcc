package controllers;

import play.mvc.Controller;
import play.mvc.With;

/**
 * Created by Rafael on 20/09/2015.
 */
@With(Secure.class)
@Check("Administrador")
public class Admin extends Controller {
}

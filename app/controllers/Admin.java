package controllers;

import play.mvc.Controller;

/**
 * Created by Rafael on 20/09/2015.
 */
//@With(Secure.class)
public class Admin extends Controller {

    public static void index() {
        render();
    }

}
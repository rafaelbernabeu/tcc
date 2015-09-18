package controllers;

import play.mvc.Controller;
import play.mvc.With;

/**
 * Created by Rafael on 17/09/2015.
 */
@With(Secure.class)
public class PM extends Controller {

    public static void index() {
        render();
    }

}

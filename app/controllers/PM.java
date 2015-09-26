package controllers;

import models.enums.TipoLogin;
import models.Usuario;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;

/**
 * Created by Rafael on 17/09/2015.
 */
@With(Secure.class)
public class PM extends Controller {

    @Before
    static void setConnectedUser() {
        if(Security.isConnected()) {
            Usuario user = Usuario.find("byEmail", Security.connected()).first();
            renderArgs.put("user", user.nome);
            renderArgs.put("email", user.email);
            if (user.instituicao.equals(TipoLogin.EXERCITO)) {
                Exercito.index();
            }
        }
    }

    public static void index() {
        render();
    }

    public static void aquisicaoRenovacao() {

        render("/PM/aquisicaoRenovacao.html");

    }

}

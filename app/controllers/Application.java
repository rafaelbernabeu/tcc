package controllers;

import models.enums.TipoLogin;
import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    @Before
    static void setConnectedUser() {
        if(Security.isConnected()) {
            Usuario user = Usuario.find("byEmail", Security.connected()).first();
            renderArgs.put("user", user.nome);
            renderArgs.put("email", user.email);
            renderArgs.put("instituicao", user.instituicao.getDescricao());
        }
    }

    public static void index() {
        boolean connected = Security.isConnected();
        render(connected);
    }

}
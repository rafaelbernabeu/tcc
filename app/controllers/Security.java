package controllers;

import models.Usuario;
import models.enums.TipoLogin;

/**
 * Created by Rafael on 18/09/2015.
 */
public class Security extends Secure.Security {

    static boolean authenticate(String email, String password) {
       Usuario usuario = Usuario.connect(email, password);
        renderArgs.put("usuario", usuario);
        return usuario != null;
    }

    static boolean check(String profile) {
        if(TipoLogin.ADMIN.getDescricao().equals(profile)) {
            return Usuario.find("byEmail", Security.connected()).<Usuario>first().instituicao.compareTo(TipoLogin.ADMIN) == 0;
        }
        if(TipoLogin.EXERCITO.getDescricao().equals(profile)) {
            return Usuario.find("byEmail", Security.connected()).<Usuario>first().instituicao.compareTo(TipoLogin.EXERCITO) == 0 ||
                    Usuario.find("byEmail", Security.connected()).<Usuario>first().instituicao.compareTo(TipoLogin.ADMIN) == 0;
        }
        if(TipoLogin.POLICIAMILITAR.getDescricao().equals(profile)) {
            return Usuario.find("byEmail", Security.connected()).<Usuario>first().instituicao.compareTo(TipoLogin.POLICIAMILITAR) == 0 ||
                    Usuario.find("byEmail", Security.connected()).<Usuario>first().instituicao.compareTo(TipoLogin.ADMIN) == 0;
        }
        return false;
    }


}
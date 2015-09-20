package controllers;

import models.Usuario;

/**
 * Created by Rafael on 18/09/2015.
 */
public class Security extends Secure.Security {

    static boolean authenticate(String email, String password) {
        return Usuario.connect(email, password) != null;
    }

}
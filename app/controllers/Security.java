package controllers;

/**
 * Created by Rafael on 18/09/2015.
 */
public class Security extends Secure.Security {

    static boolean authenticate(String username, String password) {
        return true;
    }

}
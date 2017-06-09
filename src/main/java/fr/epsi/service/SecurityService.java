package fr.epsi.service;

/** Sercurity service interface */
public interface SecurityService {

    /**
     * Get the Username of the logged user
     * @return Username
     */
    String findLoggedInUsername();

    /**
     * Redirect to welcome page after login
     * @param username User's username
     * @param password User's password
     */
    void autologin(String username, String password);
}

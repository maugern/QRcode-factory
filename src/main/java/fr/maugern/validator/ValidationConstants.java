/**
 * ValidationConstants.java
 *
 * @author Nicolas
 * Created on 24 sept. 2017
 */
package fr.maugern.validator;

/**
 * Validation values used in application's from
 */
public class ValidationConstants {

    public static final int MIN_PASSWORD_LENGTH = 8;
    public static final int MIN_USERNAME_LENGTH = 8;
    public static final int MAX_USERNAME_LENGTH = 32;
    public static final int MAX_URL_LENGTH = 4000;
    public static final String REGEX_URI = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

}

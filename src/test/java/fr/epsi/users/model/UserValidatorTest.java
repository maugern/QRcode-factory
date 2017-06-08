package fr.epsi.users.model;

import fr.epsi.users.validator.UserValidator;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.junit.Assert.assertFalse;

public class UserValidatorTest {

    private UserValidator validator = new UserValidator();

    @Test
    @Ignore
    public void should_validate_correct_alias() {
        User user = new User("foo", "foo", "foo@foo.com", "azerty123");
        Errors errors = new BeanPropertyBindingResult(user, "");
        validator.validate(user, errors);

        assertFalse(errors.hasErrors());
    }
    
}

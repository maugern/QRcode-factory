package fr.epsi.validator;

import fr.epsi.model.User;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.junit.Assert.assertFalse;

public class UserValidatorTest {

    @Autowired
    private UserValidator validator;

    @Test
    @Ignore
    public void should_validate_correct_alias() {
        User user = new User();
        user.setUsername("Richard545");
        user.setPassword("V3rYsTr0nGp4ssVVoRd");
        user.setPasswordConfirm("V3rYsTr0nGp4ssVVoRd");
        Errors errors = new BeanPropertyBindingResult(user, "user");
        validator.validate(user, errors);

        assertFalse(errors.hasErrors());
    }
    
}

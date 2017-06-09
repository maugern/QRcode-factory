package fr.epsi.validator;

import fr.epsi.model.User;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class UserValidatorTest {

    @Autowired
    private UserValidator validator;

    @Test
    public void should_validate_correct_user() {
        User user = new User();
        user.setUsername("Richard545");
        user.setPassword("V3rYsTr0nGp4ssVVoRd");
        user.setPasswordConfirm("V3rYsTr0nGp4ssVVoRd");
        Errors errors = new BeanPropertyBindingResult(user, "");
        validator.validate(user, errors);

        assertFalse(errors.hasErrors());
    }

    @Test
    @Ignore
    public void should_refuse_incorrect_user() {
        User user = new User();
        user.setUsername("foo");
        user.setPassword("bar");
        user.setPasswordConfirm("lol");
        Errors errors = new BeanPropertyBindingResult(user, "user");
        validator.validate(user, errors);

        assertTrue(errors.hasErrors());
    }

}

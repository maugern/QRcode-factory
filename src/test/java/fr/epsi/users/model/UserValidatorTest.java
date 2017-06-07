package fr.epsi.users.model;

import org.junit.Test;

public class UserValidatorTest {

    javax.validation.Validator validator;
    
    @Test
    public void should_validate_correct_alias() {
        validator.validate(new User("foo", "foo", "foo@foo.com", "azerty123"));
    }
    
    @Test
    public void should_refuse_incorrect_alias() {
        validator.validate(new User("fooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo", "foo", "foo@foo.com", "azerty123"));
    }

}

package fr.epsi.users.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

public class UserTest {

    @org.junit.jupiter.api.Test
    public void should_set_hash_at_build() {
        User user = new User("alias","name","email","password");
        assertNotNull(user.getPasswdHash());
        assertFalse(user.getPasswdHash().equals("password"));
    }

    @org.junit.jupiter.api.Test
    public void should_set_random_and_secure_hash() {
        User u1 = new User("@foo","foo","foo@email.com","password");
        User u2 = new User("@bar","bar","bar@email.com","password");
        assertFalse(u1.getPasswdHash().equals(u2.getPasswdHash()));
        assertTrue(u1.getPasswdHash().length() >= 59);
        assertTrue(u2.getPasswdHash().length() >= 59);
    }

    @Test
    public void should_accept_correct_password() {
        User user = new User("alias","name","email","password");
        assertTrue(user.isGoodPassword("password"));
        user.setPassword("machine");
        assertTrue(user.isGoodPassword("machine"));
    }

    @Test
    public void should_refuse_incorrect_password() {
        User user = new User("alias","name","email","password");
        assertFalse(user.isGoodPassword("pasSword"));
    }

    @Test
    public void should_set_user_role_by_default() {
        User user = new User("alias","name","email","password");
        assertTrue(user.getUserRole().contains(new UserRoles(user,Role.ROLE_USER)));
        assertFalse(user.getUserRole().contains(new UserRoles(user,Role.ROLE_ADMIN)));
    }

    @Test
    public void should_add_and_remove_role() {
        User user = new User("alias","name","email","password");
        user.removeRole(Role.ROLE_USER);
        assertFalse(user.getUserRole().contains(new UserRoles(user,Role.ROLE_USER)));
        user.addRole(Role.ROLE_USER);
        user.addRole(Role.ROLE_ADMIN);
        assertTrue(user.getUserRole().contains(new UserRoles(user,Role.ROLE_USER)));
        assertTrue(user.getUserRole().contains(new UserRoles(user,Role.ROLE_ADMIN)));
    }

}

package fr.epsi.users.dao;

import fr.epsi.users.model.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.*;

@WebAppConfiguration
@ContextConfiguration(locations = "file:src/test/resources/application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoImplTest {

    @Autowired UserDao userdao;

    @Test
    @Transactional
    @Rollback(true)
    public void test_saving_user() {
        User foo = new User("foo", "foo","foo@foo.com" , "F0O");
        User savedFoo = userdao.saveUser(foo).get();
        assertEquals(savedFoo.getAlias(), foo.getAlias());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void should_set_id_when_make_persistent() {
        User foo = new User("foo", "foo", "foo@foo.com", "F0O");
        assertNotNull(userdao.saveUser(foo).get().getId());
    }


    @Test
    @Transactional
    @Rollback(true)
    public void test_findById() {
        User foo = new User("foo", "foo", "foo@foo.com", "F0O");
        User savedFoo = userdao.saveUser(foo).get();
        Optional<User> retrievedFoo = userdao.getUserById(savedFoo.getId());
        assertEquals(retrievedFoo.get(),savedFoo);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void test_findByAlias() {
        User foo = new User("foo", "foo", "foo@foo.com", "F0O");
        User savedFoo = userdao.saveUser(foo).get();
        Optional<User> retrievedFoo = userdao.getUserByAlias(savedFoo.getAlias());
        if (!retrievedFoo.isPresent())
            fail("empty result");
        assertEquals(retrievedFoo.get(),savedFoo);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void test_updating_user() {
        User foo = new User("foo", "foo", "foo@foo.com", "F0O");
        assertEquals(userdao.saveUser(foo).get().getName(), "foo");
        foo.setName("bar");
        assertEquals(userdao.saveUser(foo).get().getName(),"bar");
    }

    @Test
    @Transactional
    @Rollback(true)
    public void test_persistent_removeUser() {
        User foo = new User("foo", "foo", "foo@foo.com", "F0O");
        User savedFoo = userdao.saveUser(foo).get();
        assertTrue(userdao.getUserById(savedFoo.getId()).isPresent());
        userdao.removeUser(savedFoo);
        assertFalse(userdao.getUserById(savedFoo.getId()).isPresent());
    }

}

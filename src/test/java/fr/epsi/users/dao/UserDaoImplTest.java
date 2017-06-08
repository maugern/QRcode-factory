package fr.epsi.users.dao;

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

    @Autowired UserService userService;

    @Test
    @Transactional
    @Rollback(true)
    public void test_saving_user() {
        User foo = new User("foo", "foo","foo@foo.com" , "F0O");
        User savedFoo = userService.save(foo).get();
        assertEquals(savedFoo.getAlias(), foo.getAlias());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void should_set_id_when_make_persistent() {
        User foo = new User("foo", "foo", "foo@foo.com", "F0O");
        assertNotNull(userService.save(foo).get().getId());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void test_findByAlias() {
        User foo = new User("foo", "foo", "foo@foo.com", "F0O");
        User savedFoo = userService.save(foo).get();
        Optional<User> retrievedFoo = userService.findByAlias(savedFoo.getAlias());
        if (!retrievedFoo.isPresent())
            fail("empty result");
        assertEquals(retrievedFoo.get(),savedFoo);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void test_updating_user() {
        User foo = new User("foo", "foo", "foo@foo.com", "F0O");
        assertEquals(userService.save(foo).get().getName(), "foo");
        foo.setName("bar");
        assertEquals(userService.save(foo).get().getName(),"bar");
    }

}

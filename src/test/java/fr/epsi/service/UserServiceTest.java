package fr.epsi.service;

import fr.epsi.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ContextConfiguration(locations = "file:src/test/resources/application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    @Transactional
    @Rollback(true)
    public void test_saving_user() {
        User foo = new User();
        foo.setUsername("foo");
        userService.save(foo);
        assertNotNull(userService.findByUsername("foo"));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void should_set_id_when_make_persistent() {
        User foo = new User();
        assertNotNull(userService.findByUsername("foo").getId());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void test_updating_user() {
        User foo = new User();
        foo.setUsername("foo");
        userService.save(foo);
        Long idFoo = userService.findByUsername("foo").getId();
        foo.setUsername("bar");
        userService.save(foo);
        Long idBar = userService.findByUsername("bar").getId();
        assertEquals(idBar,idFoo);
    }

}

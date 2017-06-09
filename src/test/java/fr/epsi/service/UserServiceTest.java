package fr.epsi.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.epsi.model.User;

@ContextConfiguration(locations = "classpath:/application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Ignore
    @Test
    @Transactional
    @Rollback(true)
    public void test_saving_user() {
        User foo = new User();
        foo.setUsername("foo");
        userService.save(foo);
        assertNotNull(userService.findByUsername("foo"));
    }

    @Ignore
    @Test
    @Transactional
    @Rollback(true)
    public void should_set_id_when_make_persistent() {
        User foo = new User();
        userService.save(foo);
        assertNotNull(userService.findByUsername("foo").getId());
    }

    @Ignore
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

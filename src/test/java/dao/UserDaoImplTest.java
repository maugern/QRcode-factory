package dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import dao.UserDao;
import entity.User;

import org.springframework.test.annotation.Rollback;

public class UserDaoImplTest {

    @Autowired private UserDao userdao;

    @Ignore
    @Test
    @Transactional
    @Rollback(true)
    public void test_persistent_addUser() {
        User foo = new User("foo", "foo","foo@foo.com" , "F0O");

        userdao.addUser(foo);
        List<User> users = userdao.listUsers();

        assertTrue(foo.equals(users.get(0)));
    }

    @Ignore
    @Test
    @Transactional
    @Rollback(true)
    public void test_persistent_updateUser() {
        User foo = new User("foo", "foo", "foo@foo.com", "F0O");

        userdao.addUser(foo);
        List<User> users = userdao.listUsers();

        assertTrue(foo.equals(users.get(0)));

        foo.setName("bar");
        userdao.updateUser(foo);

        users = userdao.listUsers();
        assertTrue("bar".equals(users.get(0).getName()));
    }

    @Ignore
    @Test
    @Transactional
    @Rollback(true)
    public void test_persistent_removeUser() {
        User foo = new User("foo", "foo", "foo@foo.com", "F0O");

        userdao.addUser(foo);
        List<User> users = userdao.listUsers();

        assertEquals(users.size(), 1);

        userdao.removeUser(foo);

        users = userdao.listUsers();
        assertEquals(users.size(), 0);
    }

}

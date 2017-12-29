package fr.maugern.model;

import fr.maugern.helper.AssertAnnotations;
import fr.maugern.helper.ReflectTool;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.Table;

public class UserTest {

    @Test
    public void typeAnnotations() {
        AssertAnnotations.assertType(
            User.class, Entity.class, Table.class
        );
    }

    @Test
    public void fieldAnnotations() {
        AssertAnnotations.assertField(User.class, "id");
        AssertAnnotations.assertField(User.class, "username");
        AssertAnnotations.assertField(User.class, "password");
        AssertAnnotations.assertField(User.class, "roles");
    }

    @Test
    public void entity() {
        Table entity = ReflectTool.getClassAnnotation(User.class, Table.class);
        Assert.assertEquals("users", entity.name());
    }

    @Test
    public void id() {
        GeneratedValue generatedValue = ReflectTool.getMethodAnnotation(User.class, "getId", GeneratedValue.class);
        Assert.assertEquals(GenerationType.IDENTITY, generatedValue.strategy());
        Assert.assertEquals("", generatedValue.generator());
    }

    @Test
    public void roles() {
        ManyToMany manyToMany = ReflectTool.getMethodAnnotation(User.class, "getRoles", ManyToMany.class);
        Assert.assertEquals("", manyToMany.mappedBy());
        Assert.assertEquals(FetchType.LAZY, manyToMany.fetch());

        JoinTable joinTable = ReflectTool.getMethodAnnotation(User.class, "getRoles", JoinTable.class);
        Assert.assertEquals("user_role", joinTable.name());
        Assert.assertEquals("users_id", joinTable.joinColumns()[0].name());
        Assert.assertEquals("role_id", joinTable.inverseJoinColumns()[0].name());
    }

}

package fr.maugern.model;

import fr.maugern.helper.AssertAnnotations;
import fr.maugern.helper.ReflectTool;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.*;

public class RoleTest {

    @Test
    public void typeAnnotations() {
        AssertAnnotations.assertType(
                Role.class, Entity.class, Table.class
        );
    }

    @Test
    public void fieldAnnotations() {
        AssertAnnotations.assertField(Role.class, "id");
        AssertAnnotations.assertField(Role.class, "name");
    }

    @Test
    public void entity() {
        Table entity = ReflectTool.getClassAnnotation(Role.class, Table.class);
        Assert.assertEquals("role", entity.name());
    }

    @Test
    public void id() {
        GeneratedValue generatedValue = ReflectTool.getMethodAnnotation(Role.class, "getId", GeneratedValue.class);
        Assert.assertEquals(GenerationType.IDENTITY,generatedValue.strategy());
        Assert.assertEquals("",generatedValue.generator());
    }

    @Test
    public void roles() {
        ManyToMany roles = ReflectTool.getMethodAnnotation(Role.class, "getUsers", ManyToMany.class);
        Assert.assertEquals("roles", roles.mappedBy());
        Assert.assertEquals(FetchType.LAZY, roles.fetch());
    }

}

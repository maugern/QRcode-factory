/*
 * QrCode-factory, short link generator ditributed by QRcode
 * Copyright (C) 2017 Nicolas Mauger <https://maugern.fr>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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

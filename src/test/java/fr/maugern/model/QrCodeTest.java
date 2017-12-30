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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import fr.maugern.helper.AssertAnnotations;
import fr.maugern.helper.ReflectTool;
import org.junit.*;
import org.junit.rules.TemporaryFolder;

import javax.persistence.*;

public class QrCodeTest {

    @Test
    public void typeAnnotations() {
        AssertAnnotations.assertType(
                QrCode.class, Entity.class, Table.class
        );
    }

    @Test
    public void fieldAnnotations() {
        AssertAnnotations.assertField(QrCode.class, "id");
        AssertAnnotations.assertField(QrCode.class, "author");
        AssertAnnotations.assertField(QrCode.class, "url");
    }

    @Test
    public void entity() {
        Table entity = ReflectTool.getClassAnnotation(QrCode.class, Table.class);
        Assert.assertEquals("qrcode", entity.name());
    }

    @Test
    public void id() {
        GeneratedValue generatedValue = ReflectTool.getMethodAnnotation(QrCode.class, "getId", GeneratedValue.class);
        Assert.assertEquals(GenerationType.IDENTITY,generatedValue.strategy());
        Assert.assertEquals("",generatedValue.generator());
    }

    @Test
    public void author() {
        ManyToOne manyToOne = ReflectTool.getMethodAnnotation(QrCode.class, "getAuthor", ManyToOne.class);
        Assert.assertEquals(FetchType.LAZY, manyToOne.fetch());

        JoinColumn joinColumn = ReflectTool.getMethodAnnotation(QrCode.class, "getAuthor", JoinColumn.class);
        Assert.assertEquals("author", joinColumn.name());
        Assert.assertEquals("id", joinColumn.referencedColumnName());
        Assert.assertTrue(joinColumn.nullable());
    }

    @Test
    public void should_correct_hashid_when_id_is_ok() {
        assertNotNull(QrCode.getIdFromHashid(QrCode.getHashidFromId(123456L)));
    }

    @Test
    public void should_generate_correct_base64_image() {
        QrCode qrCode = new QrCode(null, "https://helloooo.com");
        qrCode.setId(123456L);
        assertTrue(qrCode.getGeneratedImage().matches("iVBOR.*Jggg=="));
    }

}

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

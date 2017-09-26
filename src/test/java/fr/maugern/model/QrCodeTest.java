package fr.maugern.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class QrCodeTest {

    @Rule 
    public TemporaryFolder folder= new TemporaryFolder();
       
    @Before
    public void init_temporary_folder() throws  IOException {
        File createdFolder = folder.newFolder("junit_qrcode");
        System.out.println("Creat temporary folder in " + createdFolder.getAbsolutePath());
    }

    @Test
    public void should_generate_correct_base64_image() {
        QrCode qrCode = new QrCode(null,"https://helloooo.com");
        qrCode.setId(123456L);
        assertTrue(qrCode.getGeneratedImage().matches("iVBOR.*Jggg=="));
    }

    @Test
    public void should_correct_hashid_when_id_is_ok() {
        QrCode qrCode = new QrCode(null,"https://helloooo.com");
        qrCode.setId(123456L);
        String hashid = qrCode.getHashid();
        System.out.println(hashid);
        assertEquals(QrCode.getIdFromHashid(hashid), new Long(123456L));
    }

    @After
    public void automaticly_destroy_temporary_files_and_folders() {
        System.out.println("End of QrCodeTest. Delete all temporary files.");
    }

}

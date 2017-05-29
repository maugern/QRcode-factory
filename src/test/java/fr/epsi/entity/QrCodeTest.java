package fr.epsi.entity;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import fr.epsi.entity.model.QrCode;

public class QrCodeTest {

    @Rule public TemporaryFolder folder= new TemporaryFolder();
    public File createdFolder;
    public File file_to_test;

    @BeforeEach
    public void init_temporary_folder() throws  IOException {
        createdFolder = folder.newFolder("junit_qrcode");
        System.out.println("Creat temporary folder in " + createdFolder.getAbsolutePath());
    }

    @org.junit.jupiter.api.Test
    public void should_generate_and_save_qrcode() throws IOException {
        QrCode.generateAndSave("https://www.april.org/", 400, "png", createdFolder.getAbsolutePath() + System.getProperty("file.separator") + "qrcode.png");
        System.out.println("A test qrccode will be create in Testing file will be create in : ");
        System.out.println(createdFolder.getAbsolutePath() + System.getProperty("file.separator") + "qrcode.png");
        file_to_test = new File(createdFolder.getAbsolutePath() + System.getProperty("file.separator") + "qrcode.png");
        assertTrue(file_to_test.exists());
    }

    @AfterEach
    public void automaticly_destroy_temporary_files_and_folders() {
        System.out.println("End of QrCodeTest. Delete all temporary files.");
    }

}

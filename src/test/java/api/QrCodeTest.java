/**
 * QrCodeTest.java
 * @author Nicolas
 * Created on 4 févr. 2017
 */
package api;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class QrCodeTest {
	
	@Rule
	public TemporaryFolder folder= new TemporaryFolder();

	@Test
	public void should_generate_and_save_qrcode() throws IOException {
		File createdFolder= folder.newFolder("junit_qrcode");
		QrCode.generateAndSave("https://www.april.org/", 400, "png", createdFolder.getAbsolutePath() + System.getProperty("file.separator") + "qrcode.png");
		System.out.println(createdFolder.getAbsolutePath() + System.getProperty("file.separator") + "qrcode.png");
		File file_to_test = new File(createdFolder.getAbsolutePath() + System.getProperty("file.separator") + "qrcode.png");
		assertTrue(file_to_test.exists());
	}
}

package fr.maugern.model;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.hashids.Hashids;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/** QrCode Entity model */
@Entity
@Table(name = "qrcode")
public class QrCode {

    private static final Logger logger = LoggerFactory.getLogger(QrCode.class);
    private static final Hashids hashids = new Hashids("J'apprécis les fruits au sirop");

    private Long id;
    private User author;
    private String url;
    private List<Date> calls = new ArrayList<>();

    /** Default constructor */
    public QrCode() {}

    /**
     * QrCode contructor. Will generate hashid and is salt.
     * @param author Author of the QRcode
     * @param url Url of the redirection
     */
    public QrCode(User author, String url) {
        this.author = author;
        this.url = url;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author", referencedColumnName = "id", nullable = true)
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Generate image in base64
     * @return String who representing Qrcode in base64
     */
    @Transient
    public String getGeneratedImage() {
        BitMatrix bitMatrix;
        BufferedImage bufferedImage = null;
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            bitMatrix = new QRCodeWriter().encode(url,BarcodeFormat.QR_CODE,400,400);
            bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            ImageIO.write(bufferedImage, "png", bao);
        } catch (WriterException e) {
            logger.error("Fail to encode QRcode, maybe URL is in bad format",e);
        } catch (IOException e) {
            logger.error("Fail to write bitmatrix in buffer",e);
        }
        return Base64.getEncoder().encodeToString(bao.toByteArray());
    }
    
    @Transient
    public void call(){
        calls.add(new Date());
    }

    @Transient
    public String getHashid() {
        return hashids.encode(id);
    }
    
    @Transient
    public static Long getIdFromHashid(final String hashid) {
        return hashids.decode(hashid)[0];
    }

}

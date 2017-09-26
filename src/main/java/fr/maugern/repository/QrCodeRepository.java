package fr.maugern.repository;

import fr.maugern.model.QrCode;
import org.springframework.data.jpa.repository.JpaRepository;

/** JPA repository for QRcode */
public interface QrCodeRepository extends JpaRepository<QrCode, Long>{

    /**
     * Finding qrcode by hashid
     * @param hashid User's hashid
     * @return QrCode with corresponding hash
     */
    public QrCode findByHashid(final String hashid);
}

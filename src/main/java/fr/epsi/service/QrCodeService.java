package fr.epsi.service;

import fr.epsi.entity.model.QrCode;

import java.util.Optional;

public interface QrCodeService {

    /**
     * Find QrCode by hash
     * @param hashid Hashid of QrCode
     * @return Qrcode
     */
    Optional<QrCode> findByHash(String hashid);

    /**
     * Make qrcode persistent in database
     * @param qrCode Qrcode to save
     * @return save qrcode, with generated Id and Hashid/salt
     */
    QrCode save(QrCode qrCode);

}

package fr.epsi.data;

import fr.epsi.model.QrCode;

import java.util.Optional;

/** QRcode DAO interface */
public interface QrCodeDao {

    /**
     * Make qrCode persistent in database
     * @param qrCode
     * @return an Optional QrCode
     */
    Optional<QrCode> save(QrCode qrCode);

    /**
     * Find qrCode by hashid
     * @param hashid
     * @return an optional qrCode
     */
    Optional<QrCode> findByHashid(String hashid);
}

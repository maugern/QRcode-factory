package fr.maugern.data;

import fr.maugern.model.QrCode;

import java.util.Optional;

/** QRcode DAO interface */
public interface QrCodeDao {

    /**
     * Make qrCode persistent in database
     * @param qrCode QrCode to save
     * @return an Optional QrCode
     */
    Optional<QrCode> save(QrCode qrCode);

    /**
     * Find qrCode by id
     * @param hashid User's id
     * @return an optional qrCode
     */
    Optional<QrCode> findById(Long hashid);
}

package fr.epsi.repository;

import fr.epsi.entity.model.QrCode;

import java.util.Optional;

/**
 * Interface of QrCode database access object
 */
public interface QrCodeDao {

    /**
     * Finding by id in database
     * @param id QrCode Id
     * @return QrCode in Optional
     */
    QrCode findById(Long id);

    /**
     * Finding by hash in database
     * @param hash QrCode Hash
     * @return QrCode in Optional
     */
    Optional<QrCode> findByHash(String hash);

    /**
     * Make QrCode persistent in database
     * @param qrCode qrcode to save
     * @return the saved Qrcode
     */
    QrCode save(QrCode qrCode);

    /**
     * Delete Qrcode in database
     * @param qrCode Qrcode to delete
     */
    void delete(QrCode qrCode);
}

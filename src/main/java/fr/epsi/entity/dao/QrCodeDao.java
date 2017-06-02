package fr.epsi.entity.dao;

import fr.epsi.entity.model.QrCode;

import java.util.Optional;

/**
 * TODO documentation
 */
public interface QrCodeDao {

    Optional<QrCode> findById(Long id);

    Optional<QrCode> findByHash(String hash);

    QrCode save(QrCode qrCode);

    void delete(QrCode qrCode);
}

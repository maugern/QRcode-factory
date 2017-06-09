package fr.epsi.data.impl;

import fr.epsi.data.QrCodeDao;
import fr.epsi.model.QrCode;
import fr.epsi.repository.QrCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/** Implementation of QrCodeDao repository */
@Repository
public class QrCodeDaoImpl implements QrCodeDao {

    @Autowired
    private QrCodeRepository qrCodeRepository;

    /** {@inheritDoc} */
    @Override
    public Optional<QrCode> save(final QrCode qrCode) {
        return Optional.ofNullable(qrCodeRepository.save(qrCode));
    }

    /** {@inheritDoc} */
    @Override
    public Optional<QrCode> findByHashid(final String hashid) {
        return Optional.ofNullable(qrCodeRepository.findByHashid(hashid));
    }
}

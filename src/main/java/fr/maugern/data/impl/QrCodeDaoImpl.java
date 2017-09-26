package fr.maugern.data.impl;

import fr.maugern.data.QrCodeDao;
import fr.maugern.model.QrCode;
import fr.maugern.repository.QrCodeRepository;
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
    public Optional<QrCode> findById(Long hashid) {
        return Optional.of(qrCodeRepository.findOne(hashid));
    }
}

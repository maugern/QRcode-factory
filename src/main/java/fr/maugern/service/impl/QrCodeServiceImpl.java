package fr.maugern.service.impl;

import fr.maugern.data.QrCodeDao;
import fr.maugern.model.QrCode;
import fr.maugern.model.User;
import fr.maugern.service.QrCodeService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** QrCode service implementation */
@Service
public class QrCodeServiceImpl implements QrCodeService {

    @Autowired
    private QrCodeDao qrCodeDao;

    /** {@inheritDoc} */
    @Override
    public Optional<QrCode> save(QrCode qrcode) {
        return qrCodeDao.save(qrcode);
    }

    /** {@inheritDoc} */
    @Override
    public Optional<QrCode> findById(final Long hashid) {
        return qrCodeDao.findById(hashid);
    }

    /** {@inheritDoc} */
    @Override
    public List<QrCode> findByAuthor(final User user) {
        return qrCodeDao.findByAuthor(user);
    }

}

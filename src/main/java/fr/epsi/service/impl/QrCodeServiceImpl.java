package fr.epsi.service.impl;

import fr.epsi.data.QrCodeDao;
import fr.epsi.model.QrCode;
import fr.epsi.service.QrCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** QrCode service implementation */
@Service
public class QrCodeServiceImpl implements QrCodeService {

    @Autowired
    private QrCodeDao qrCodeDao;

    /** {@inheritDoc} */
    @Override
    public QrCode save(final QrCode qrcode) {
        return qrCodeDao.save(qrcode).get();
    }

    /** {@inheritDoc} */
    @Override
    public QrCode findByHashid(final String hashid) {
        return qrCodeDao.findByHashid(hashid).get();
    }

}

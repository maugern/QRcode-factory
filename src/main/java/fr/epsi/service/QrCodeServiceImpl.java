package fr.epsi.service;

import fr.epsi.repository.QrCodeDao;
import fr.epsi.entity.model.QrCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("qrCodeService")
public class QrCodeServiceImpl implements QrCodeService {

    @Autowired
    private QrCodeDao qrCodeDao;

    @Override
    public Optional<QrCode> findByHash(final String hashid) {
        return qrCodeDao.findByHash(hashid);
    }

    @Override
    public QrCode save(QrCode qrCode) {
        return qrCodeDao.save(qrCode);
    }
}

/*
 * QrCode-factory, short link generator ditributed by QRcode
 * Copyright (C) 2017 Nicolas Mauger <https://maugern.fr>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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

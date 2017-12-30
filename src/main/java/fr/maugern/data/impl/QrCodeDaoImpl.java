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

package fr.maugern.data.impl;

import fr.maugern.data.QrCodeDao;
import fr.maugern.model.QrCode;
import fr.maugern.model.User;
import fr.maugern.repository.QrCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/** Implementation of QrCodeDao repository */
@Repository
public class QrCodeDaoImpl implements QrCodeDao {

    @Autowired
    private QrCodeRepository qrCodeRepository;

    /** {@inheritDoc} */
    @Override
    public Optional<QrCode> save(QrCode qrCode) {
        return Optional.ofNullable(qrCodeRepository.save(qrCode));
    }

    /** {@inheritDoc} */
    @Override
    public Optional<QrCode> findById(final Long hashid) {
        return Optional.of(qrCodeRepository.findOne(hashid));
    }

    /** {@inheritDoc} */
    @Override
    public List<QrCode> findByAuthor(final User user) {
        return qrCodeRepository.findByAuthor(user);
    }
}

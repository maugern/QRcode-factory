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

import fr.maugern.data.UserDao;
import fr.maugern.model.User;
import fr.maugern.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/** Implementation of UserDao repository */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserRepository userRepository;

    /** {@inheritDoc} */
    @Override
    public Optional<User> save(final User user) {
        return Optional.ofNullable(userRepository.save(user));
    }

    /** {@inheritDoc} */
    @Override
    public Optional<User> findByUsername(final String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }
}

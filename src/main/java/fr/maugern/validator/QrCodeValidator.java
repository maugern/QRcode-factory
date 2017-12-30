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

package fr.maugern.validator;

import fr.maugern.model.QrCode;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/** QRcode code validator */
@Component
public class QrCodeValidator implements Validator {

    /** {@inheritDoc} */
    @Override
    public boolean supports(Class<?> aClass) {
        return QrCode.class.equals(aClass);
    }

    /** {@inheritDoc} */
    @Override
    public void validate(final Object o, Errors errors) {
        QrCode qrcode = (QrCode) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "url", "NotEmpty");
        if (!qrcode.getUrl().matches(ValidationConstants.REGEX_URI)) {
            errors.rejectValue("url", "Form.qrcodeForm.url");
        }

        if (qrcode.getUrl().length() > ValidationConstants.MAX_URL_LENGTH) {
            errors.rejectValue("url", "Size.qrcodeForm.url");
        }
    }
}

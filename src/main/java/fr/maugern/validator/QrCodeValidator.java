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

package fr.epsi.validator;

import fr.epsi.model.QrCode;
import fr.epsi.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class QrCodeValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        QrCode qrcode = (QrCode) o;

        String uriRegex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "url", "NotEmpty");
        if (!qrcode.getUrl().matches(uriRegex)) {
            errors.rejectValue("url", "Form.qrcodeForm.url");
        }

        if (qrcode.getUrl().length() > 4000) {
            errors.rejectValue("url", "Size.qrcodeForm.url");
        }
    }
}

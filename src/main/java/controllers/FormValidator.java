package controllers;

import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("formValidator")
public class FormValidator implements Validator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?\\d{10,15}$");

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String componentId = component.getId();
        if ("name".equals(componentId)) {
            validateName(context, component, value);
        } else if ("email".equals(componentId)) {
            validateEmail(context, component, value);
        } else if ("address".equals(componentId)) {
            validateAddress(context, component, value);
        } else if ("phone".equals(componentId)) {
            validatePhone(context, component, value);
        }
    }

    public void validateName(FacesContext context, UIComponent component, Object value) {
        String name = (String) value;
        if (name == null || name.trim().isEmpty()) {
            throw new ValidatorException(new FacesMessage("Name is required."));
        } else if (name.length() < 2 || name.length() > 50) {
            throw new ValidatorException(new FacesMessage("Name must be between 2 and 50 characters."));
        }
    }

    public void validateEmail(FacesContext context, UIComponent component, Object value) {
        String email = (String) value;
        if (email == null || email.trim().isEmpty()) {
            throw new ValidatorException(new FacesMessage("Email is required."));
        } else if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new ValidatorException(new FacesMessage("Invalid email format."));
        }
    }

    public void validateAddress(FacesContext context, UIComponent component, Object value) {
        String address = (String) value;
        if (address == null || address.trim().isEmpty()) {
            throw new ValidatorException(new FacesMessage("Address is required."));
        } else if (address.length() < 5 || address.length() > 100) {
            throw new ValidatorException(new FacesMessage("Address must be between 5 and 100 characters."));
        }
    }

    public void validatePhone(FacesContext context, UIComponent component, Object value) {
        String phone = (String) value;
        if (phone == null || phone.trim().isEmpty()) {
            throw new ValidatorException(new FacesMessage("Phone number is required."));
        } else if (!PHONE_PATTERN.matcher(phone).matches()) {
            throw new ValidatorException(new FacesMessage("Invalid phone number format."));
        }
    }
}

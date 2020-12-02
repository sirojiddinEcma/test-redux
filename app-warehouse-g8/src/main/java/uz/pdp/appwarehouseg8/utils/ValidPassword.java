package uz.pdp.appwarehouseg8.utils;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface ValidPassword {

    String password();

    String prePassword();
}

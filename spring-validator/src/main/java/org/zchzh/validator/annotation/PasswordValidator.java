package org.zchzh.validator.annotation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.util.regex.Pattern;

/**
 * @author zengchzh
 * @date 2021/8/11
 *
 * 密码限制
 */

@Documented
@Constraint(validatedBy = PasswordValidator.CheckWeakPasswordValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidator {

    String message() default "密码不符合要求，需要包含数字和字母，且长度不小于8，不大于20";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class CheckWeakPasswordValidator implements ConstraintValidator<PasswordValidator, String> {

        @Override
        public void initialize(PasswordValidator constraintAnnotation) {

        }

        @Override
        public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
            if (s.length() < 8 || s.length() > 20){
                return false;
            }
            return Pattern.matches("^(?![a-zA-z]+$)(?!\\d+$)(?![!@#$%^&*]+$)[a-zA-Z\\d!@#$%^&*]+$",s);
        }
    }
}

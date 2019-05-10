package com.springboot.validation.customized.utils.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @ClassName: DateTimeValidator.java
 * @Description: TODO
 * @Author: booxj
 * @CreateDate 2019/5/9 19:05
 * @version:
 */
public class DateTimeValidator implements ConstraintValidator<DateTime, String> {

    private DateTime dateTime;

    @Override
    public void initialize(DateTime constraintAnnotation) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 如果 value 为空则不进行格式验证，为空验证可以使用 @NotBlank @NotNull @NotEmpty 等注解来进行控制，职责分离
        if (value == null||"".equals(value)) {
            return true;
        }
        String format = dateTime.format();
        if (value.length() != format.length()) {
            return false;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            simpleDateFormat.parse(value);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}

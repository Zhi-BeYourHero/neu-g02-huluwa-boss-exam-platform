package com.boss.bes.exam.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 手机号验证
 * @create 2020-07-07 18:03
 * @since 1.0
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {MobileValidator.class })
public @interface Mobile {
	
	boolean required() default true;
	
	String message() default "手机号格式错误";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}

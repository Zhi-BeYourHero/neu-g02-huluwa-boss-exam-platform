package com.boss.bes.exam.validator;

import com.boss.bes.exam.util.ValidatorUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 手机号验证器
 * @create 2020-07-07 18:03
 * @since 1.0
 */
public class MobileValidator implements ConstraintValidator<Mobile, String> {

	private boolean required = false;
	
	@Override
	public void initialize(Mobile constraintAnnotation) {
		required = constraintAnnotation.required();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (required) {
			return ValidatorUtil.isMobile(value);
		} else {
			if (StringUtils.isEmpty(value)) {
				return true;
			} else {
				return ValidatorUtil.isMobile(value);
			}
		}
	}
}

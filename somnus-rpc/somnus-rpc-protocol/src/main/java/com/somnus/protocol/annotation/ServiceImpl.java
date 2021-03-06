package com.somnus.protocol.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TODO
 * 
 * @author ZHUYUHUA129
 * @version 0.0.1
 * @since 2015年11月19日 下午3:23:40
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceImpl {

	public String lookUP() default AnnotationUtil.DEFAULT_VALUE;
}

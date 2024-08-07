package org.beko.annotations;

import org.beko.model.types.ActionType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Auditable {
    String login() default "";
    String userId() default "";
    ActionType actionType();
}
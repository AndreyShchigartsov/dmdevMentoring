package ru.sbercraft.service.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.sbercraft.service.validation.impl.UsernameUniqueValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UsernameUniqueValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface UsernameUnique {

    String message() default "Username должен быть уникальный!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}






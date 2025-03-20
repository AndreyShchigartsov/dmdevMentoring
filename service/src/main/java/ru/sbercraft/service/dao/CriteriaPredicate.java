package ru.sbercraft.service.dao;

import com.querydsl.core.types.ExpressionUtils;
import jakarta.persistence.criteria.Predicate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CriteriaPredicate {

    private final List<Predicate> predicates = new ArrayList<>();

    public static CriteriaPredicate builder() {
        return new CriteriaPredicate();
    }

    public <T> CriteriaPredicate add(T object, Function<T, Predicate> function) {
        if (object != null) {
            predicates.add(function.apply(object));
        }
        return this;
    }

//    public Predicate buildAnd() {
//        return Predicate.BooleanOperator.AND.predicates;
//    }

//    public Predicate buildOr() {
//        return ExpressionUtils.anyOf(predicates);
//    }
}

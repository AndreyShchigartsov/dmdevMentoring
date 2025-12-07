package ru.vita.service.repository.querydsl;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import ru.vita.service.repository.QPredicate;
import ru.vita.service.dto.excursion.ExcursionsFilter;
import ru.vita.service.entity.Excursion;

import java.util.List;

import static ru.vita.service.entity.QExcursion.excursion;

@RequiredArgsConstructor
public class FilterExcursionRepositoryImpl implements FilterExcursionRepository {

    private final EntityManager entityManager;

    /**
     * @return услугу по name
     */
    public Excursion findByName(String name) {
        return new JPAQuery<Excursion>(entityManager)
                .select(excursion)
                .from(excursion)
                .where(excursion.service.eq(name))
                .fetchOne();
    }

    /**
     * @return list всех дополнительных услуг
     */
    public List<Excursion> findAll() {
        return new JPAQuery<Excursion>(entityManager)
                .select(excursion)
                .from(excursion)
                .fetch();
    }

    /**
     * @return list всех доп услуг от n суммы по возрастанию
     */
    public List<Excursion> findPrice(Integer price) {
        return new JPAQuery<Excursion>(entityManager)
                .select(excursion)
                .from(excursion)
                .where(excursion.price.gt(price))
                .fetch();
    }

    /**
     * @return list доп услуг по фильтру структуры и названию услуги
     */
    public List<Excursion> findAllQueryDslFilter(ExcursionsFilter filter) {
        Predicate predicate = QPredicate.builder()
                .add(filter.getName(), excursion.service::eq)
                .add(filter.getStructureDivision(), excursion.structureDivision::eq)
                .buildAnd();

        return new JPAQuery<Excursion>(entityManager)
                .select(excursion)
                .from(excursion)
                .where(predicate)
                .fetch();
    }
}

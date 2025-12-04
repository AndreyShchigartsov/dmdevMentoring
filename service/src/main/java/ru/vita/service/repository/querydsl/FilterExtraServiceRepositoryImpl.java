package ru.vita.service.repository.querydsl;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import ru.vita.service.repository.QPredicate;
import ru.vita.service.dto.extra.services.ExtraServicesFilter;
import ru.vita.service.entity.ExtraService;

import java.util.List;

import static ru.vita.service.entity.QExtraService.extraService;

@RequiredArgsConstructor
public class FilterExtraServiceRepositoryImpl implements FilterExtraServiceRepository {

    private final EntityManager entityManager;

    /**
     * @return услугу по name
     */
    public ExtraService findByName(String name) {
        return new JPAQuery<ExtraService>(entityManager)
                .select(extraService)
                .from(extraService)
                .where(extraService.service.eq(name))
                .fetchOne();
    }

    /**
     * @return list всех дополнительных услуг
     */
    public List<ExtraService> findAll() {
        return new JPAQuery<ExtraService>(entityManager)
                .select(extraService)
                .from(extraService)
                .fetch();
    }

    /**
     * @return list всех доп услуг от n суммы по возрастанию
     */
    public List<ExtraService> findPrice(Integer price) {
        return new JPAQuery<ExtraService>(entityManager)
                .select(extraService)
                .from(extraService)
                .where(extraService.price.gt(price))
                .fetch();
    }

    /**
     * @return list доп услуг по фильтру структуры и названию услуги
     */
    public List<ExtraService> findAllQueryDslFilter(ExtraServicesFilter filter) {
        Predicate predicate = QPredicate.builder()
                .add(filter.getName(), extraService.service::eq)
                .add(filter.getStructureDivision(), extraService.structureDivision::eq)
                .buildAnd();

        return new JPAQuery<ExtraService>(entityManager)
                .select(extraService)
                .from(extraService)
                .where(predicate)
                .fetch();
    }
}

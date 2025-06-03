package ru.sbercraft.service.repository.querydsl;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;
import ru.sbercraft.service.repository.QPredicate;
import ru.sbercraft.service.dto.extra.services.ExtraServicesFilter;
import ru.sbercraft.service.entity.ExtraService;

import java.util.List;

import static java.util.Collections.emptyList;
import static ru.sbercraft.service.entity.QExtraService.extraService;


@RequiredArgsConstructor
public class FilterExtraServiceRepositoryImpl implements FilterExtraServiceRepository {

    private final EntityManager entityManager;

    /**
     * @return услугу по name
     */
    public List<ExtraService> findByName(String name) {
        return new JPAQuery<ExtraService>(entityManager)
                .select(extraService)
                .from(extraService)
//                .where(predicate)
                .fetch();
    }

    /**
     * @return list всех дополнительных услуг
     */
    public List<ExtraService> findAllQueryDsl() {
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
     * @return list всех доп услуг от n суммы по возрастанию
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

    /**
     * @return list всех доп услуг от n суммы по возрастанию
     */
    public List<ExtraService> findAllCriteriaApiFilter(ExtraServicesFilter filter) {
        Predicate predicate = QPredicate.builder()
                .add(filter.getName(), extraService.service::eq)
                .add(filter.getStructureDivision(), extraService.structureDivision::eq)
                .buildAnd();

//        CriteriaQuery<ExtraService> criteria = entityManager.getCriteriaBuilder().createQuery(ExtraService.class);
//        criteria.from(ExtraService.class);
//        criteria.where(predicate);
//        return entityManager.createQuery(criteria).getResultList();
        return emptyList();
    }
}

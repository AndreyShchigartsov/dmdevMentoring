package ru.sbercraft.service.dao;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.sbercraft.service.dto.ExtraServicesFilter;
import ru.sbercraft.service.entity.ExtraService;

import java.util.List;

import static ru.sbercraft.service.entity.QExtraService.*;

@Repository
public class ExtraServiceDao extends BaseDao<Integer, ExtraService>{

    private final Session session;

    public ExtraServiceDao(Session session) {
        super(ExtraService.class, session);
        this.session = session;
    }

    /**
     * @return услугу по name
     */
    public List<ExtraService> findByName(String name) {
        return new JPAQuery<ExtraService>(session)
                .select(extraService)
                .from(extraService)
//                .where(predicate)
                .fetch();
    }

    /**
     * @return list всех дополнительных услуг
     */
    public List<ExtraService> findAllQueryDsl() {
        return new JPAQuery<ExtraService>(session)
                .select(extraService)
                .from(extraService)
                .fetch();
    }

    /**
     * @return list всех доп услуг от n суммы по возрастанию
     */
    public List<ExtraService> findPrice(Integer price) {
        return new JPAQuery<ExtraService>(session)
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
                .add(filter.getName(), extraService.name::eq)
                .add(filter.getStructureDivision(), extraService.structureDivision::eq)
                .buildAnd();

        return new JPAQuery<ExtraService>(session)
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
                .add(filter.getName(), extraService.name::eq)
                .add(filter.getStructureDivision(), extraService.structureDivision::eq)
                .buildAnd();

        CriteriaQuery<ExtraService> criteria = session.getCriteriaBuilder().createQuery(ExtraService.class);
        criteria.from(ExtraService.class);
//        criteria.where(predicate);
        return session.createQuery(criteria).getResultList();
    }
}

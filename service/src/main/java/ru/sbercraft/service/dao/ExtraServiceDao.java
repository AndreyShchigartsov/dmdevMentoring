package ru.sbercraft.service.dao;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.Session;
import ru.sbercraft.service.dto.ExtraServicesFilter;
import ru.sbercraft.service.entity.ExtraService;

import java.util.List;

import static ru.sbercraft.service.entity.QExtraService.*;

public class ExtraServiceDao {

    /**
     * @return услугу по name
     */
    public List<ExtraService> findByName(Session session, String name) {
        return new JPAQuery<ExtraService>(session)
                .select(extraService)
                .from(extraService)
//                .where(predicate)
                .fetch();
    }

    /**
     * @return list всех дополнительных услуг
     */
    public List<ExtraService> findAll(Session session) {
        return new JPAQuery<ExtraService>(session)
                .select(extraService)
                .from(extraService)
                .fetch();
    }

    /**
     * @return list всех доп услуг от n суммы по возрастанию
     */
    public List<ExtraService> findPrice(Session session, Integer price) {
        return new JPAQuery<ExtraService>(session)
                .select(extraService)
                .from(extraService)
                .where(extraService.price.gt(price))
                .fetch();
    }

    /**
     * @return list всех доп услуг от n суммы по возрастанию
     */
    public List<ExtraService> findAll(Session session, ExtraServicesFilter filter) {
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
}

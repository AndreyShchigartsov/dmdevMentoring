package ru.sbercraft.service.dao.querydsl;

import ru.sbercraft.service.dto.ExtraServicesFilter;
import ru.sbercraft.service.entity.ExtraService;

import java.util.List;

public interface FilterExtraServiceDao {

    /**
     * @return услугу по name
     */
    List<ExtraService> findByName(String name);

    /**
     * @return list всех дополнительных услуг
     */
    List<ExtraService> findAllQueryDsl();

    /**
     * @return list всех доп услуг от n суммы по возрастанию
     */
    List<ExtraService> findPrice(Integer price);

    /**
     * @return list всех доп услуг от n суммы по возрастанию
     */
    List<ExtraService> findAllQueryDslFilter(ExtraServicesFilter filter);

    /**
     * @return list всех доп услуг от n суммы по возрастанию
     */
    List<ExtraService> findAllCriteriaApiFilter(ExtraServicesFilter filter);

}

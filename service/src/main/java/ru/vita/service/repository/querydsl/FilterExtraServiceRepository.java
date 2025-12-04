package ru.vita.service.repository.querydsl;

import ru.vita.service.dto.extra.services.ExtraServicesFilter;
import ru.vita.service.entity.ExtraService;

import java.util.List;

public interface FilterExtraServiceRepository {

    /**
     * @return услугу по name
     */
    ExtraService findByName(String name);

    /**
     * @return list всех дополнительных услуг
     */
    List<ExtraService> findAll();

    /**
     * @return list всех доп услуг от n суммы по возрастанию
     */
    List<ExtraService> findPrice(Integer price);

    /**
     * @return list доп услуг по фильтру структуры и названию услуги
     */
    List<ExtraService> findAllQueryDslFilter(ExtraServicesFilter filter);

}

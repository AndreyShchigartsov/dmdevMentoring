package ru.vita.service.repository.querydsl;

import ru.vita.service.dto.excursion.ExcursionsFilter;
import ru.vita.service.entity.Excursion;

import java.util.List;

public interface FilterExcursionRepository {

    /**
     * @return услугу по name
     */
    Excursion findByName(String name);

    /**
     * @return list всех дополнительных услуг
     */
    List<Excursion> findAll();

    /**
     * @return list всех доп услуг от n суммы по возрастанию
     */
    List<Excursion> findPrice(Integer price);

    /**
     * @return list доп услуг по фильтру структуры и названию услуги
     */
    List<Excursion> findAllQueryDslFilter(ExcursionsFilter filter);

}

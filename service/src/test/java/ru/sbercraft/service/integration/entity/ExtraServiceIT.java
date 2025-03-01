package ru.sbercraft.service.integration.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbercraft.service.entity.ExtraService;
import ru.sbercraft.service.entity.StructureDivision;
import ru.sbercraft.service.entity.enums.Structure;
import ru.sbercraft.service.integration.HibernateTestUtil;

import java.time.Duration;

import static org.assertj.core.api.Assertions.*;

class ExtraServiceIT {
    private static final SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();

    private Session session;

    @BeforeEach
    void init() {
        session = sessionFactory.openSession();
        session.beginTransaction();
    }

    @AfterEach
    void delete() {
        session.getTransaction().rollback();
        session.close();
    }

    @AfterAll
    static void afterAll() {
        sessionFactory.close();
    }

    @Test
    void checkSave() {
        StructureDivision structureDivision = createStuctureDivision();
        ExtraService extraService = createExtraService();
        extraService.setStructureDivision(structureDivision);
        session.persist(extraService);
        session.clear();

        ExtraService responseExtraService = session.get(ExtraService.class, 1L);

        assertThat(responseExtraService.getStructureDivision()).isEqualTo(null);
        assertThat(responseExtraService.getName()).isEqualTo("Вата");
//        Assertions.assertThat(responseExtraService).isEqualTo(extraService);//todo почему тест проходит объекты же отличаются?
    }

    private StructureDivision createStuctureDivision() {
        return StructureDivision.builder()
                .typeStructure(Structure.ORGANIZATIONAL)
                .name("Экскурсвита")
                .build();

    }

    private ExtraService createExtraService() {
        return ExtraService.builder()
                .name("Вата")
                .price(300)
                .duration(Duration.ofMinutes(30))
                .build();
    }
}
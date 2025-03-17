package ru.sbercraft.service.listener;

import org.hibernate.event.spi.AbstractPreDatabaseOperationEvent;
import org.hibernate.event.spi.PreDeleteEvent;
import org.hibernate.event.spi.PreDeleteEventListener;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import ru.sbercraft.service.entity.Audit;
import ru.sbercraft.service.entity.Audit.Operation;

//Жалко удалять, пусть еще нескольуо раз попадется на глаза для освежения памаяти)
public class AuditTableListener implements PreDeleteEventListener, PreInsertEventListener, PreUpdateEventListener {

    @Override
    public boolean onPreDelete(PreDeleteEvent event) {
        auditEntity(event, Operation.DELETE);
        return false;
    }

    @Override
    public boolean onPreInsert(PreInsertEvent event) {
        auditEntity(event, Operation.INSERT);
        return false;
    }

    @Override
    public boolean onPreUpdate(PreUpdateEvent event) {
        auditEntity(event, Operation.UPDATE);
        return false;
    }

    public void auditEntity(AbstractPreDatabaseOperationEvent event, Operation operation) {
        if (event.getEntity().getClass() != Audit.class) {
            Audit audit = Audit.builder()
                    .entityName(event.getPersister().getEntityName())
                    .operation(operation)
                    .build();
            event.getSession().persist(audit);
        }
    }
}

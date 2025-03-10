package ru.sbercraft.service.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWorker is a Querydsl query type for Worker
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWorker extends EntityPathBase<Worker> {

    private static final long serialVersionUID = -1194822949L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWorker worker = new QWorker("worker");

    public final QUser _super;

    //inherited
    public final BooleanPath active;

    //inherited
    public final StringPath firstname;

    //inherited
    public final NumberPath<Long> id;

    //inherited
    public final ListPath<Image, QImage> images;

    public final EnumPath<ru.sbercraft.service.entity.enums.JobPosition> jobPosition = createEnum("jobPosition", ru.sbercraft.service.entity.enums.JobPosition.class);

    //inherited
    public final StringPath lastname;

    //inherited
    public final StringPath login;

    //inherited
    public final StringPath password;

    // inherited
    public final QPersonalInformation personalInformation;

    //inherited
    public final DateTimePath<java.time.Instant> registrationDate;

    //inherited
    public final EnumPath<ru.sbercraft.service.entity.enums.Role> role;

    // inherited
    public final QRoom room;

    public final NumberPath<Integer> salary = createNumber("salary", Integer.class);

    //inherited
    public final ListPath<Schedule, QSchedule> schedules;

    // inherited
    public final QStructureDivision structureDivision;

    //inherited
    public final StringPath username;

    public QWorker(String variable) {
        this(Worker.class, forVariable(variable), INITS);
    }

    public QWorker(Path<? extends Worker> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWorker(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWorker(PathMetadata metadata, PathInits inits) {
        this(Worker.class, metadata, inits);
    }

    public QWorker(Class<? extends Worker> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QUser(type, metadata, inits);
        this.active = _super.active;
        this.firstname = _super.firstname;
        this.id = _super.id;
        this.images = _super.images;
        this.lastname = _super.lastname;
        this.login = _super.login;
        this.password = _super.password;
        this.personalInformation = _super.personalInformation;
        this.registrationDate = _super.registrationDate;
        this.role = _super.role;
        this.room = _super.room;
        this.schedules = _super.schedules;
        this.structureDivision = _super.structureDivision;
        this.username = _super.username;
    }

}


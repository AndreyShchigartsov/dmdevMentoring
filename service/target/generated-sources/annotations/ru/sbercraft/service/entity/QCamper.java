package ru.sbercraft.service.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCamper is a Querydsl query type for Camper
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCamper extends EntityPathBase<Camper> {

    private static final long serialVersionUID = -1780479413L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCamper camper = new QCamper("camper");

    public final QUser _super;

    //inherited
    public final BooleanPath active;

    public final DateTimePath<java.time.Instant> checkInDate = createDateTime("checkInDate", java.time.Instant.class);

    public final DateTimePath<java.time.Instant> departureDate = createDateTime("departureDate", java.time.Instant.class);

    //inherited
    public final StringPath firstname;

    //inherited
    public final NumberPath<Long> id;

    //inherited
    public final ListPath<Image, QImage> images;

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

    //inherited
    public final ListPath<Schedule, QSchedule> schedules;

    // inherited
    public final QStructureDivision structureDivision;

    //inherited
    public final StringPath username;

    public QCamper(String variable) {
        this(Camper.class, forVariable(variable), INITS);
    }

    public QCamper(Path<? extends Camper> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCamper(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCamper(PathMetadata metadata, PathInits inits) {
        this(Camper.class, metadata, inits);
    }

    public QCamper(Class<? extends Camper> type, PathMetadata metadata, PathInits inits) {
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


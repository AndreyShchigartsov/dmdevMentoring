package ru.sbercraft.service.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1301856664L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final BooleanPath active = createBoolean("active");

    public final StringPath firstname = createString("firstname");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<Image, QImage> images = this.<Image, QImage>createList("images", Image.class, QImage.class, PathInits.DIRECT2);

    public final StringPath lastname = createString("lastname");

    public final StringPath login = createString("login");

    public final StringPath password = createString("password");

    public final QPersonalInformation personalInformation;

    public final DateTimePath<java.time.Instant> registrationDate = createDateTime("registrationDate", java.time.Instant.class);

    public final EnumPath<ru.sbercraft.service.entity.enums.Role> role = createEnum("role", ru.sbercraft.service.entity.enums.Role.class);

    public final QRoom room;

    public final ListPath<Schedule, QSchedule> schedules = this.<Schedule, QSchedule>createList("schedules", Schedule.class, QSchedule.class, PathInits.DIRECT2);

    public final QStructureDivision structureDivision;

    public final StringPath username = createString("username");

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.personalInformation = inits.isInitialized("personalInformation") ? new QPersonalInformation(forProperty("personalInformation"), inits.get("personalInformation")) : null;
        this.room = inits.isInitialized("room") ? new QRoom(forProperty("room"), inits.get("room")) : null;
        this.structureDivision = inits.isInitialized("structureDivision") ? new QStructureDivision(forProperty("structureDivision"), inits.get("structureDivision")) : null;
    }

}


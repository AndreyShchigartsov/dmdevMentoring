package ru.sbercraft.service.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPersonalInformation is a Querydsl query type for PersonalInformation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPersonalInformation extends EntityPathBase<PersonalInformation> {

    private static final long serialVersionUID = -94237041L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPersonalInformation personalInformation = new QPersonalInformation("personalInformation");

    public final StringPath address = createString("address");

    public final StringPath birthCertificate = createString("birthCertificate");

    public final DatePath<java.time.LocalDate> birthDate = createDate("birthDate", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath passportData = createString("passportData");

    public final QUser user;

    public QPersonalInformation(String variable) {
        this(PersonalInformation.class, forVariable(variable), INITS);
    }

    public QPersonalInformation(Path<? extends PersonalInformation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPersonalInformation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPersonalInformation(PathMetadata metadata, PathInits inits) {
        this(PersonalInformation.class, metadata, inits);
    }

    public QPersonalInformation(Class<? extends PersonalInformation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}


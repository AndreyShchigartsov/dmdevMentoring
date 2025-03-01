package ru.sbercraft.service.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExtraService is a Querydsl query type for ExtraService
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExtraService extends EntityPathBase<ExtraService> {

    private static final long serialVersionUID = 495943202L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExtraService extraService = new QExtraService("extraService");

    public final ComparablePath<java.time.Duration> duration = createComparable("duration", java.time.Duration.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final QStructureDivision structureDivision;

    public QExtraService(String variable) {
        this(ExtraService.class, forVariable(variable), INITS);
    }

    public QExtraService(Path<? extends ExtraService> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExtraService(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExtraService(PathMetadata metadata, PathInits inits) {
        this(ExtraService.class, metadata, inits);
    }

    public QExtraService(Class<? extends ExtraService> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.structureDivision = inits.isInitialized("structureDivision") ? new QStructureDivision(forProperty("structureDivision"), inits.get("structureDivision")) : null;
    }

}


package ru.sbercraft.service.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStructureDivision is a Querydsl query type for StructureDivision
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStructureDivision extends EntityPathBase<StructureDivision> {

    private static final long serialVersionUID = -1025559613L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStructureDivision structureDivision = new QStructureDivision("structureDivision");

    public final ListPath<ExtraService, QExtraService> extraServices = this.<ExtraService, QExtraService>createList("extraServices", ExtraService.class, QExtraService.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final QStructureDivision parentId;

    public final ListPath<Room, QRoom> rooms = this.<Room, QRoom>createList("rooms", Room.class, QRoom.class, PathInits.DIRECT2);

    public final EnumPath<ru.sbercraft.service.entity.enums.Structure> typeStructure = createEnum("typeStructure", ru.sbercraft.service.entity.enums.Structure.class);

    public final ListPath<User, QUser> users = this.<User, QUser>createList("users", User.class, QUser.class, PathInits.DIRECT2);

    public QStructureDivision(String variable) {
        this(StructureDivision.class, forVariable(variable), INITS);
    }

    public QStructureDivision(Path<? extends StructureDivision> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStructureDivision(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStructureDivision(PathMetadata metadata, PathInits inits) {
        this(StructureDivision.class, metadata, inits);
    }

    public QStructureDivision(Class<? extends StructureDivision> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parentId = inits.isInitialized("parentId") ? new QStructureDivision(forProperty("parentId"), inits.get("parentId")) : null;
    }

}


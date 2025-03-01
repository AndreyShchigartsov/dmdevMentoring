package ru.sbercraft.service.entity;

import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import ru.sbercraft.service.entity.enums.Structure;

@StaticMetamodel(StructureDivision.class)
public abstract class StructureDivision_ {

	public static final String ROOMS = "rooms";
	public static final String TYPE_STRUCTURE = "typeStructure";
	public static final String EXTRA_SERVICES = "extraServices";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String PARENT_ID = "parentId";
	public static final String USERS = "users";

	
	/**
	 * @see ru.sbercraft.service.entity.StructureDivision#rooms
	 **/
	public static volatile ListAttribute<StructureDivision, Room> rooms;
	
	/**
	 * @see ru.sbercraft.service.entity.StructureDivision#typeStructure
	 **/
	public static volatile SingularAttribute<StructureDivision, Structure> typeStructure;
	
	/**
	 * @see ru.sbercraft.service.entity.StructureDivision#extraServices
	 **/
	public static volatile ListAttribute<StructureDivision, ExtraService> extraServices;
	
	/**
	 * @see ru.sbercraft.service.entity.StructureDivision#name
	 **/
	public static volatile SingularAttribute<StructureDivision, String> name;
	
	/**
	 * @see ru.sbercraft.service.entity.StructureDivision#id
	 **/
	public static volatile SingularAttribute<StructureDivision, Long> id;
	
	/**
	 * @see ru.sbercraft.service.entity.StructureDivision
	 **/
	public static volatile EntityType<StructureDivision> class_;
	
	/**
	 * @see ru.sbercraft.service.entity.StructureDivision#parentId
	 **/
	public static volatile SingularAttribute<StructureDivision, StructureDivision> parentId;
	
	/**
	 * @see ru.sbercraft.service.entity.StructureDivision#users
	 **/
	public static volatile ListAttribute<StructureDivision, User> users;

}


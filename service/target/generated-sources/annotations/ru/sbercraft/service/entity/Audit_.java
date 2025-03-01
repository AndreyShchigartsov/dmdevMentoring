package ru.sbercraft.service.entity;

import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import ru.sbercraft.service.entity.Audit.Operation;

@StaticMetamodel(Audit.class)
public abstract class Audit_ {

	public static final String ENTITY_NAME = "entityName";
	public static final String ID = "id";
	public static final String OPERATION = "operation";

	
	/**
	 * @see ru.sbercraft.service.entity.Audit#entityName
	 **/
	public static volatile SingularAttribute<Audit, String> entityName;
	
	/**
	 * @see ru.sbercraft.service.entity.Audit#id
	 **/
	public static volatile SingularAttribute<Audit, Long> id;
	
	/**
	 * @see ru.sbercraft.service.entity.Audit
	 **/
	public static volatile EntityType<Audit> class_;
	
	/**
	 * @see ru.sbercraft.service.entity.Audit#operation
	 **/
	public static volatile SingularAttribute<Audit, Operation> operation;

}


package ru.sbercraft.service.entity;

import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.Duration;

@StaticMetamodel(ExtraService.class)
public abstract class ExtraService_ {

	public static final String DURATION = "duration";
	public static final String STRUCTURE_DIVISION = "structureDivision";
	public static final String PRICE = "price";
	public static final String NAME = "name";
	public static final String ID = "id";

	
	/**
	 * @see ru.sbercraft.service.entity.ExtraService#duration
	 **/
	public static volatile SingularAttribute<ExtraService, Duration> duration;
	
	/**
	 * @see ru.sbercraft.service.entity.ExtraService#structureDivision
	 **/
	public static volatile SingularAttribute<ExtraService, StructureDivision> structureDivision;
	
	/**
	 * @see ru.sbercraft.service.entity.ExtraService#price
	 **/
	public static volatile SingularAttribute<ExtraService, Integer> price;
	
	/**
	 * @see ru.sbercraft.service.entity.ExtraService#name
	 **/
	public static volatile SingularAttribute<ExtraService, String> name;
	
	/**
	 * @see ru.sbercraft.service.entity.ExtraService#id
	 **/
	public static volatile SingularAttribute<ExtraService, Long> id;
	
	/**
	 * @see ru.sbercraft.service.entity.ExtraService
	 **/
	public static volatile EntityType<ExtraService> class_;

}


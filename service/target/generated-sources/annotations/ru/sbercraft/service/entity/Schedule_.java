package ru.sbercraft.service.entity;

import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.Instant;
import ru.sbercraft.service.entity.enums.Status;

@StaticMetamodel(Schedule.class)
public abstract class Schedule_ {

	public static final String DATE_TIME = "dateTime";
	public static final String STRUCTURE_DIVISION = "structureDivision";
	public static final String ACTION = "action";
	public static final String ID = "id";
	public static final String EVENT = "event";
	public static final String USER = "user";
	public static final String STATUS = "status";

	
	/**
	 * @see ru.sbercraft.service.entity.Schedule#dateTime
	 **/
	public static volatile SingularAttribute<Schedule, Instant> dateTime;
	
	/**
	 * @see ru.sbercraft.service.entity.Schedule#structureDivision
	 **/
	public static volatile SingularAttribute<Schedule, StructureDivision> structureDivision;
	
	/**
	 * @see ru.sbercraft.service.entity.Schedule#action
	 **/
	public static volatile SingularAttribute<Schedule, String> action;
	
	/**
	 * @see ru.sbercraft.service.entity.Schedule#id
	 **/
	public static volatile SingularAttribute<Schedule, Long> id;
	
	/**
	 * @see ru.sbercraft.service.entity.Schedule#event
	 **/
	public static volatile SingularAttribute<Schedule, Event> event;
	
	/**
	 * @see ru.sbercraft.service.entity.Schedule
	 **/
	public static volatile EntityType<Schedule> class_;
	
	/**
	 * @see ru.sbercraft.service.entity.Schedule#user
	 **/
	public static volatile SingularAttribute<Schedule, User> user;
	
	/**
	 * @see ru.sbercraft.service.entity.Schedule#status
	 **/
	public static volatile SingularAttribute<Schedule, Status> status;

}


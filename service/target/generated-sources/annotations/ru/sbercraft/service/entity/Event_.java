package ru.sbercraft.service.entity;

import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import ru.sbercraft.service.entity.enums.CategoryEvent;

@StaticMetamodel(Event.class)
public abstract class Event_ {

	public static final String SCHEDULES = "schedules";
	public static final String GRAPH_EVENT_SCHEDULES = "EventSchedules";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String CATEGORY = "category";
	public static final String GRAPH_EVENT_SCHEDULES_AND_USER = "EventSchedulesAndUser";

	
	/**
	 * @see ru.sbercraft.service.entity.Event#schedules
	 **/
	public static volatile ListAttribute<Event, Schedule> schedules;
	
	/**
	 * @see ru.sbercraft.service.entity.Event#name
	 **/
	public static volatile SingularAttribute<Event, String> name;
	
	/**
	 * @see ru.sbercraft.service.entity.Event#id
	 **/
	public static volatile SingularAttribute<Event, Integer> id;
	
	/**
	 * @see ru.sbercraft.service.entity.Event#category
	 **/
	public static volatile SingularAttribute<Event, CategoryEvent> category;
	
	/**
	 * @see ru.sbercraft.service.entity.Event
	 **/
	public static volatile EntityType<Event> class_;

}


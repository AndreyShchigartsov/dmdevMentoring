package ru.sbercraft.service.entity;

import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.Instant;

@StaticMetamodel(Camper.class)
public abstract class Camper_ extends ru.sbercraft.service.entity.User_ {

	public static final String DEPARTURE_DATE = "departureDate";
	public static final String CHECK_IN_DATE = "checkInDate";

	
	/**
	 * @see ru.sbercraft.service.entity.Camper#departureDate
	 **/
	public static volatile SingularAttribute<Camper, Instant> departureDate;
	
	/**
	 * @see ru.sbercraft.service.entity.Camper
	 **/
	public static volatile EntityType<Camper> class_;
	
	/**
	 * @see ru.sbercraft.service.entity.Camper#checkInDate
	 **/
	public static volatile SingularAttribute<Camper, Instant> checkInDate;

}


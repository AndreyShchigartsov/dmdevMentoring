package ru.sbercraft.service.entity;

import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import ru.sbercraft.service.entity.enums.JobPosition;

@StaticMetamodel(Worker.class)
public abstract class Worker_ extends ru.sbercraft.service.entity.User_ {

	public static final String SALARY = "salary";
	public static final String JOB_POSITION = "jobPosition";

	
	/**
	 * @see ru.sbercraft.service.entity.Worker#salary
	 **/
	public static volatile SingularAttribute<Worker, Integer> salary;
	
	/**
	 * @see ru.sbercraft.service.entity.Worker
	 **/
	public static volatile EntityType<Worker> class_;
	
	/**
	 * @see ru.sbercraft.service.entity.Worker#jobPosition
	 **/
	public static volatile SingularAttribute<Worker, JobPosition> jobPosition;

}


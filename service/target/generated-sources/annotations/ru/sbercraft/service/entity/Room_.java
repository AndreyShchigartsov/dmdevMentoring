package ru.sbercraft.service.entity;

import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Room.class)
public abstract class Room_ {

	public static final String ROOM_NUMBER = "roomNumber";
	public static final String STRUCTURE_DIVISION = "structureDivision";
	public static final String SEATS_VALUE = "seatsValue";
	public static final String ID = "id";
	public static final String USERS = "users";

	
	/**
	 * @see ru.sbercraft.service.entity.Room#roomNumber
	 **/
	public static volatile SingularAttribute<Room, Integer> roomNumber;
	
	/**
	 * @see ru.sbercraft.service.entity.Room#structureDivision
	 **/
	public static volatile SingularAttribute<Room, StructureDivision> structureDivision;
	
	/**
	 * @see ru.sbercraft.service.entity.Room#seatsValue
	 **/
	public static volatile SingularAttribute<Room, Integer> seatsValue;
	
	/**
	 * @see ru.sbercraft.service.entity.Room#id
	 **/
	public static volatile SingularAttribute<Room, Integer> id;
	
	/**
	 * @see ru.sbercraft.service.entity.Room
	 **/
	public static volatile EntityType<Room> class_;
	
	/**
	 * @see ru.sbercraft.service.entity.Room#users
	 **/
	public static volatile ListAttribute<Room, User> users;

}


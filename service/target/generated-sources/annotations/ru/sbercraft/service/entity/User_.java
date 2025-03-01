package ru.sbercraft.service.entity;

import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.Instant;
import ru.sbercraft.service.entity.enums.Role;

@StaticMetamodel(User.class)
public abstract class User_ {

	public static final String FIRSTNAME = "firstname";
	public static final String IMAGES = "images";
	public static final String ROLE = "role";
	public static final String PERSONAL_INFORMATION = "personalInformation";
	public static final String ACTIVE = "active";
	public static final String LOGIN = "login";
	public static final String ROOM = "room";
	public static final String LASTNAME = "lastname";
	public static final String PASSWORD = "password";
	public static final String STRUCTURE_DIVISION = "structureDivision";
	public static final String SCHEDULES = "schedules";
	public static final String REGISTRATION_DATE = "registrationDate";
	public static final String ID = "id";
	public static final String USERNAME = "username";

	
	/**
	 * @see ru.sbercraft.service.entity.User#firstname
	 **/
	public static volatile SingularAttribute<User, String> firstname;
	
	/**
	 * @see ru.sbercraft.service.entity.User#images
	 **/
	public static volatile ListAttribute<User, Image> images;
	
	/**
	 * @see ru.sbercraft.service.entity.User#role
	 **/
	public static volatile SingularAttribute<User, Role> role;
	
	/**
	 * @see ru.sbercraft.service.entity.User#personalInformation
	 **/
	public static volatile SingularAttribute<User, PersonalInformation> personalInformation;
	
	/**
	 * @see ru.sbercraft.service.entity.User#active
	 **/
	public static volatile SingularAttribute<User, Boolean> active;
	
	/**
	 * @see ru.sbercraft.service.entity.User#login
	 **/
	public static volatile SingularAttribute<User, String> login;
	
	/**
	 * @see ru.sbercraft.service.entity.User#room
	 **/
	public static volatile SingularAttribute<User, Room> room;
	
	/**
	 * @see ru.sbercraft.service.entity.User#lastname
	 **/
	public static volatile SingularAttribute<User, String> lastname;
	
	/**
	 * @see ru.sbercraft.service.entity.User#password
	 **/
	public static volatile SingularAttribute<User, String> password;
	
	/**
	 * @see ru.sbercraft.service.entity.User#structureDivision
	 **/
	public static volatile SingularAttribute<User, StructureDivision> structureDivision;
	
	/**
	 * @see ru.sbercraft.service.entity.User#schedules
	 **/
	public static volatile ListAttribute<User, Schedule> schedules;
	
	/**
	 * @see ru.sbercraft.service.entity.User#registrationDate
	 **/
	public static volatile SingularAttribute<User, Instant> registrationDate;
	
	/**
	 * @see ru.sbercraft.service.entity.User#id
	 **/
	public static volatile SingularAttribute<User, Long> id;
	
	/**
	 * @see ru.sbercraft.service.entity.User
	 **/
	public static volatile EntityType<User> class_;
	
	/**
	 * @see ru.sbercraft.service.entity.User#username
	 **/
	public static volatile SingularAttribute<User, String> username;

}


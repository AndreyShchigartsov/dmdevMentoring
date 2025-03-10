package ru.sbercraft.service.entity;

import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;

@StaticMetamodel(PersonalInformation.class)
public abstract class PersonalInformation_ {

	public static final String PASSPORT_DATA = "passportData";
	public static final String ADDRESS = "address";
	public static final String BIRTH_CERTIFICATE = "birthCertificate";
	public static final String ID = "id";
	public static final String BIRTH_DATE = "birthDate";
	public static final String USER = "user";

	
	/**
	 * @see ru.sbercraft.service.entity.PersonalInformation#passportData
	 **/
	public static volatile SingularAttribute<PersonalInformation, String> passportData;
	
	/**
	 * @see ru.sbercraft.service.entity.PersonalInformation#address
	 **/
	public static volatile SingularAttribute<PersonalInformation, String> address;
	
	/**
	 * @see ru.sbercraft.service.entity.PersonalInformation#birthCertificate
	 **/
	public static volatile SingularAttribute<PersonalInformation, String> birthCertificate;
	
	/**
	 * @see ru.sbercraft.service.entity.PersonalInformation#id
	 **/
	public static volatile SingularAttribute<PersonalInformation, Long> id;
	
	/**
	 * @see ru.sbercraft.service.entity.PersonalInformation
	 **/
	public static volatile EntityType<PersonalInformation> class_;
	
	/**
	 * @see ru.sbercraft.service.entity.PersonalInformation#birthDate
	 **/
	public static volatile SingularAttribute<PersonalInformation, LocalDate> birthDate;
	
	/**
	 * @see ru.sbercraft.service.entity.PersonalInformation#user
	 **/
	public static volatile SingularAttribute<PersonalInformation, User> user;

}


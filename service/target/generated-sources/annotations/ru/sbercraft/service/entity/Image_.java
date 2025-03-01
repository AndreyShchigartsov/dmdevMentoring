package ru.sbercraft.service.entity;

import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Image.class)
public abstract class Image_ {

	public static final String PATH = "path";
	public static final String ID = "id";
	public static final String USER = "user";

	
	/**
	 * @see ru.sbercraft.service.entity.Image#path
	 **/
	public static volatile SingularAttribute<Image, String> path;
	
	/**
	 * @see ru.sbercraft.service.entity.Image#id
	 **/
	public static volatile SingularAttribute<Image, Long> id;
	
	/**
	 * @see ru.sbercraft.service.entity.Image
	 **/
	public static volatile EntityType<Image> class_;
	
	/**
	 * @see ru.sbercraft.service.entity.Image#user
	 **/
	public static volatile SingularAttribute<Image, User> user;

}


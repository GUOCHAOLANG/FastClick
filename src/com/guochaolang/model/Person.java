package com.guochaolang.model;

import java.io.Serializable;

public class Person implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String pass;
	private Integer gender;

	public Person()
	{
	}

	/**
	 * @param name
	 * @param pass
	 * @param gender
	 */
	public Person(String name, String pass)
	{
		this.name = name;
		this.pass = pass;
		//this.gender = gender;
	}
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getPass()
	{
		return pass;
	}
	public void setPass(String pass)
	{
		this.pass = pass;
	}
	public Integer getGender()
	{
		return gender;
	}
	public void setGender(Integer gender)
	{
		this.gender = gender;
	}

}

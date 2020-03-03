package com.github.PavelKisliuk.entity;

import java.util.Date;

public class Human {
	private String name;
	private Integer age;
	private Date birthday;

	public Human(String name, Integer age, Date birthday) {
		this.name = name;
		this.age = age;
		this.birthday = birthday;
	}

	public Human() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}

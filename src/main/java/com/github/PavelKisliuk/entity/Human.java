/*  By Pavel Kisliuk, 31.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.github.PavelKisliuk.entity;

import java.util.Date;
import java.util.Objects;

/**
 * The {@code Human} class is simple entity class describing human.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class Human {
	private String name;
	private Integer age;
	private Date birthday;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Human human = (Human) o;

		if (!Objects.equals(name, human.name)) return false;
		if (!Objects.equals(age, human.age)) return false;
		return Objects.equals(birthday, human.birthday);
	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (age != null ? age.hashCode() : 0);
		result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Human{" +
				"name='" + name + '\'' +
				", age=" + age +
				", birthday=" + birthday +
				'}';
	}
}
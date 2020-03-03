/*  By Pavel Kisliuk, 31.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.github.PavelKisliuk.entity;

import java.time.LocalDate;
import java.util.Objects;

/**
 * The {@code Human} class is simple entity class describing human.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 1.8
 */
public class Human {
	private String name;
	private Integer age;
	private LocalDate birthday;

	public static class Builder {
		private String name;
		private Integer age;
		private LocalDate birthday;

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder age(Integer age) {
			this.age = age;
			return this;
		}

		public Builder birthday(LocalDate birthday){
			this.birthday = birthday;
			return this;
		}

		public Human build() {
			return new Human(this);
		}
	}

	private Human(Builder builder) {
		this.name = builder.name;
		this.age = builder.age;
		this.birthday = builder.birthday;
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

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
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
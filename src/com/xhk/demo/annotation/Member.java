package com.xhk.demo.annotation;

import com.xhk.demo.annotation.SQLAnnotation.Constraints;
import com.xhk.demo.annotation.SQLAnnotation.DBTable;
import com.xhk.demo.annotation.SQLAnnotation.SQLInteger;
import com.xhk.demo.annotation.SQLAnnotation.SQLString;

/**
 * @author xhk
 * @time 2018-12-24 13:34
 */
@DBTable(name = "MEMBER")
public class Member {

	@SQLString(30)
	String firstName;

	@SQLString(value = 20, constraints = @Constraints(unique = true))
	String lastName;

	@SQLInteger
	int age;

	@SQLString(value = 30, constraints = @Constraints(primaryKey = true, unique = true))
	String handle;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	@Override
	public String toString() {
		return "Member{" +
				"handle='" + handle + '\'' +
				'}';
	}
}

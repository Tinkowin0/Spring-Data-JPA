package com.jdc.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.entity.Course;
import com.jdc.repo.CourseRepo;

@SpringJUnitConfig(locations = "classpath:/applicatoin.xml")
public class CourseTest {

	@Autowired
	private CourseRepo repo;
	
	@ParameterizedTest
	@CsvSource({
		"Java Basic,Basic Course,2,300000,1"
	})
	@Sql(statements = "truncate table course")
	void test(String name, String description,
			int duration, int fees, int id) {
		
		var course = new Course(name, description, duration, fees);
		var result = repo.create(course);
		assertEquals(id, result);
	}
}

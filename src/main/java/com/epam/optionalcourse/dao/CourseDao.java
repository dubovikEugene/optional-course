package com.epam.optionalcourse.dao;

import com.epam.optionalcourse.bean.Course;
import com.epam.optionalcourse.dao.exception.DaoException;

import java.util.List;

public interface CourseDao {

    public List<Course> findAll() throws DaoException;
}

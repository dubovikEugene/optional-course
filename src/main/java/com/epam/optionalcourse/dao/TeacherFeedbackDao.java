package com.epam.optionalcourse.dao;

import com.epam.optionalcourse.bean.feedback.TeacherFeedback;
import com.epam.optionalcourse.dao.exception.DaoException;

import java.util.List;

public interface TeacherFeedbackDao {

    List<TeacherFeedback> getAllFeedbackByStudentId(Integer id) throws DaoException;

    void addTeacherFeedback(TeacherFeedback teacherFeedback) throws DaoException;
}

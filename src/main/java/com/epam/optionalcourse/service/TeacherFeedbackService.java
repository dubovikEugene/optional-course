package com.epam.optionalcourse.service;

import com.epam.optionalcourse.bean.TeacherFeedback;
import com.epam.optionalcourse.service.exception.ServiceException;

public interface TeacherFeedbackService {

    void addTeacherFeedback(TeacherFeedback teacherFeedback) throws ServiceException;
}

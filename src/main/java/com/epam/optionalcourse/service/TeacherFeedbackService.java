package com.epam.optionalcourse.service;

import com.epam.optionalcourse.bean.feedback.TeacherFeedback;
import com.epam.optionalcourse.service.exception.ServiceException;

public interface TeacherFeedbackService {

    void addTeacherFeedback(TeacherFeedback teacherFeedback) throws ServiceException;
}

package com.epam.optionalcourse.service;

import com.epam.optionalcourse.bean.user.AuthorizedUser;
import com.epam.optionalcourse.bean.user.CreateUser;
import com.epam.optionalcourse.bean.user.ReadUser;
import com.epam.optionalcourse.service.exception.ServiceException;

import java.util.List;

public interface UserService {

    AuthorizedUser register(CreateUser user) throws ServiceException;

    AuthorizedUser signIn(String email, String password) throws ServiceException;

    public AuthorizedUser registerForACourse(AuthorizedUser user, String courseRunId) throws ServiceException;

    AuthorizedUser dropCourseFromStudent(AuthorizedUser authorizedUser, int courseId) throws ServiceException;

    List<ReadUser> getAllStudentsByCourseRunId(int courseRunId) throws ServiceException;
}

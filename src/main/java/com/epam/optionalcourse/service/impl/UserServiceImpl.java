package com.epam.optionalcourse.service.impl;

import com.epam.optionalcourse.bean.AuthorizedUser;
import com.epam.optionalcourse.bean.CreateUser;
import com.epam.optionalcourse.bean.ReadUser;
import com.epam.optionalcourse.bean.User;
import com.epam.optionalcourse.controller.command.impl.AddFeedback;
import com.epam.optionalcourse.dao.exception.DaoException;
import com.epam.optionalcourse.dao.factory.DaoFactory;
import com.epam.optionalcourse.service.UserService;
import com.epam.optionalcourse.service.exception.ServiceException;
import com.epam.optionalcourse.service.mapper.Mapper;
import com.epam.optionalcourse.service.mapper.impl.CreateUserMapper;
import com.epam.optionalcourse.service.validator.impl.UserRegisterValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(AddFeedback.class);
    private static final DaoFactory daoFactory = DaoFactory.getInstance();
    private static final Mapper<CreateUser, User> createUserMapper = CreateUserMapper.getInstance();
    private static final UserRegisterValidator registerValidator = UserRegisterValidator.getInstance();


    public UserServiceImpl() {
    }


    public AuthorizedUser register(CreateUser createUser) throws ServiceException {
        try {
            if (!registerValidator.validate(createUser)) {
                throw new ServiceException("Not valid user data"); //? or better do ValidatorException
            }
            // TODO: 6/17/2022  check email is exist before register
            var user = createUserMapper.mapFrom(createUser);

            var userDao = daoFactory.getUserDao();
            return userDao.registration(user);
        } catch (DaoException exception) {
            logger.error(exception);
            throw new ServiceException(exception);
        }
    }

    @Override
    public AuthorizedUser signIn(String email, String password) throws ServiceException {
        try {
            var userDao = daoFactory.getUserDao();
            var authorizedUser = userDao.signIn(email, password);
            // TODO: 6/17/2022 check what incorrect
            if (authorizedUser.isPresent()) {
                return authorizedUser.get();
            } else {
                throw new ServiceException("Something went wrong");
            }

        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public AuthorizedUser registerForACourse(AuthorizedUser user, String courseId) throws ServiceException {

        try {
            var courseRunId = Integer.parseInt(courseId);
            return daoFactory.getUserDao().registerForACourse(user, courseRunId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }


    }

    @Override
    public AuthorizedUser dropCourseFromStudent(AuthorizedUser authorizedUser, int courseId) throws ServiceException {
        try {
            var updatedAuthorizedUser = daoFactory.getUserDao()
                    .dropCourseFromStudent(authorizedUser, courseId);

            return updatedAuthorizedUser;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public List<ReadUser> getAllStudentsByCourseRunId(int courseRunId) throws ServiceException{
        try {
            return daoFactory.getUserDao().findAllStudentsByCourseRunId(courseRunId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


}

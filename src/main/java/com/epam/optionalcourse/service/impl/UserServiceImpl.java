package com.epam.optionalcourse.service.impl;

import com.epam.optionalcourse.bean.AuthorizedUser;
import com.epam.optionalcourse.bean.CreateUser;
import com.epam.optionalcourse.bean.User;
import com.epam.optionalcourse.dao.exception.DaoException;
import com.epam.optionalcourse.dao.factory.DaoFactory;
import com.epam.optionalcourse.service.UserService;
import com.epam.optionalcourse.service.exception.ServiceException;
import com.epam.optionalcourse.service.mapper.Mapper;
import com.epam.optionalcourse.service.mapper.impl.CreateUserMapper;
import com.epam.optionalcourse.service.validator.impl.UserRegisterValidator;

public class UserServiceImpl implements UserService {

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
            var user = createUserMapper.mapFrom(createUser);

            var userDao = daoFactory.getUserDao();
            return userDao.registration(user);
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    @Override
    public AuthorizedUser signIn(String email, String password) throws ServiceException {
        try {
            var userDao = daoFactory.getUserDao();
            var authorizedUser = userDao.signIn(email, password);
            if (authorizedUser.isPresent()){
                return authorizedUser.get();
            } else {
                throw new ServiceException("User is not exist");
            }

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}

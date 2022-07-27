package com.epam.optionalcourse.controller;

import com.epam.optionalcourse.controller.command.Command;
import com.epam.optionalcourse.controller.command.impl.ChangeLocal;
import com.epam.optionalcourse.controller.command.impl.GetMainPage;
import com.epam.optionalcourse.controller.command.impl.WrongRequest;
import com.epam.optionalcourse.controller.command.impl.course.CreateCourseRunCommand;
import com.epam.optionalcourse.controller.command.impl.course.DropCourseFromStudent;
import com.epam.optionalcourse.controller.command.impl.course.EditCourse;
import com.epam.optionalcourse.controller.command.impl.course.GetCourseEditPage;
import com.epam.optionalcourse.controller.command.impl.course.GetCoursePage;
import com.epam.optionalcourse.controller.command.impl.course.GetCoursesPage;
import com.epam.optionalcourse.controller.command.impl.course.GetCreateCourseRunPage;
import com.epam.optionalcourse.controller.command.impl.feedback.AddFeedback;
import com.epam.optionalcourse.controller.command.impl.feedback.GetFeedbackPage;
import com.epam.optionalcourse.controller.command.impl.user.GetListOfCourseStudents;
import com.epam.optionalcourse.controller.command.impl.user.GetProfilePage;
import com.epam.optionalcourse.controller.command.impl.user.GetRegistrationPage;
import com.epam.optionalcourse.controller.command.impl.user.GetSignInPage;
import com.epam.optionalcourse.controller.command.impl.user.GetTeacherCoursesPage;
import com.epam.optionalcourse.controller.command.impl.user.RegisterForACourse;
import com.epam.optionalcourse.controller.command.impl.user.Registration;
import com.epam.optionalcourse.controller.command.impl.user.SignIn;
import com.epam.optionalcourse.controller.command.impl.user.SignOut;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {

    private final Map<CommandName, Command> repository = new HashMap<>();
    private static final CommandProvider INSTANCE = new CommandProvider();

    private CommandProvider() {
        repository.put(CommandName.SIGN_IN, new SignIn());
        repository.put(CommandName.SIGN_OUT, new SignOut());
        repository.put(CommandName.REGISTRATION, new Registration());
        repository.put(CommandName.GET_REGISTRATION_PAGE, new GetRegistrationPage());
        repository.put(CommandName.GET_MAIN_PAGE, new GetMainPage());
        repository.put(CommandName.GET_SIGN_IN_PAGE, new GetSignInPage());
        repository.put(CommandName.GET_COURSES_PAGE, new GetCoursesPage());
        repository.put(CommandName.GET_COURSE_PAGE, new GetCoursePage());
        repository.put(CommandName.GET_PROFILE_PAGE, new GetProfilePage());
        repository.put(CommandName.REGISTER_FOR_A_COURSE, new RegisterForACourse());
        repository.put(CommandName.CHANGE_LOCAl, new ChangeLocal());
        repository.put(CommandName.DROP_COURSE_FROM_STUDENT, new DropCourseFromStudent());
        repository.put(CommandName.GET_TEACHER_COURSES_PAGE, new GetTeacherCoursesPage());
        repository.put(CommandName.GET_COURSE_EDIT_PAGE, new GetCourseEditPage());
        repository.put(CommandName.EDIT_COURSE, new EditCourse());
        repository.put(CommandName.GET_CREATE_COURSE_RUN_PAGE, new GetCreateCourseRunPage());
        repository.put(CommandName.CREATE_COURSE_RUN, new CreateCourseRunCommand());
        repository.put(CommandName.GET_LIST_OF_COURSE_STUDENTS_PAGE, new GetListOfCourseStudents());
        repository.put(CommandName.GET_FEEDBACK_PAGE, new GetFeedbackPage());
        repository.put(CommandName.ADD_FEEDBACK, new AddFeedback());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
    }

    public Command getCommand(String name) {

        var optionalCommandName = CommandName.find(name);

        if (optionalCommandName.isPresent()) {
            return repository.get(optionalCommandName.get());
        }
        return repository.get(CommandName.WRONG_REQUEST);
    }

    public static CommandProvider getInstance() {
        return INSTANCE;
    }


}

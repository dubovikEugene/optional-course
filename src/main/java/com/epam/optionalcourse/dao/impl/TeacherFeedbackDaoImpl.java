package com.epam.optionalcourse.dao.impl;

import com.epam.optionalcourse.bean.TeacherFeedback;
import com.epam.optionalcourse.dao.TeacherFeedbackDao;
import com.epam.optionalcourse.dao.connectionpool.ConnectionPool;
import com.epam.optionalcourse.dao.exception.ConnectionPoolException;
import com.epam.optionalcourse.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherFeedbackDaoImpl implements TeacherFeedbackDao {

    private static final Logger logger = LogManager.getLogger(TeacherFeedbackDaoImpl.class);

    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final String FIND_ALL_BY_STUDENT_ID_SQL = """
            SELECT course_runs_id, student_id, mark, feedback
            FROM teachers_feedbacks
            WHERE is_get_feedback IS NOT NULL
                AND student_id = ?""";
    private static final String SAVE_TEACHER_FEEDBACK_SQL = """
            UPDATE teachers_feedbacks
            SET mark = ?, feedback = ?, is_get_feedback = 1
            WHERE course_runs_id = ?
                AND student_id = ?
            """;

    @Override
    public List<TeacherFeedback> getAllFeedbackByStudentId(Integer id) throws DaoException {
        try (var connection = pool.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_BY_STUDENT_ID_SQL)) {

            preparedStatement.setObject(1, id);
            var resultSet = preparedStatement.executeQuery();
            List<TeacherFeedback> teacherFeedbacks = new ArrayList<>();
            while (resultSet.next()) {
                teacherFeedbacks.add(buildTeacherFeedback(resultSet));
            }

            return teacherFeedbacks;

        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }

    @Override
    public void addTeacherFeedback(TeacherFeedback teacherFeedback) throws DaoException {
        try (var connection = pool.get();
             var preparedStatement = connection.prepareStatement(SAVE_TEACHER_FEEDBACK_SQL)) {
            preparedStatement.setObject(1, teacherFeedback.getMark());
            preparedStatement.setObject(2, teacherFeedback.getFeedback());
            preparedStatement.setObject(3, teacherFeedback.getCourseRunId());
            preparedStatement.setObject(4, teacherFeedback.getStudentId());

            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }

    private TeacherFeedback buildTeacherFeedback(ResultSet resultSet) throws SQLException {
        return new TeacherFeedback(
                resultSet.getObject("course_runs_id", Integer.class),
                resultSet.getObject("student_id", Integer.class),
                resultSet.getObject("mark", Integer.class),
                resultSet.getObject("feedback", String.class)
        );
    }
}

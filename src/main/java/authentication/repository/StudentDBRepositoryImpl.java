package authentication.repository;

import authentication.config.DBConfig;
import authentication.dto.StudentDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDBRepositoryImpl implements StudentRepository {
    @Override
    public void save(StudentDto studentDto) {

        String insertQuery = "INSERT INTO student_service.student(name, scholarship_amount) VALUES (?,?)";
        try (Connection connection = DBConfig.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            connection.commit();
            preparedStatement.setString(1, studentDto.name());
            preparedStatement.setBigDecimal(2, studentDto.scholarshipAmount());
            preparedStatement.execute();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public List<StudentDto> findAll() {

        String selectQuery = "select * from student_service.student";
        List<StudentDto> students = new ArrayList<>();
        try (Connection connection = DBConfig.getDataSource().getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()) {
                StudentDto student = new StudentDto(resultSet.getString("NAME")
                        , resultSet.getInt("ID")
                        , resultSet.getBigDecimal("SCHOLARSHIP_AMOUNT"));
                students.add(student);
            }
            return students;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public void removeById(int id) {
    }

    @Override
    public StudentDto findById(int id) {

        String selectQuery = "select * from student_service.student where id = ?";
        try (Connection connection = DBConfig.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            StudentDto student = null;
            while (resultSet.next()) {
                student = new StudentDto(resultSet.getString("NAME")
                        , resultSet.getInt("ID")
                        , resultSet.getBigDecimal("SCHOLARSHIP_AMOUNT"));
            }
            return student;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

}

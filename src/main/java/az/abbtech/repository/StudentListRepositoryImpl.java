package az.abbtech.repository;

import az.abbtech.config.BCryptEncoderUtil;
import az.abbtech.config.DBConfig;
import az.abbtech.dto.LoginDto;
import az.abbtech.dto.RegStudentDto;
import az.abbtech.dto.StudentDto;
import az.abbtech.exception.BadRequestException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentListRepositoryImpl implements StudentRepository {

    @Override
    public void save(StudentDto student) {
        String insertQuery = """
                           INSERT INTO student_service.student
                            (NAME,SCHOLAR_SHIP_AMOUNT) VALUES (?,?)
                """;
        try (Connection connection = DBConfig.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, student.name());
            preparedStatement.setBigDecimal(2, student.scholarshipAmount());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public List<StudentDto> findAll() {
        String insertQuery = """
                           SELECT * FROM student_service.student
                """;
        try (Connection connection = DBConfig.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<StudentDto> students = new ArrayList<>();
            while (resultSet.next()) {
                StudentDto studentDto = new StudentDto(resultSet.getString("NAME")
                        , resultSet.getLong("ID")
                        , resultSet.getBigDecimal("SCHOLAR_SHIP_AMOUNT"));
                students.add(studentDto);
            }
            return students;

        } catch (Exception exception) {
            throw new BadRequestException("Can not fetch students");
        }
    }

    @Override
    public StudentDto findById(Long id) {
        String insertQuery = """
                           SELECT * FROM student_service.student WHERE ID = ?
                """;

        try (Connection connection = DBConfig.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            StudentDto studentDto = null;
            while (resultSet.next()) {
                studentDto = new StudentDto(resultSet.getString("NAME")
                        , resultSet.getLong("ID")
                        , resultSet.getBigDecimal("SCHOLAR_SHIP_AMOUNT"));

            }
            if (studentDto == null) {
                throw new BadRequestException("Can not find student");
            }
            return studentDto;
        } catch (Exception exception) {
            throw new BadRequestException("Can not find student by id");
        }
    }

    @Override
    public void registerStudent(RegStudentDto registerDto) {
        String insertQuery = """
                           INSERT INTO student_service.student
                            (NAME,USERNAME,PASSWORD) VALUES (?,?,?)
                """;
        String hashedPassword = BCryptEncoderUtil.encode(registerDto.password());
        try (Connection connection = DBConfig.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, registerDto.name());
            preparedStatement.setString(2, registerDto.userName());
            preparedStatement.setString(3, BCryptEncoderUtil.encode(registerDto.password()));
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public List<String> getUserAuthorities(LoginDto loginDto) {
        String roleQuery = """
                SELECT r.name
                FROM role r
                JOIN student_role sr ON r.id = sr.role_id
                JOIN student s ON s.id = sr.student_id
                WHERE s.username = ?;
                """;

        String passwordQuery = """
                SELECT password
                FROM student
                WHERE username = ?;
                """;
        List<String> roles = new ArrayList<>();
        try (Connection connection = DBConfig.getDataSource().getConnection()) {
            /*
            Get user password and check it matches(loginDto.password(), storedPassword)) method
             */
            try (PreparedStatement passwordStmt = connection.prepareStatement(passwordQuery)) {
                passwordStmt.setString(1, loginDto.userName());
                ResultSet passwordResult = passwordStmt.executeQuery();
                if (passwordResult.next()) {
                    String storedPassword = passwordResult.getString("password");
                    if (!BCryptEncoderUtil.matches(loginDto.password(), storedPassword)) {
                        throw new BadRequestException("Wrong password");
                    }
                } else {
                    throw new BadRequestException("User not found");
                }
            }

            /*
             if password matches continue to get roles
             */
            try (PreparedStatement roleStmt = connection.prepareStatement(roleQuery)) {
                roleStmt.setString(1, loginDto.userName());
                ResultSet roleResult = roleStmt.executeQuery();
                /*
                if user has roles return else throw exception
                 */
                if (roleResult.next()) {
                    roles.add(roleResult.getString("name"));
                    while (roleResult.next()) {
                        roles.add(roleResult.getString("name"));
                    }
                } else {
                    throw new BadRequestException("User role not found");
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new BadRequestException("Server error");
        }
        return roles;
    }
}

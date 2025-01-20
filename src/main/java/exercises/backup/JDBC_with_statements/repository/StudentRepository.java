package exercises.backup.JDBC_with_statements.repository;

import exercises.backup.JDBC_with_statements.dto.StudentDto;

import java.util.List;

public interface StudentRepository {

    void save(StudentDto studentDto);

    List<StudentDto> findAll();

    void removeById(int id);

    StudentDto findById(int id);
}

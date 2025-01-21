package exercises.jdbc_statements.repository;

import exercises.jdbc_statements.dto.StudentDto;

import java.util.List;

public interface StudentRepository {

    void save(StudentDto studentDto);

    List<StudentDto> findAll();

    void removeById(int id);

    StudentDto findById(int id);
}

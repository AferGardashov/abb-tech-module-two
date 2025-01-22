package jdbc_statements_dbconfig.repository;

import jdbc_statements_dbconfig.dto.StudentDto;

import java.util.List;

public interface StudentRepository {

    void save(StudentDto studentDto);

    List<StudentDto> findAll();

    void removeById(int id);

    StudentDto findById(int id);
}

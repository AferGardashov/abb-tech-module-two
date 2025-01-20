package exercises.backup.JDBC_with_statements.service;

import exercises.backup.JDBC_with_statements.dto.StudentDto;
import exercises.backup.JDBC_with_statements.exeptions.BadRequestException;

import java.util.List;

public interface StudentService {

    void save(StudentDto studentDto) throws BadRequestException;

    List<StudentDto> findAll();

    void removeById(Integer id);

    StudentDto findById(Integer id);
}

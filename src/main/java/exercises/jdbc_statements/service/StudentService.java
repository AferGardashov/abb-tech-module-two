package exercises.jdbc_statements.service;

import exercises.jdbc_statements.dto.StudentDto;
import exercises.jdbc_statements.exeptions.BadRequestException;

import java.util.List;

public interface StudentService {

    void save(StudentDto studentDto) throws BadRequestException;

    List<StudentDto> findAll();

    void removeById(Integer id);

    StudentDto findById(Integer id);
}

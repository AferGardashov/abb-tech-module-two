package jdbc_statements_dbconfig.service;

import jdbc_statements_dbconfig.dto.StudentDto;
import jdbc_statements_dbconfig.exeptions.BadRequestException;

import java.util.List;

public interface StudentService {

    void save(StudentDto studentDto) throws BadRequestException;

    List<StudentDto> findAll();

    void removeById(Integer id);

    StudentDto findById(Integer id);
}

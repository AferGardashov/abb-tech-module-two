package authentication.service;

import authentication.dto.StudentDto;
import authentication.exeptions.BadRequestException;

import java.util.List;

public interface StudentService {

    void save(StudentDto studentDto) throws BadRequestException;

    List<StudentDto> findAll();

    void removeById(Integer id);

    StudentDto findById(Integer id);
}

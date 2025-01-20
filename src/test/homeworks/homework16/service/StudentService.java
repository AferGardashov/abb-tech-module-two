package homeworks.homework16.service;

import homeworks.homework16.dto.StudentDto;
import homeworks.homework16.exeptions.BadRequestException;

import java.util.List;

public interface StudentService {

    void save(StudentDto studentDto) throws BadRequestException;

    List<StudentDto> findAll();

    void removeById(Integer id);

    StudentDto findById(Integer id);
}

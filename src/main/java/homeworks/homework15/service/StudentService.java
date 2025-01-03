package homeworks.homework15.service;

import homeworks.homework15.dto.StudentDto;

import java.util.List;

public interface StudentService {

    void save(StudentDto studentDto);

    List<StudentDto> findAll();

    void removeById(Integer id);
}

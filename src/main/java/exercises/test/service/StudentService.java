package exercises.test.service;

import exercises.test.dto.StudentDto;

import java.util.List;

public interface StudentService {

    void save(StudentDto studentDto);

    List<StudentDto> findAll();

    void removeById(Integer id);
}

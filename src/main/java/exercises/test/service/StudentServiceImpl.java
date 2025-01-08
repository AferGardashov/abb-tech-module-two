package exercises.test.service;

import exercises.test.dto.StudentDto;
import exercises.test.repository.StudentRepository;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void save(StudentDto studentDto) {
        studentRepository.save(studentDto);
    }

    @Override
    public List<StudentDto> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public void removeById(Integer id) {
        studentRepository.removeById(id);
    }
}

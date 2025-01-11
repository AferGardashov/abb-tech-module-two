package homeworks.homework15.service;

import homeworks.homework15.dto.StudentDto;
import homeworks.homework15.repository.StudentRepository;

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

    @Override
    public StudentDto findById(Integer id) {
        return studentRepository.findById(id);
    }
}

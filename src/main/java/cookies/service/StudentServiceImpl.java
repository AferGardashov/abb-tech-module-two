package cookies.service;

import cookies.dto.StudentDto;
import cookies.exeptions.BadRequestException;
import cookies.repository.StudentRepository;

import java.math.BigDecimal;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void save(StudentDto studentDto) throws BadRequestException {
        if (studentDto.scholarshipAmount().compareTo(BigDecimal.valueOf(100)) > 0) {
            throw new BadRequestException("Scholarship amount not allowed");
        }
        if (!studentRepository.findAll().isEmpty() && studentRepository.findById(studentDto.id()) != null) {
            throw new BadRequestException("Student already exists");
        } else {
            studentRepository.save(studentDto);
        }
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

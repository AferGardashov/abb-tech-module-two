package az.abbtech.service;

import az.abbtech.dto.LoginDto;
import az.abbtech.dto.RegStudentDto;
import az.abbtech.dto.StudentDto;
import az.abbtech.exception.BadRequestException;
import az.abbtech.repository.StudentRepository;

import java.math.BigDecimal;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void save(StudentDto student) {
        if (student.scholarshipAmount().compareTo(BigDecimal.valueOf(100)) > -1) {
            throw new BadRequestException("Scholarship amount cannot be greater than 100");
        } else {
            studentRepository.save(student);
        }
    }

    @Override
    public void registerStudent(RegStudentDto register) {
        studentRepository.registerStudent(register);

    }

    @Override
    public List<StudentDto> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public StudentDto findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public List<String> getUserAuthorities(LoginDto loginDto) {
        return studentRepository.getUserAuthorities(loginDto);
    }
}

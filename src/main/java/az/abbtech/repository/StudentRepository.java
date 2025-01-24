package az.abbtech.repository;

import az.abbtech.dto.LoginDto;
import az.abbtech.dto.RegStudentDto;
import az.abbtech.dto.StudentDto;

import java.util.List;

public interface StudentRepository {
    void save(StudentDto student);

    List<StudentDto> findAll();

    StudentDto findById(Long id);

    void registerStudent(RegStudentDto register);

    public List<String> getUserAuthorities(LoginDto loginDto);
}

package az.abbtech.service;

import az.abbtech.dto.LoginDto;
import az.abbtech.dto.RegStudentDto;
import az.abbtech.dto.StudentDto;

import java.util.List;

public interface StudentService {

    void save(StudentDto student);

    void registerStudent(RegStudentDto registerDto);

    List<StudentDto> findAll();

    StudentDto findById(Long id);

    public List<String> getUserAuthorities(LoginDto loginDto);
}

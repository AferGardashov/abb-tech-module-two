package az.abbtech.repository;

import az.abbtech.dto.LoginDto;
import az.abbtech.dto.RegStudentDto;
import az.abbtech.dto.StudentDto;

import java.util.List;

public class StudentFileRepositoryImpl implements StudentRepository {
    @Override
    public void save(StudentDto student) {
        // Save to File
    }

    @Override
    public List<StudentDto> findAll() {
        // Get ALl students from File
        return List.of();
    }

    @Override
    public StudentDto findById(Long id) {
        return null;
    }

    @Override
    public void registerStudent(RegStudentDto register) {

    }

    @Override
    public List<String> getUserAuthorities(LoginDto loginDto) {
        return List.of();
    }
}

package homework15.repository;

import homework15.dto.StudentDto;

import java.util.ArrayList;
import java.util.List;

public class StudentListRepositoryImpl implements StudentRepository {

    private final static List<StudentDto> STUDENTS = new ArrayList<StudentDto>();

    @Override
    public void save(StudentDto student) {
        STUDENTS.add(student);
    }

    @Override
    public List<StudentDto> findAll() {
        if (STUDENTS.isEmpty()) {
            System.out.println("No students found");
        }
        return STUDENTS;
    }

    @Override
    public void removeById(int id) {
        findAll().removeIf(student -> student.id() == id);
    }

    @Override
    public StudentDto findById(int id) {
        for (StudentDto student : findAll()) {
            if (student.id() == id) {
                return student;
            }
        }
        return null;
    }

}

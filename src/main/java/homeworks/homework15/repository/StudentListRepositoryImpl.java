package homeworks.homework15.repository;

import homeworks.homework15.dto.StudentDto;

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
        List<StudentDto> students = findAll();
        students.removeIf(student -> student.id() == id);
    }
}
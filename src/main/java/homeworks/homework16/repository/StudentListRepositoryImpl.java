package homeworks.homework16.repository;

import homeworks.homework16.dto.StudentDto;

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

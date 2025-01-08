package exercises.test.repository;

import exercises.test.dto.StudentDto;

import java.math.BigDecimal;
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
        STUDENTS.add(new StudentDto("Test", 5, BigDecimal.valueOf(105)));

        return STUDENTS;
    }

    @Override
    public void removeById(int id) {
        List<StudentDto> students = findAll();
        students.removeIf(student -> student.id() == id);
    }
}

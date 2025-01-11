package exercises.test.repository;

import exercises.test.dto.StudentDto;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StudentFileRepositoryImpl implements StudentRepository {

    private final String filePath;

    public StudentFileRepositoryImpl(String filePath) {
        this.filePath = filePath;
    }


    @Override
    public void save(StudentDto student) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(student.name() + "," + student.id() + "," + student.scholarshipAmount() + "\n");
        } catch (IOException e) {
            throw new RuntimeException("Error saving student to file");
        }
    }

    @Override
    public List<StudentDto> findAll() {
        List<StudentDto> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    students.add(new StudentDto(parts[0], Integer.valueOf(parts[1]), new BigDecimal(parts[2])));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading students from file", e);
        }
        return students;
    }

    @Override
    public void removeById(int id) {
        List<StudentDto> students = findAll();
        students.removeIf(student -> student.id() == id);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (StudentDto student : students) {
                writer.write(student.id() + "," + student.name() + "," + student.scholarshipAmount() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error removing student from file", e);
        }
    }

    @Override
    public StudentDto findById(int id) {
        List<StudentDto> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    students.add(new StudentDto(parts[0], Integer.valueOf(parts[1]), new BigDecimal(parts[2])));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading students from file", e);
        }
        StudentDto student = null;
        for (StudentDto sd : students) {
            if(sd.id() == id){
                student = sd;
            }
        }
        return student;
    }
}

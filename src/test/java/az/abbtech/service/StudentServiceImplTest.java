package az.abbtech.service;

import exercises.test.dto.StudentDto;
import exercises.test.repository.StudentRepository;
import exercises.test.service.StudentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;


    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    void saveTest_throws_exception() {
        StudentDto studentDto = new StudentDto("Student", 10, BigDecimal.valueOf(98));
        Mockito.doNothing().when(studentRepository).save(Mockito.any(StudentDto.class));
        Exception exception = Assertions.assertThrows(IllegalAccessException.class, () -> studentService.save(studentDto));
        Assertions.assertEquals("Scholarship amount not allowed", exception.getMessage());
    }

    @Test
    void saveTest_success() {
        StudentDto studentDto = new StudentDto("Student", 10, BigDecimal.valueOf(98));
        Mockito.doNothing().when(studentRepository).save(Mockito.any(StudentDto.class));
        Mockito.verify(studentRepository, Mockito.atMost(1)).save(Mockito.any(StudentDto.class));
        Assertions.assertDoesNotThrow(() -> studentService.save(studentDto));
    }

}

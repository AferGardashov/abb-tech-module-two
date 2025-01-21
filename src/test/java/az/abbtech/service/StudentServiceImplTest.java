package az.abbtech.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

//    @Mock
//    private StudentRepository studentRepository;
//
//    @InjectMocks
//    private StudentServiceImpl studentService;
//
//
//    @Test
//    @MockitoSettings(strictness = Strictness.LENIENT)
//    void saveTest_throws_exception() {
//        StudentDto studentDto = new StudentDto("Student", 10, BigDecimal.valueOf(98));
//        Mockito.doNothing().when(studentRepository).save(Mockito.any(StudentDto.class));
//        Exception exception = Assertions.assertThrows(IllegalAccessException.class, () -> studentService.save(studentDto));
//        Assertions.assertEquals("Scholarship amount not allowed", exception.getMessage());
//    }
//
//    @Test
//    void saveTest_success() {
//        StudentDto studentDto = new StudentDto("Student", 10, BigDecimal.valueOf(98));
//        Mockito.doNothing().when(studentRepository).save(Mockito.any(StudentDto.class));
//        Mockito.verify(studentRepository, Mockito.atMost(1)).save(Mockito.any(StudentDto.class));
//        Assertions.assertDoesNotThrow(() -> studentService.save(studentDto));
//    }

}

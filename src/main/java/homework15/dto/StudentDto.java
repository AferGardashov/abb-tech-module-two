package homework15.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public record StudentDto(String name, int id, BigDecimal scholarshipAmount) implements Serializable {
}

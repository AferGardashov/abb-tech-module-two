package test.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public record StudentDto(String name, int id, BigDecimal scholarshipAmount) implements Serializable {
    @Override
    public boolean equals(Object obj) {
        return id == this.id();
    }

    @Override
    public int hashCode() {
        return id;
    }
}

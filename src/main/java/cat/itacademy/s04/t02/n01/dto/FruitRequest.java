package cat.itacademy.s04.t02.n01.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class FruitRequest {

    @NotBlank(message = "Name can't be empty")
    private String name;

    @NotNull(message = "Kg can't be empty")
    @Positive(message = "Kg must be positive")
    private Integer kilogramQuantity;

    public FruitRequest() {
    }

    public FruitRequest(String name, Integer kilogramQuantity) {
        this.name = name;
        this.kilogramQuantity = kilogramQuantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getKilogramQuantity() {
        return kilogramQuantity;
    }

    public void setKilogramQuantity(Integer kilogramQuantity) {
        this.kilogramQuantity = kilogramQuantity;
    }
}
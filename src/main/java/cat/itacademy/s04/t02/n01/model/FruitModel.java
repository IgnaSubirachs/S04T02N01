package cat.itacademy.s04.t02.n01.model;

import jakarta.persistence.*;


@Entity
@Table(name = "Fruit")
public class FruitModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int kilogramQuantity;

    public FruitModel() {
    }

    public FruitModel(Long id, String name, int kilogramQuantity) {
        this.id = id;
        this.name = name;
        this.kilogramQuantity = kilogramQuantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKilogramQuantity() {
        return kilogramQuantity;
    }

    public void setKilogramQuantity(int kilogramQuantity) {
        this.kilogramQuantity = kilogramQuantity;
    }
}

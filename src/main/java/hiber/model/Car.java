package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "series")
    private int series;

    public Car() {
        // Default constructor
    }

    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }

    // Getters and setters
}

package hiber.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="model")
    private String model;

    @Column(name="series")
    private long series;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="users_id")
    private User ownerCar;

    public Car() {
    }

    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }

    public User getOwnerCar() {
        return ownerCar;
    }

    public void setOwnerCar(User ownerCar) {
        this.ownerCar = ownerCar;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getSeries() {
        return series;
    }

    public void setSeries(long series) {
        this.series = series;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return series == car.series && Objects.equals(id, car.id) && Objects.equals(model, car.model) && Objects.equals(ownerCar, car.ownerCar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, series, ownerCar);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", series=" + series +
                ", user=" + ownerCar +
                '}';
    }
}

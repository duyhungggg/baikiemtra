package baikiemtra.qlynhansu.models;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @Column(name = "ma_phong", nullable = false, length = 2)
    private String maPhong;

    @Column(name = "ten_phong", nullable = false, length = 30)
    private String tenPhong;

    // Constructors, getters, setters, and other methods as needed
}
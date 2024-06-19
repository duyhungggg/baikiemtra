package baikiemtra.qlynhansu.models;



import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_nv", nullable = false, length = 3)
    private String maNv;

    @Column(name = "ten_nv", nullable = false, length = 100)
    private String tenNv;

    @Column(name = "phai", length = 3)
    private String phai;

    @Column(name = "noi_sinh", length = 200)
    private String noiSinh;

    @Column(name = "luong")
    private Integer luong;

    @ManyToOne
    @JoinColumn(name = "ma_phong")
    private Department department;

    // Constructors, getters, setters, and other methods as needed
}

package baikiemtra.qlynhansu.repositories;

import baikiemtra.qlynhansu.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, String> {
    // Các phương thức tùy chỉnh nếu cần
}

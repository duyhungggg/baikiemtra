package baikiemtra.qlynhansu.repositories;

import baikiemtra.qlynhansu.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Các phương thức tùy chỉnh nếu cần
}

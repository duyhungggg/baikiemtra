package baikiemtra.qlynhansu.Service;

import baikiemtra.qlynhansu.models.Employee;
import baikiemtra.qlynhansu.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    // Retrieve all employees from the database with pagination
    public List<Employee> getAllEmployees(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return employeeRepository.findAll(pageable).getContent();
    }

    // Retrieve an employee by its id
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    // Add a new employee to the database
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Update an existing employee
    public Employee updateEmployee(@NotNull Employee employee) {
        Employee existingEmployee = employeeRepository.findById(employee.getId())
                .orElseThrow(() -> new IllegalStateException("Employee with ID " +
                        employee.getId() + " does not exist."));
        existingEmployee.setMaNv(employee.getMaNv());
        existingEmployee.setTenNv(employee.getTenNv());
        existingEmployee.setPhai(employee.getPhai());
        existingEmployee.setNoiSinh(employee.getNoiSinh());
        existingEmployee.setLuong(employee.getLuong());
        existingEmployee.setDepartment(employee.getDepartment());
        return employeeRepository.save(existingEmployee);
    }

    // Delete an employee by its id
    public void deleteEmployeeById(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new IllegalStateException("Employee with ID " + id + " does not exist.");
        }
        employeeRepository.deleteById(id);
    }
}

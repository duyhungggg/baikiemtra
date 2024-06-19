package baikiemtra.qlynhansu.Service;


import baikiemtra.qlynhansu.models.Department;
import baikiemtra.qlynhansu.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    /**
     * Retrieve all departments from the database.
     *
     * @return a list of departments
     */
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    /**
     * Retrieve a department by its id.
     *
     * @param maPhong the id of the department to retrieve
     * @return an Optional containing the found department or empty if not found
     */
    public Optional<Department> getDepartmentById(String maPhong) {
        return departmentRepository.findById(maPhong);
    }

    /**
     * Add a new department to the database.
     *
     * @param department the department to add
     */
    public void addDepartment(Department department) {
        departmentRepository.save(department);
    }

    /**
     * Update an existing department.
     *
     * @param department the department with updated information
     */
    public void updateDepartment(@NotNull Department department) {
        Department existingDepartment = departmentRepository.findById(department.getMaPhong())
                .orElseThrow(() -> new IllegalStateException("Department with ID " +
                        department.getMaPhong() + " does not exist."));
        existingDepartment.setTenPhong(department.getTenPhong());
        departmentRepository.save(existingDepartment);
    }

    /**
     * Delete a department by its id.
     *
     * @param maPhong the id of the department to delete
     */
    public void deleteDepartmentById(String maPhong) {
        if (!departmentRepository.existsById(maPhong)) {
            throw new IllegalStateException("Department with ID " + maPhong + " does not exist.");
        }
        departmentRepository.deleteById(maPhong);
    }
}
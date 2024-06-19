package baikiemtra.qlynhansu.Controller;


import baikiemtra.qlynhansu.Service.DepartmentService;
import baikiemtra.qlynhansu.models.Department;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DepartmentController {
    @Autowired
    private final DepartmentService departmentService;

    // Hiển thị form thêm phòng ban
    @GetMapping("/departments/add")
    public String showAddForm(Model model) {
        model.addAttribute("department", new Department());
        return "/departments/add-department";
    }

    // Xử lý thêm phòng ban
    @PostMapping("/departments/add")
    public String addDepartment(@Valid Department department, BindingResult result) {
        if (result.hasErrors()) {
            return "/departments/add-department";
        }
        departmentService.addDepartment(department);
        return "redirect:/departments";
    }

    // Hiển thị danh sách phòng ban
    @GetMapping("/departments")
    public String listDepartments(Model model) {
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "/departments/departments-list";
    }

    // Hiển thị form cập nhật phòng ban
    @GetMapping("/departments/edit/{maPhong}")
    public String showUpdateForm(@PathVariable("maPhong") String maPhong, Model model) {
        Department department = departmentService.getDepartmentById(maPhong)
                .orElseThrow(() -> new IllegalArgumentException("Invalid department Id:" + maPhong));
        model.addAttribute("department", department);
        return "/departments/update-department";
    }

    // Xử lý cập nhật phòng ban
    @PostMapping("/departments/update/{maPhong}")
    public String updateDepartment(@PathVariable("maPhong") String maPhong, @Valid Department department,
                                   BindingResult result, Model model) {
        if (result.hasErrors()) {
            department.setMaPhong(maPhong);
            return "/departments/update-department";
        }
        departmentService.updateDepartment(department);
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "redirect:/departments";
    }

    // Xóa phòng ban
    @GetMapping("/departments/delete/{maPhong}")
    public String deleteDepartment(@PathVariable("maPhong") String maPhong, Model model) {
        Department department = departmentService.getDepartmentById(maPhong)
                .orElseThrow(() -> new IllegalArgumentException("Invalid department Id:" + maPhong));
        departmentService.deleteDepartmentById(maPhong);
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "redirect:/departments";
    }
}

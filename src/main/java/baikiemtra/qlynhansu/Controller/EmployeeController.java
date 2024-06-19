package baikiemtra.qlynhansu.Controller;

import baikiemtra.qlynhansu.models.Employee;
import baikiemtra.qlynhansu.repositories.DepartmentRepository;
import baikiemtra.qlynhansu.repositories.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    // Hiển thị danh sách nhân viên
    @GetMapping
    public String showEmployeeList(Model model) {
        List<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "employees/employee-list";
    }

    // Hiển thị form thêm nhân viên
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentRepository.findAll());
        return "employees/add-employee";
    }

    // Xử lý thêm nhân viên
    @PostMapping("/add")
    public String addEmployee(@Valid Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentRepository.findAll());
            return "employees/add-employee";
        }
        employeeRepository.save(employee);
        return "redirect:/employees";
    }



    // Hiển thị form sửa thông tin nhân viên
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            model.addAttribute("employee", employee.get());
            model.addAttribute("departments", departmentRepository.findAll());
            return "employees/update-employee";
        } else {
            return "redirect:/employees";
        }
    }

    // Xử lý sửa thông tin nhân viên
    @PostMapping("/edit/{id}")
    public String updateEmployee(@PathVariable Long id, @Valid Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            return "employees/update-employee";
        }
        employee.setId(id);
        employeeRepository.save(employee);
        return "redirect:/employees";
    }

    // Xóa nhân viên
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
        return "redirect:/employees";
    }

    // Hiển thị chi tiết nhân viên
    @GetMapping("/detail/{id}")
    public String showEmployeeDetail(@PathVariable Long id, Model model) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            model.addAttribute("employee", employee.get());
            return "employees/employee-detail";
        } else {
            return "redirect:/employees";
        }
    }
}

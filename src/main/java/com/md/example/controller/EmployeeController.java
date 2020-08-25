package com.md.example.controller;

import com.md.example.entity.Employee;
import com.md.example.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepo employeeRepo;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String saveEmployee(@RequestBody Employee employee) {
        employeeRepo.save(employee);
        return "employee saved successfully!!";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllEmployee() {
        return employeeRepo.findAll();
    }

    @PutMapping
    public Employee updateEmployee(@RequestBody Employee employee) {

        Optional<Employee> optEmp = employeeRepo.findById(employee.getId());
        if (optEmp.isPresent()) {
            Employee existEmployee = optEmp.get();

            if (Objects.nonNull(employee.getName())) {
                existEmployee.setName(employee.getName());
            }

            if (employee.getSalary() != 0.0) {
                existEmployee.setSalary(employee.getSalary());
            }
            return employeeRepo.save(existEmployee);
        }

        throw new RuntimeException("employee id not exist");
    }

    @DeleteMapping
    public String deleteEmployee(@RequestParam Integer id) {
        employeeRepo.deleteById(id);
        return "employee deleted successfully!!";
    }
}

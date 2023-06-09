package org.jt.employeemanagement.controller;

import java.io.FileOutputStream;
import java.util.UUID;

import org.jt.employeemanagement.model.Employee;
import org.jt.employeemanagement.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

/**
 * EmployeeController
 */
@Controller
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping({ "/", "/view-employee" })
    public String viewEmployee(Model model) {
        var employees = employeeService.getEmployees();
        model.addAttribute("employees", employees);

        return "view-employee";
    }

    @GetMapping("/add-employee")
    public String addEmployee() {
        return "add-employee";
    }

    @PostMapping("/save-employee")
    public String saveEmployee(@ModelAttribute Employee employee,
            @RequestParam("picture") MultipartFile multipartFile) throws Exception {

        if (!multipartFile.isEmpty()) {
            var fileName = multipartFile.getOriginalFilename();
            var fileData = multipartFile.getBytes();

            var createFileName = UUID.randomUUID().toString() + "-" + fileName;
            var location = "images/" + createFileName;

            employee.setPictureURL("/" + location);

            var fileOutputStream = new FileOutputStream(location);
            fileOutputStream.write(fileData);
            fileOutputStream.close();
        }

        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

}
package org.jt.employeemanagement.service.impl;

import java.util.List;

import org.jt.employeemanagement.model.Employee;
import org.jt.employeemanagement.repository.EmployeeRepository;
import org.jt.employeemanagement.service.EmployeeService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

}

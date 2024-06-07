package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import model.Payroll;
import repository.PayrollRepository;

@RestController
@RequestMapping("/payroll")
public class PayrollController {

    @Autowired
    private PayrollRepository payrollRepository;

    @GetMapping
    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    public List<Payroll> getAllPayrolls() {
        return payrollRepository.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    public Payroll getPayrollById(@PathVariable Long id) {
        return payrollRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payroll not found with id: " + id));
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Payroll createPayroll(@RequestBody Payroll payroll) {
        return payrollRepository.save(payroll);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Payroll updatePayroll(@PathVariable Long id, @RequestBody Payroll payrollDetails) {
        Payroll payroll =payrollRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payroll not found with id: " + id));
        payroll.setEmployeeName(payrollDetails.getEmployeeName());
        payroll.setSalary(payrollDetails.getSalary());
        return payrollRepository.save(payroll);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deletePayroll(@PathVariable Long id) {
        Payroll payroll =payrollRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payroll not found with id: " + id));
        payrollRepository.delete(payroll);
        return ResponseEntity.ok().build();
    }
}

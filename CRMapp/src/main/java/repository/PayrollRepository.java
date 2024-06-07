package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Payroll;

public interface PayrollRepository extends JpaRepository<Payroll, Long> {

}

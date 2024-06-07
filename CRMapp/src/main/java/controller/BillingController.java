package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import model.Billing;
import repository.BillingRepository;

@RestController
@RequestMapping("/billing")
public class BillingController {

    @Autowired
    private BillingRepository billingRepository;

    @GetMapping
    @PreAuthorize("hasAnyRole('ACCOUNTANT', 'ADMIN')")
    public List<Billing> getAllBillings() {
        return billingRepository.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ACCOUNTANT', 'ADMIN')")
    public Billing getBillingById(@PathVariable Long id) {
        return billingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Billing not found with id: " + id));
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Billing createBilling(@RequestBody Billing billing) {
        return billingRepository.save(billing);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Billing updateBilling(@PathVariable Long id, @RequestBody Billing billingDetails) {
        Billing billing = billingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Billing not found with id: " + id));
        billing.setDescription(billingDetails.getDescription());
        billing.setAmount(billingDetails.getAmount());
        return billingRepository.save(billing);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteBilling(@PathVariable Long id) {
        Billing billing = billingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Billing not found with id: " + id));
        billingRepository.delete(billing);
        return ResponseEntity.ok().build();
    }
}

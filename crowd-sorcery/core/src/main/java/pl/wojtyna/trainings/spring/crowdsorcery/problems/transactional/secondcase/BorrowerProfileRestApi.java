package pl.wojtyna.trainings.spring.crowdsorcery.problems.transactional.secondcase;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/problems/borrowers")
public class BorrowerProfileRestApi {

    private final BorrowerProfileManager borrowerProfileManager;

    public BorrowerProfileRestApi(BorrowerProfileManager borrowerProfileManager) {this.borrowerProfileManager = borrowerProfileManager;}

    @PostMapping
    public void create(@RequestBody BorrowerProfile borrowerProfile) {
        borrowerProfileManager.save(borrowerProfile);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody BorrowerProfile newProfile) {
        borrowerProfileManager.update(newProfile);
    }

    @GetMapping("/{id}")
    public Optional<BorrowerProfile> getProfile(@PathVariable("id") String id) {
        return borrowerProfileManager.find(id);
    }
}

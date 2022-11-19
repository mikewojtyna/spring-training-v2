package pl.wojtyna.trainings.spring.crowdsorcery.borrower;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/borrower-module/api/v0/borrowers")
public class BorrowerRestApi {

    private final BorrowerService borrowerService;

    public BorrowerRestApi(BorrowerService borrowerService) {
        this.borrowerService = borrowerService;
    }

    @GetMapping
    public List<Borrower> fetchAllBorrowers() {
        return borrowerService.findAll();
    }

    @GetMapping("/confidential")
    public List<Borrower> fetchConfidentialBorrowers() {
        return List.of(new Borrower("111", "[Confidential] George Borrower"),
                       new Borrower("222", "[Confidential] Henry Borrower"));
    }
}

package pl.wojtyna.trainings.spring.crowdsorcery.borrower;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/borrower-module/api/v0/borrowers")
public class BorrowerRestApi {

    @GetMapping
    public List<Borrower> fetchAllBorrowers() {
        return List.of(new Borrower("123", "George Borrower"),
                       new Borrower("456", "Henry Borrower"),
                       new Borrower("789", "Martin Borrower"));
    }

    @GetMapping("/confidential")
    public List<Borrower> fetchConfidentialBorrowers() {
        return List.of(new Borrower("111", "[Confidential] George Borrower"),
                       new Borrower("222", "[Confidential] Henry Borrower"));
    }
}

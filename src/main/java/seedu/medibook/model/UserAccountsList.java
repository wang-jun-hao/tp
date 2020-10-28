package seedu.medibook.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserAccountsList {

    private List<Account> accountsList = new ArrayList<>();

    public void addAccount(Account account) {
        this.accountsList.add(account);
    }

    /**
     * Checks if a given username and password are representative of an account.
     */
    public Optional<Account> check(String username, String password) {
        Optional<Account> result = Optional.empty();
        for (Account account : accountsList) {
            if (account.check(username, password)) {
                result = Optional.of(account);
            }
        }
        return result;
    }
}

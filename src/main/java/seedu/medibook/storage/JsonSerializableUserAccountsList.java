package seedu.medibook.storage;

import java.util.ArrayList;
import java.util.List;

import seedu.medibook.commons.exceptions.IllegalValueException;
import seedu.medibook.model.Account;
import seedu.medibook.model.UserAccountsList;

class JsonSerializableUserAccountsList {

    private final List<JsonAdaptedAccount> accounts = new ArrayList<>();

    public UserAccountsList toModelType() throws IllegalValueException {
        UserAccountsList userAccountsList = new UserAccountsList();
        for (JsonAdaptedAccount jsonAdaptedAccount : accounts) {
            Account account = jsonAdaptedAccount.toModelType();
            userAccountsList.addAccount(account);
        }
        return userAccountsList;
    }
}

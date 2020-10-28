package seedu.medibook.storage;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.medibook.commons.exceptions.IllegalValueException;
import seedu.medibook.model.Account;
import seedu.medibook.model.UserAccountsList;

@JsonRootName(value = "accounts")
class JsonSerializableUserAccountsList {

    @JsonProperty("accounts")
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

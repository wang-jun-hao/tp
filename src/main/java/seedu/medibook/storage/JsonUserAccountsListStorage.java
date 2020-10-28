package seedu.medibook.storage;

import java.nio.file.Path;
import java.util.Optional;

import seedu.medibook.commons.exceptions.DataConversionException;
import seedu.medibook.commons.exceptions.IllegalLoginException;
import seedu.medibook.commons.exceptions.IllegalValueException;
import seedu.medibook.commons.util.JsonUtil;
import seedu.medibook.model.Account;
import seedu.medibook.model.UserAccountsList;

public class JsonUserAccountsListStorage implements UserAccountsListStorage {

    private final Path filepath;

    public JsonUserAccountsListStorage(Path filepath) {
        this.filepath = filepath;
    }

    @Override
    public Path getUserAccountFilepath() {
        return this.filepath;
    }

    @Override
    public Optional<Account> login(String username, String password) throws DataConversionException,
            IllegalLoginException, IllegalValueException {
        Optional<JsonSerializableUserAccountsList> jsonUserAccountList = JsonUtil.readJsonFile(
                filepath, JsonSerializableUserAccountsList.class);
        UserAccountsList accountsList;
        if (jsonUserAccountList.isPresent()) {
            accountsList = jsonUserAccountList.get().toModelType();
            Optional<Account> result = accountsList.check(username, password);
            if (result.isPresent()) {
                return result;
            } else {
                throw new IllegalLoginException("Invalid username/password");
            }
        } else {
            throw new IllegalLoginException("Invalid username/password");
        }
    }
}

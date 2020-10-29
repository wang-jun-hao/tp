package seedu.medibook.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.medibook.commons.exceptions.DataConversionException;
import seedu.medibook.commons.exceptions.IllegalLoginException;
import seedu.medibook.commons.exceptions.IllegalValueException;
import seedu.medibook.commons.util.FileUtil;
import seedu.medibook.commons.util.JsonUtil;
import seedu.medibook.model.Account;
import seedu.medibook.model.AdminAccount;
import seedu.medibook.model.UserAccountsList;
import seedu.medibook.model.commonfields.Name;
import seedu.medibook.model.doctor.Doctor;
import seedu.medibook.model.doctor.Mcr;

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
        if (username.equals("admin") && password.equals("admin")) {
            return Optional.of(new AdminAccount());
        }
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

    @Override
    public void createAccount(String username, String password, String doctorName, String doctorMcr) throws
            DataConversionException, IOException, IllegalValueException {

        requireNonNull(doctorName);
        requireNonNull(doctorMcr);
        if (!Name.isValidName(doctorName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }

        if (!Mcr.isValidMcr(doctorMcr)) {
            throw new IllegalValueException(Mcr.MESSAGE_CONSTRAINTS);
        }

        if (username.length() < 5) {
            throw new IllegalValueException(Account.MESSAGE_USERNAME_CONSTRAINTS);
        }

        if (password.length() < 5) {
            throw new IllegalValueException(Account.MESSAGE_PASSWORD_CONSTRAINTS);
        }

        Account newAccount = new Account(username, password, new Doctor(new Name(doctorName), new Mcr(doctorMcr)));

        System.out.println(filepath);

        if (FileUtil.isFileExists(filepath)) {
            JsonSerializableUserAccountsList jsonUserAccountList = JsonUtil.readJsonFile(
                    filepath, JsonSerializableUserAccountsList.class).get();
            UserAccountsList accountsList = jsonUserAccountList.toModelType();

            if (accountsList.usernameExists(newAccount)) {
                throw new IllegalValueException("Username already exists");
            }

            if (accountsList.mcrExists(newAccount)) {
                throw new IllegalValueException("This MCR already has an account");
            }
            accountsList.addAccount(newAccount);
            jsonUserAccountList = new JsonSerializableUserAccountsList(accountsList);
            JsonUtil.saveJsonFile(jsonUserAccountList, filepath);

        } else {
            FileUtil.createFile(filepath);
            UserAccountsList newAccountsList = new UserAccountsList();
            newAccountsList.addAccount(newAccount);
            JsonSerializableUserAccountsList newJsonUserAccountList =
                    new JsonSerializableUserAccountsList(newAccountsList);
            JsonUtil.saveJsonFile(newJsonUserAccountList, filepath);
        }
    }
}

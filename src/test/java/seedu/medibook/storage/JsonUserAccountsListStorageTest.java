package seedu.medibook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.medibook.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.medibook.commons.exceptions.DataConversionException;
import seedu.medibook.commons.exceptions.IllegalLoginException;
import seedu.medibook.commons.exceptions.IllegalValueException;
import seedu.medibook.model.Account;
import seedu.medibook.model.commonfields.Name;
import seedu.medibook.model.doctor.Doctor;
import seedu.medibook.model.doctor.Mcr;

public class JsonUserAccountsListStorageTest {
    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonUserAccountsListStorageTest");

    private static final Path VALID_DIRECTORY = TEST_DATA_FOLDER.resolve("accounts.json");
    private static final Path INVALID_DIRECTORY = TEST_DATA_FOLDER.resolve("invalid.json");

    @TempDir
    public Path testFolder;

    @Test
    public void login_invalidInfo_throwsIllegalLoginException() {
        JsonUserAccountsListStorage storage = new JsonUserAccountsListStorage(VALID_DIRECTORY);
        assertThrows(IllegalLoginException.class, () -> storage.login("invaliduser", "invalidpass"));
    }

    @Test
    public void login_invalidFilepath_throwsIllegalLoginException() {
        JsonUserAccountsListStorage storage = new JsonUserAccountsListStorage(INVALID_DIRECTORY);
        assertThrows(IllegalLoginException.class, () -> storage.login("user1", "password1"));
    }

    @Test
    public void login_validInfo_success() throws IllegalValueException, DataConversionException, IllegalLoginException {
        JsonUserAccountsListStorage storage = new JsonUserAccountsListStorage(VALID_DIRECTORY);
        Optional<Account> expectedResult = Optional.of(new Account("user1", "password1",
                new Doctor(new Name("Doctor"), new Mcr("M12345Q"))));
        Optional<Account> result = storage.login("user1", "password1");
        assertEquals(result, expectedResult);
    }
}

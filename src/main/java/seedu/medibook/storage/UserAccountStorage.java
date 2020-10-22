package seedu.medibook.storage;

import java.nio.file.Path;

public interface UserAccountStorage {

    Path getUserAccountFilepath();

    boolean isAccount(String username, String password);

}

package seedu.medibook.storage;

import java.nio.file.Path;

public class JsonUserAccountStorage implements UserAccountStorage {

    private final Path filepath;

    public JsonUserAccountStorage(Path filepath) {
        this.filepath = filepath;
    }

    @Override
    public Path getUserAccountFilepath() {
        return this.filepath;
    }

    @Override
    public boolean isAccount(String username, String password) {
        return true;
    }
}

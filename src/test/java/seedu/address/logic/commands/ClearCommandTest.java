package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPatients.getTypicalMediBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.MediBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyMediBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyMediBook_success() {
        Model model = new ModelManager(getTypicalMediBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalMediBook(), new UserPrefs());
        expectedModel.setMediBook(new MediBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}

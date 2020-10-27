package seedu.medibook.model.medicaldetail;

public class Condition extends Tag {

    /**
     * Constructs a medical {@code Condition}.
     *
     * @param tagName A valid tag name.
     */
    public Condition(String tagName) {
        super(tagName);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Condition // instanceof handles nulls
            && tagName.equals(((Condition) other).tagName)); // state check
    }
}

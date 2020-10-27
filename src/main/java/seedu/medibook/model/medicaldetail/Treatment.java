package seedu.medibook.model.medicaldetail;

public class Treatment extends Tag {

    /**
     * Constructs a medical {@code Treatment}.
     *
     * @param tagName A valid tag name.
     */
    public Treatment(String tagName) {
        super(tagName);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Treatment // instanceof handles nulls
            && tagName.equals(((Treatment) other).tagName)); // state check
    }
}

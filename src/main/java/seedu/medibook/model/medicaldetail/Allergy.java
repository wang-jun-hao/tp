package seedu.medibook.model.medicaldetail;

public class Allergy extends Tag {

    /**
     * Constructs an {@code Allergy}.
     *
     * @param tagName A valid tag name.
     */
    public Allergy(String tagName) {
        super(tagName);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Allergy // instanceof handles nulls
            && tagName.equals(((Allergy) other).tagName)); // state check
    }
}

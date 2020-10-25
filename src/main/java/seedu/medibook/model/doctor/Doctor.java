package seedu.medibook.model.doctor;

import static seedu.medibook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.medibook.model.commonfields.Name;

/**
 * Represents a doctor in MediBook.
 */
public class Doctor {
    private static final String TITLE = "Dr";

    private final Name name;
    private final Mcr mcr;

    /**
     * Constructs a doctor object with the specified name and MCR.
     * @param name Alphanumberic name.
     * @param mcr Valid MCR.
     */
    public Doctor(Name name, Mcr mcr) {
        requireAllNonNull(name, mcr);
        this.name = name;
        this.mcr = mcr;
    }

    public String getDisplayStringName() {
        return TITLE + " " + name.toString();
    }

    public String getStringNameNoTitle() {
        return name.toString();
    }

    public String getDisplayStringMcr() {
        return mcr.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Doctor)) {
            return false;
        }

        Doctor otherDoctor = (Doctor) other;
        return otherDoctor.name.equals(name) && otherDoctor.mcr.equals(mcr);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, mcr);
    }

    @Override
    public String toString() {
        return TITLE + " " + name.toString() + " (" + mcr.toString() + ")";
    }
}

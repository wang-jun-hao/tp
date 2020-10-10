package seedu.address.testutil;

import seedu.address.model.MediBook;
import seedu.address.model.patient.Patient;

/**
 * A utility class to help with building MediBook objects.
 * Example usage: <br>
 *     {@code MediBook ab = new MediBookBuilder().withPatient("John", "Doe").build();}
 */
public class MediBookBuilder {

    private MediBook mediBook;

    public MediBookBuilder() {
        mediBook = new MediBook();
    }

    public MediBookBuilder(MediBook mediBook) {
        this.mediBook = mediBook;
    }

    /**
     * Adds a new {@code Patient} to the {@code MediBook} that we are building.
     */
    public MediBookBuilder withPatient(Patient patient) {
        mediBook.addPatient(patient);
        return this;
    }

    public MediBook build() {
        return mediBook;
    }
}

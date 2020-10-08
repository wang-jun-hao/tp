package seedu.address.model.patient;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Patient in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Patient {

    // Identity fields
    private final Ic ic;
    private final Name name;
    private final DateOfBirth dateOfBirth;
    private final Phone phone;
    private final Optional<Email> email;

    // Data fields
    private final Optional<Address> address;
    private final Optional<Height> height;
    private final Optional<Weight> weight;
    private final Optional<Bmi> bmi;
    private final Optional<BloodType> bloodType;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Patient(Ic ic, Name name, DateOfBirth dateOfBirth, Phone phone, Optional<Email> email,
                   Optional<Address> address, Optional<Height> height, Optional<Weight> weight,
                   Optional<BloodType> bloodType, Set<Tag> tags) {
        requireAllNonNull(ic, name, dateOfBirth, phone, email, address, height, weight, bloodType, tags);
        this.ic = ic;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.height = height;
        this.weight = weight;
        this.bloodType = bloodType;
        this.tags.addAll(tags);

        if (height.isEmpty() || weight.isEmpty()) {
            this.bmi = Optional.empty();
        } else {
            this.bmi = Optional.of(new Bmi(weight.get(), height.get()));
        }
    }

    /**
     * Every field must be present and not null.
     * Overloaded constructor of Person with an additional bmi field that has already been computed to bypass
     * unnecessary re-computation of bmi.
     */
    public Patient(Ic ic, Name name, DateOfBirth dateOfBirth, Phone phone, Optional<Email> email,
                   Optional<Address> address, Optional<Height> height, Optional<Weight> weight, Optional<Bmi> bmi,
                   Optional<BloodType> bloodType, Set<Tag> tags) {
        requireAllNonNull(ic, name, dateOfBirth, phone, email, address, height, weight, bloodType, tags);
        this.ic = ic;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
        this.bloodType = bloodType;
        this.tags.addAll(tags);
    }

    public Ic getIc() {
        return ic;
    }

    public Name getName() {
        return name;
    }

    public DateOfBirth getDateOfBirth() {
        return dateOfBirth;
    }

    public Phone getPhone() {
        return phone;
    }

    public Optional<Email> getEmail() {
        return email;
    }

    /**
     * Represents the email of the patient for the UI to display.
     * @return a string representing the email of the patient or N/A if there
     * is no email.
     */
    public String stringEmail() {
        if (getEmail().isPresent()) {
            return getEmail().get().toString();
        } else {
            return "N/A";
        }
    }

    public Optional<Address> getAddress() {
        return address;
    }

    /**
     * Represents the address of the patient for the UI to display.
     * @return a string representing the address of the patient or N/A if there
     * is no address.
     */
    public String stringAddress() {
        if (getAddress().isPresent()) {
            return getAddress().get().toString();
        } else {
            return "N/A";
        }
    }

    public Optional<Height> getHeight() {
        return height;
    }

    /**
     * Represents the height of the patient for the UI to display.
     * @return a string representing the height of the patient or N/A if there
     * is no height.
     */
    public String stringHeight() {
        if (getHeight().isPresent()) {
            return getHeight().get().toString();
        } else {
            return "N/A";
        }
    }

    public Optional<Weight> getWeight() {
        return weight;
    }

    /**
     * Represents the weight of the patient for the UI to display.
     * @return a string representing the weight of the patient or N/A if there
     * is no weight.
     */
    public String stringWeight() {
        if (getWeight().isPresent()) {
            return getWeight().get().toString();
        } else {
            return "N/A";
        }
    }

    public Optional<Bmi> getBmi() {
        return bmi;
    }

    /**
     * Represents the bmi of the patient for the UI to display.
     * @return a string representing the bmi of the patient or N/A if there
     * is no bmi.
     */
    public String stringBmi() {
        if (getBmi().isPresent()) {
            return getBmi().get().toString();
        } else {
            return "N/A";
        }
    }

    public Optional<BloodType> getBloodType() {
        return bloodType;
    }

    /**
     * Represents the bloodtype of the patient for the UI to display.
     * @return a string representing the bloodtype of the patient or N/A if there
     * is no bloodtype.
     */
    public String stringBloodType() {
        if (getBloodType().isPresent()) {
            return getBloodType().get().toString();
        } else {
            return "N/A";
        }
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both patients of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two patients.
     */
    public boolean isSamePatient(Patient otherPatient) {
        if (otherPatient == this) {
            return true;
        }

        return otherPatient != null
                && otherPatient.getIc().equals(getIc());
    }

    /**
     * Returns true if both patients have the same identity and data fields.
     * This defines a stronger notion of equality between two patients.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Patient)) {
            return false;
        }

        Patient otherPatient = (Patient) other;
        return otherPatient.getIc().equals(getIc())
                && otherPatient.getName().equals(getName())
                && otherPatient.getDateOfBirth().equals(getDateOfBirth())
                && otherPatient.getPhone().equals(getPhone())
                && otherPatient.getEmail().equals(getEmail())
                && otherPatient.getAddress().equals(getAddress())
                && otherPatient.getHeight().equals(getHeight())
                && otherPatient.getWeight().equals(getWeight())
                && otherPatient.getBmi().equals(getBmi())
                && otherPatient.getBloodType().equals(getBloodType())
                && otherPatient.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(ic, name, dateOfBirth, phone, email, address, height, weight, bmi,
                            bloodType, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getIc())
                .append(" Name ")
                .append(getName())
                .append(" Date of Birth: ")
                .append(getDateOfBirth())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(stringEmail())
                .append(" Address: ")
                .append(stringAddress())
                .append(" Height: ")
                .append(stringHeight())
                .append(" Weight: ")
                .append(stringWeight())
                .append(" BMI: ")
                .append(stringBmi())
                .append(" Blood type: ")
                .append(stringBloodType())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}

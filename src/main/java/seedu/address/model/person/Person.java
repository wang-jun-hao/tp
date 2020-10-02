package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Ic ic;
    private final Name name;
    private final DateOfBirth dateOfBirth;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Height height;
    private final Weight weight;
    private final BloodType bloodType;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Ic ic, Name name, DateOfBirth dateOfBirth, Phone phone, Email email, Address address, Height height,
                  Weight weight, BloodType bloodType, Set<Tag> tags) {
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

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Height getHeight() {
        return height;
    }

    public Weight getWeight() {
        return weight;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getIc().equals(getIc());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getIc().equals(getIc())
                && otherPerson.getName().equals(getName())
                && otherPerson.getDateOfBirth().equals(getDateOfBirth())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getHeight().equals(getHeight())
                && otherPerson.getWeight().equals(getWeight())
                && otherPerson.getBloodType().equals(getBloodType())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(ic, name, dateOfBirth, phone, email, address, height, weight,
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
                .append(getEmail())
                .append(" Address: ")
                .append(getAddress())
                .append(" Height: ")
                .append(getHeight())
                .append(" Weight: ")
                .append(getWeight())
                .append(" Blood type: ")
                .append(getBloodType())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}

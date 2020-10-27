package seedu.medibook.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.medibook.logic.commands.EditCommand;
import seedu.medibook.model.commonfields.Name;
import seedu.medibook.model.patient.Address;
import seedu.medibook.model.patient.BloodType;
import seedu.medibook.model.patient.DateOfBirth;
import seedu.medibook.model.patient.Email;
import seedu.medibook.model.patient.Height;
import seedu.medibook.model.patient.Ic;
import seedu.medibook.model.patient.Patient;
import seedu.medibook.model.patient.Phone;
import seedu.medibook.model.patient.Weight;
import seedu.medibook.model.medicaldetail.Tag;

/**
 * A utility class to help with building EditPatientDescriptor objects.
 */
public class EditPatientDescriptorBuilder {

    private EditCommand.EditPatientDescriptor descriptor;

    public EditPatientDescriptorBuilder() {
        descriptor = new EditCommand.EditPatientDescriptor();
    }

    public EditPatientDescriptorBuilder(EditCommand.EditPatientDescriptor descriptor) {
        this.descriptor = new EditCommand.EditPatientDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPatientDescriptor} with fields containing {@code patient}'s details
     */
    public EditPatientDescriptorBuilder(Patient patient) {
        descriptor = new EditCommand.EditPatientDescriptor();
        descriptor.setIc(patient.getIc());
        descriptor.setName(patient.getName());
        descriptor.setDateOfBirth(patient.getDateOfBirth());
        descriptor.setPhone(patient.getPhone());
        descriptor.setEmail(patient.getEmail().get());
        descriptor.setAddress(patient.getAddress().get());
        descriptor.setHeight(patient.getHeight().get());
        descriptor.setWeight(patient.getWeight().get());
        descriptor.setBloodType(patient.getBloodType().get());
        descriptor.setTags(patient.getTags());
    }

    /**
     * Sets the {@code Ic} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withIc(String ic) {
        descriptor.setIc(new Ic(ic));
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code DateOfBirth} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withDateOfBirth(String dateOfBirth) {
        descriptor.setDateOfBirth(new DateOfBirth(dateOfBirth));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Height} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withHeight(String height) {
        descriptor.setHeight(new Height(height));
        return this;
    }

    /**
     * Sets the {@code Weight} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withWeight(String weight) {
        descriptor.setWeight(new Weight(weight));
        return this;
    }

    /**
     * Sets the {@code BloodType} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withBloodType(String bloodType) {
        descriptor.setBloodType(new BloodType(bloodType));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPatientDescriptor}
     * that we are building.
     */
    public EditPatientDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditCommand.EditPatientDescriptor build() {
        return descriptor;
    }
}

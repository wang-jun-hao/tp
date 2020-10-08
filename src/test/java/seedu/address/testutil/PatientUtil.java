package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOOD_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.model.patient.Patient;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Patient.
 */
public class PatientUtil {

    /**
     * Returns an add command string for adding the {@code patient}.
     */
    public static String getAddCommand(Patient patient) {
        return AddCommand.COMMAND_WORD + " " + getPatientDetails(patient);
    }

    /**
     * Returns the part of command string for the given {@code patient}'s details.
     */
    public static String getPatientDetails(Patient patient) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_IC + patient.getIc().ic + " ");
        sb.append(PREFIX_NAME + patient.getName().fullName + " ");
        sb.append(PREFIX_DOB + patient.getDateOfBirth().value + " ");
        sb.append(PREFIX_PHONE + patient.getPhone().value + " ");

        if (patient.getEmail().isPresent()) {
            sb.append(PREFIX_EMAIL + patient.stringEmail() + " ");
        }

        if (patient.getAddress().isPresent()) {
            sb.append(PREFIX_ADDRESS + patient.stringAddress() + " ");
        }

        if (patient.getHeight().isPresent()) {
            sb.append(PREFIX_HEIGHT + patient.stringHeight() + " ");
        }

        if (patient.getWeight().isPresent()) {
            sb.append(PREFIX_WEIGHT + patient.stringWeight() + " ");
        }

        if (patient.getBloodType().isPresent()) {
            sb.append(PREFIX_BLOOD_TYPE + patient.stringBloodType() + " ");
        }

        patient.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPatientDescriptor}'s details.
     */
    public static String getEditPatientDescriptorDetails(EditCommand.EditPatientDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getIc().ifPresent(ic -> sb.append(PREFIX_IC).append(ic.ic).append(" "));
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getDateOfBirth()
            .ifPresent(dateOfBirth -> sb.append(PREFIX_DOB).append(dateOfBirth.value).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));

        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.get().value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.get().value).append(" "));
        descriptor.getHeight().ifPresent(height -> sb.append(PREFIX_HEIGHT).append(height.get().value).append(" "));
        descriptor.getWeight().ifPresent(weight -> sb.append(PREFIX_WEIGHT).append(weight.get().value).append(" "));
        descriptor.getBloodType().ifPresent(bloodType -> sb.append(PREFIX_BLOOD_TYPE)
                .append(bloodType.get().bloodType.label).append(" "));

        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}

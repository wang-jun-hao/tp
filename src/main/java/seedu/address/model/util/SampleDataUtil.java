package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.BloodType;
import seedu.address.model.patient.DateOfBirth;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.Height;
import seedu.address.model.patient.Ic;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Phone;
import seedu.address.model.patient.Weight;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Patient[] getSamplePatients() {
        return new Patient[] {
            new Patient(new Ic("S9123456A"), new Name("Alex Yeoh"), new DateOfBirth("15-05-1991"),
                    new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"), new Height("173"), new Weight("67.4"),
                    new BloodType("A+"), getTagSet("friends")),
            new Patient(new Ic("S8786858D"), new Name("Bernice Yu"), new DateOfBirth("17-09-1987"),
                    new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Height("166"), new Weight("48.2"),
                    new BloodType("B+"), getTagSet("colleagues", "friends")),
            new Patient(new Ic("T1234567G"), new Name("Charlotte Oliveiro"), new DateOfBirth("28-02-2012"),
                    new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new Height("175"), new Weight("58.5"),
                    new BloodType("A+"), getTagSet("neighbours")),
            new Patient(new Ic("F7654321K"), new Name("David Li"), new DateOfBirth("20-12-1976"),
                    new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new Height("169"), new Weight("64.9"),
                    new BloodType("O+"), getTagSet("family")),
            new Patient(new Ic("G1928374R"), new Name("Irfan Ibrahim"), new DateOfBirth("11-04-2000"),
                    new Phone("92492021"), new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"), new Height("180"), new Weight("80.5"),
                    new BloodType("AB+"), getTagSet("classmates")),
            new Patient(new Ic("S9182736Q"), new Name("Roy Balakrishnan"), new DateOfBirth("14-06-1991"),
                    new Phone("92624417"), new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"), new Height("165"), new Weight("60.6"),
                    new BloodType("A-"), getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Patient samplePatient : getSamplePatients()) {
            sampleAb.addPatient(samplePatient);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}

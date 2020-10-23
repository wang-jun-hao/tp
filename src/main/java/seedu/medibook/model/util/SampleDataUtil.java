package seedu.medibook.model.util;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.medibook.model.MediBook;
import seedu.medibook.model.ReadOnlyMediBook;
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
import seedu.medibook.model.tag.Tag;

/**
 * Contains utility methods for populating {@code MediBook} with sample data.
 */
public class SampleDataUtil {
    public static Patient[] getSamplePatients() {
        return new Patient[] {
            new Patient(new Ic("S9123456A"), new Name("Alex Yeoh"), new DateOfBirth("15-05-1991"),
                    new Phone("87438807"), Optional.of(new Email("alexyeoh@example.com")),
                    Optional.of(new Address("Blk 30 Geylang Street 29, #06-40")), Optional.of(new Height("173")),
                    Optional.of(new Weight("67.4")), Optional.of(new BloodType("A+")), getTagSet("friends")),
            new Patient(new Ic("S8786858D"), new Name("Bernice Yu"), new DateOfBirth("17-09-1987"),
                    new Phone("99272758"), Optional.of(new Email("berniceyu@example.com")),
                    Optional.of(new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18")),
                    Optional.of(new Height("166")), Optional.of(new Weight("48.2")),
                    Optional.of(new BloodType("B+")), getTagSet("colleagues", "friends")),
            new Patient(new Ic("T1234567G"), new Name("Charlotte Oliveiro"), new DateOfBirth("28-02-2012"),
                    new Phone("93210283"), Optional.of(new Email("charlotte@example.com")),
                    Optional.of(new Address("Blk 11 Ang Mo Kio Street 74, #11-04")), Optional.of(new Height("175")),
                    Optional.of(new Weight("58.5")), Optional.of(new BloodType("A+")), getTagSet("neighbours")),
            new Patient(new Ic("F7654321K"), new Name("David Li"), new DateOfBirth("20-12-1976"),
                    new Phone("91031282"), Optional.of(new Email("lidavid@example.com")),
                    Optional.of(new Address("Blk 436 Serangoon Gardens Street 26, #16-43")),
                    Optional.of(new Height("169")), Optional.of(new Weight("64.9")),
                    Optional.of(new BloodType("O+")), getTagSet("family")),
            new Patient(new Ic("G1928374R"), new Name("Irfan Ibrahim"), new DateOfBirth("11-04-2000"),
                    new Phone("92492021"), Optional.of(new Email("irfan@example.com")),
                    Optional.of(new Address("Blk 47 Tampines Street 20, #17-35")), Optional.of(new Height("180")),
                    Optional.of(new Weight("80.5")), Optional.of(new BloodType("AB+")),
                    getTagSet("classmates")),
            new Patient(new Ic("S9182736Q"), new Name("Roy Balakrishnan"), new DateOfBirth("14-06-1991"),
                    new Phone("92624417"), Optional.of(new Email("royb@example.com")),
                    Optional.of(new Address("Blk 45 Aljunied Street 85, #11-31")), Optional.of(new Height("165")),
                    Optional.of(new Weight("60.6")), Optional.of(new BloodType("A-")), getTagSet("colleagues"))
        };
    }

    public static ReadOnlyMediBook getSampleMediBook() {
        MediBook sampleMb = new MediBook();
        for (Patient samplePatient : getSamplePatients()) {
            sampleMb.addPatient(samplePatient);
        }
        return sampleMb;
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

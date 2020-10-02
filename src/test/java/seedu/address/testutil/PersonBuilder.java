package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.BloodType;
import seedu.address.model.person.DateOfBirth;
import seedu.address.model.person.Email;
import seedu.address.model.person.Height;
import seedu.address.model.person.Ic;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Weight;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_IC = "S5436781A";
    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_DOB = "08-09-1954";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_HEIGHT = "170";
    public static final String DEFAULT_WEIGHT = "58.7";
    public static final String DEFAULT_BLOOD_TYPE = "A+";

    private Ic ic;
    private Name name;
    private DateOfBirth dateOfBirth;
    private Phone phone;
    private Email email;
    private Address address;
    private Height height;
    private Weight weight;
    private BloodType bloodType;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        ic = new Ic(DEFAULT_IC);
        name = new Name(DEFAULT_NAME);
        dateOfBirth = new DateOfBirth(DEFAULT_DOB);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        height = new Height(DEFAULT_HEIGHT);
        weight = new Weight(DEFAULT_WEIGHT);
        bloodType = new BloodType(DEFAULT_BLOOD_TYPE);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        ic = personToCopy.getIc();
        name = personToCopy.getName();
        dateOfBirth = personToCopy.getDateOfBirth();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        height = personToCopy.getHeight();
        weight = personToCopy.getWeight();
        bloodType = personToCopy.getBloodType();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Ic} of the {@code Person} that we are building.
     */
    public PersonBuilder withIc(String ic) {
        this.ic = new Ic(ic);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code DateOfBirth} of the {@code Person} that we are building.
     */
    public PersonBuilder withDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = new DateOfBirth(dateOfBirth);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Height} of the {@code Person} that we are building.
     */
    public PersonBuilder withHeight(String height) {
        this.height = new Height(height);
        return this;
    }

    /**
     * Sets the {@code Weight} of the {@code Person} that we are building.
     */
    public PersonBuilder withWeight(String weight) {
        this.weight = new Weight(weight);
        return this;
    }

    /**
     * Sets the {@code BloodType} of the {@code Person} that we are building.
     */
    public PersonBuilder withBloodType(String bloodType) {
        this.bloodType = new BloodType(bloodType);
        return this;
    }

    /**
    * Creates Person object based on the fields specified in this PersonBuilder object.
    */
    public Person build() {
        return new Person(ic, name, dateOfBirth, phone, email, address, height, weight,
                          bloodType, tags);
    }

}

package tutman.tuiniverse.testutil;

import static tutman.tuiniverse.testutil.TypicalLessons.Y3_MATH;

import java.time.YearMonth;
import java.util.HashSet;
import java.util.Set;

import tutman.tuiniverse.model.lesson.LessonList;
import tutman.tuiniverse.model.payment.Payment;
import tutman.tuiniverse.model.payment.PaymentList;
import tutman.tuiniverse.model.payment.TotalAmount;
import tutman.tuiniverse.model.payment.UnpaidAmount;
import tutman.tuiniverse.model.person.Email;
import tutman.tuiniverse.model.person.Name;
import tutman.tuiniverse.model.person.Phone;
import tutman.tuiniverse.model.person.student.Address;
import tutman.tuiniverse.model.person.student.Student;
import tutman.tuiniverse.model.person.student.tag.Tag;
import tutman.tuiniverse.model.util.SampleDataUtil;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final Payment DEFAULT_PAYMENT = new Payment(
            YearMonth.parse("2025-10"),
            new TotalAmount(400f),
            new UnpaidAmount(0f)
    );

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private LessonList ll = new LessonList();
    private PaymentList pl = new PaymentList();
    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        ll.addLesson(Y3_MATH);
        pl.addPayment(DEFAULT_PAYMENT);
    }

    /**
     * Initializes the StudentBuilder with the data of {@code personToCopy}.
     */
    public StudentBuilder(Student personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        tags = new HashSet<>(personToCopy.getTags());
        ll = personToCopy.getLessonList();
        pl = personToCopy.getPayments();
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public StudentBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Student} that we are building.
     */
    public StudentBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Student} that we are building.
     */
    public StudentBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Lessonlist} of the {@code Person} that we are building.
     */
    public StudentBuilder withLessonList(LessonList ll) {
        this.ll = new LessonList(ll.getLessons());
        return this;
    }

    /**
     * Sets the {@code Paymentlist} of the {@code Person} that we are building.
     */
    public StudentBuilder withPaymentList(PaymentList pl) {
        this.pl = pl;
        return this;
    }

    public Student build() {
        return new Student(name, phone, email, address, tags, ll, pl);
    }

}

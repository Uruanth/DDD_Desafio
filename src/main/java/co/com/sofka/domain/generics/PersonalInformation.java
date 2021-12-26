package co.com.sofka.domain.generics;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class PersonalInformation implements ValueObject<PersonalInformation.Properties> {

    private final String name;
    private final String phoneNumber;
    private final String job;

    public PersonalInformation(String name, String phoneNumber, String job) {
        if(name.isBlank()) throw new IllegalArgumentException("name cannot be empty");
        if(phoneNumber.isBlank() || phoneNumber.length()<5) throw new IllegalArgumentException("phoneNumber cannot be empty");
        if(job.isBlank()) throw new IllegalArgumentException("job cannot be empty");

        this.name = Objects.requireNonNull(name, "name cannot be null");
        this.phoneNumber = Objects.requireNonNull(phoneNumber, "phoneNumber cannot be null");
        this.job = Objects.requireNonNull(job, "job cannot be null");
    }


    public PersonalInformation updatePhoneNumber(String phoneNumberNew){
        return new PersonalInformation(name, phoneNumberNew, job);
    }

    @Override
    public Properties value() {
         return new Properties() {
            @Override
            public String name() {
                return name;
            }

            @Override
            public String phoneNumber() {
                return phoneNumber;
            }

            @Override
            public String job() {
                return job;
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonalInformation that = (PersonalInformation) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;
        return job != null ? job.equals(that.job) : that.job == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (job != null ? job.hashCode() : 0);
        return result;
    }

    interface Properties{
        String name();
        String phoneNumber();
        String job();
    }
}

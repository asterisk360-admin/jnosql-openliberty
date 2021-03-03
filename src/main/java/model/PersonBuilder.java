package model;

import java.util.List;

public class PersonBuilder {
    private Long id;
    private String name;
    private List<String> phones;

    PersonBuilder() {
    }

    public PersonBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public PersonBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PersonBuilder withPhones(List<String> phones) {
        this.phones = phones;
        return this;
    }

    public Person build() {
        return new Person(id, name, phones);
    }
}
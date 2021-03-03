package app;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

import jakarta.nosql.document.Document;
import jakarta.nosql.document.DocumentQuery;
import jakarta.nosql.mapping.document.DocumentTemplate;
import model.Person;

import static jakarta.nosql.document.DocumentQuery.select;

public class App {

    public static void main(String[] args) {

        Random random = new Random();
        Long id = random.nextLong();
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            Person person = Person.builder().withPhones(Arrays.asList("234", "432")).withName("Name").withId(id).build();

            DocumentTemplate documentTemplate = container.select(DocumentTemplate.class).get();
            Person saved = documentTemplate.insert(person);
            System.out.println("Person saved" + saved);

            DocumentQuery query = select().from("Person").where("_id").eq(id).build();
            Optional<Person> personOptional = documentTemplate.singleResult(query);
            System.out.println("Entity found: " + personOptional);

        }
    }

    private App() {

    }

}
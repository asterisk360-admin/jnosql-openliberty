package app;

import dao.PersonRepository;
import model.Person;
import org.eclipse.jnosql.mapping.DatabaseQualifier;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class App2 {


	  public static void main(String[] args) {

	    Random random = new Random();
	    Long id = random.nextLong();

	    try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

	      Person person = Person.builder().
	      withPhones(Arrays.asList("234", "432"))
	      .withName("Name")
	      .withId(id)
	      .build();

	      PersonRepository repository = container.select(PersonRepository.class).select(DatabaseQualifier.ofDocument()).get();
	      repository.save(person);

	      List<Person> people = repository.findByName("Name");
	      System.out.println("Entity found: " + people);
	      repository.findByPhones("234").forEach(System.out::println);

	    }
	  }

	  private App2() {
	  }
	}
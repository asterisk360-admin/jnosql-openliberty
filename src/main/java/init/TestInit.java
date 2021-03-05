package init;


import static jakarta.nosql.document.DocumentQuery.select;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.inject.Inject;

import org.eclipse.jnosql.mapping.DatabaseQualifier;

import dao.repository.PersonRepository;
import dao.repository.PersonDAO;
import jakarta.nosql.document.DocumentQuery;
import jakarta.nosql.mapping.document.DocumentTemplate;
import model.Person;



@Startup
@Singleton
public class TestInit {
	
	
	@Inject
	private PersonDAO personDAO;
	
	
	@PostConstruct
	public void init() {
		Random random = new Random();
		Long id = random.nextLong();
		Person person = Person.builder().withPhones(Arrays.asList("234", "432")).withName("Name").withId(id).build();
		personDAO.save(person);
	}
	
	
	
	public void initLegacyOFF() {
		
		Random random = new Random();
		Long id = random.nextLong();

		try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

			Person person = Person.builder().withPhones(Arrays.asList("234", "432")).withName("Name").withId(id).build();

			PersonRepository repository = container.select(PersonRepository.class).select(DatabaseQualifier.ofDocument()).get();
			repository.save(person);

			List<Person> people = repository.findByName("Name");
			System.out.println("Entity found: " + people);
			repository.findByPhones("234").forEach(System.out::println);
			
			//Person person = Person.builder().withPhones(Arrays.asList("234", "432")).withName("Name").withId(id).build();

            DocumentTemplate documentTemplate = container.select(DocumentTemplate.class).get();
            Person saved = documentTemplate.insert(person);
            System.out.println("Person saved" + saved);

            DocumentQuery query = select().from("Person").where("_id").eq(id).build();
            Optional<Person> personOptional = documentTemplate.singleResult(query);
            System.out.println("Entity found: " + personOptional);
			
			
		}
	}
	
}
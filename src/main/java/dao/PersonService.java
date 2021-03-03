package dao;

import java.util.Optional;
import javax.inject.Inject;
import jakarta.nosql.document.DocumentQuery;
import jakarta.nosql.mapping.document.DocumentTemplate;
import model.Person;

import static jakarta.nosql.document.DocumentQuery.select;

public class PersonService {

	
	@Inject
	private DocumentTemplate template;

	public Person insert(Person person) {
		return template.insert(person);
	}

	public Optional<Person> find(Long id) {
		DocumentQuery query = select().from("Person").where("id").eq(id).build();
		return template.singleResult(query);
	}
	
	
}

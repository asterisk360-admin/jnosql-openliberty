package dao;

import java.util.Optional;
import javax.inject.Inject;
import jakarta.nosql.document.DocumentQuery;
import jakarta.nosql.mapping.Database;
import jakarta.nosql.mapping.document.DocumentTemplate;
import model.Person;


public class PersonServiceRepository {

	@Inject
	@Database(DatabaseType.DOCUMENT)
	private PersonRepository repository;

	public Person save(Person person) {
		return repository.save(person);
	}

	public Optional<Person> find(Long id) {
		return repository.findById(id);
	}

}
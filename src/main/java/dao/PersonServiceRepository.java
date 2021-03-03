package dao;

import jakarta.nosql.mapping.Database;
import jakarta.nosql.mapping.DatabaseType;
import model.Person;

import javax.inject.Inject;
import java.util.Optional;


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
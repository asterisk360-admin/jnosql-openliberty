package dao;


import jakarta.nosql.mapping.Database;
import jakarta.nosql.mapping.DatabaseType;
import model.Person;
import javax.inject.Inject;

import java.util.List;
import java.util.Random;


public class PersonDAO {

	
	@Inject
	@Database(DatabaseType.DOCUMENT)
	private PersonRepository repository;

	
	public Person save(Person person) {
		Random random = new Random();
		Long id = random.nextLong();
		person.setId(id);
		return repository.save(person);
	}

	
	public Person findById(Long id) {
		Person result = null;
		try {
			result = repository.findById(id).get();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public List<Person> findByName(String name) {
		List<Person> results = null;
		try {
			results = repository.findByName(name);
			
			repository.findByPhones("234");
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}
	
	
	public List<Person> findByPhone(String phone) {
		List<Person> results = null;
		try {
			results = repository.findByPhones(phone);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

}
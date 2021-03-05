package dao;


import java.util.List;
import jakarta.nosql.mapping.Repository;
import model.Person;


public interface PersonRepository extends Repository<Person, Long> {

	List<Person> findByName(String name);
	List<Person> findByPhones(String phone);
	
}
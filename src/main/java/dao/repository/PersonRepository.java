package dao.repository;


import java.util.List;
import java.util.stream.Stream;
import jakarta.nosql.mapping.Repository;
import model.Person;


public interface PersonRepository extends Repository<Person, Long> {

	List<Person> findByName(String name);
	Stream<Person> findByPhones(String phone);
	
}
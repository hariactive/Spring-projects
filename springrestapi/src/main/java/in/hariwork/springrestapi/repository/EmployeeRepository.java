package in.hariwork.springrestapi.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.hariwork.springrestapi.model.Employee;


@Repository
//public interface EmployeeRepository extends  PagingAndSortingRepository<Employee, Long> {

public interface EmployeeRepository extends JpaRepository<Employee, Long>,
PagingAndSortingRepository<Employee, Long>{
	
	List<Employee>findByName(String name);
//	Select *from tablname Where name ="tomhaegen" AND location = "USA"
//	Writing the JPA query method for above query
	List<Employee> findByNameAndLocation(String name, String location);

// select *from table where name like "%ram"
//	List<Employee> findByNameContaining (String keyword);
	List<Employee> findByNameContaining (String keyword,Sort sort);

//	JPQL Select Query in DATA JPA
	@Query("FROM Employee WHERE name = :name OR location =:location")
	List<Employee> getEmployeesByNameAndLocation(String name, String location);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Employee WHERE name = :name")
	Integer deleteEmployeeByName(String name);
	
	
	
}




package in.hariwork.springrestapi.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import in.hariwork.springrestapi.model.Employee;

public interface EmployeeService {

	List <Employee> getEmployees();
	
	List<Employee> getEmployees(int pageNumber,int pageSize);
	
	List<Employee> getEmployeesDirection(int pageNumber,int pageSize);
	
	Employee saveEmployee (Employee employee);
	
	Employee getSingleEmployee (Long id);
	
	void deleteEmployee(Long id);
	
	Employee updateEmployee (Employee employee);
	
	List<Employee> getEmployeesByName(String name);
	
	List<Employee> getNameAndLocation(String name, String location);
	
	List<Employee> getEmployeeByKeyword(String name);
	
	List<Employee> getEmployeesByNameAndLocation(String name, String location);
	
	Integer deleteEmployeeByName(String name);
	
	
	
	

	
	
	
}
 
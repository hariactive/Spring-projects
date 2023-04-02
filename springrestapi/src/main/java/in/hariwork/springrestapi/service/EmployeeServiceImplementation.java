package in.hariwork.springrestapi.service;
//import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import in.hariwork.springrestapi.model.Employee;
import in.hariwork.springrestapi.repository.EmployeeRepository;

@Service
public class EmployeeServiceImplementation implements EmployeeService {
	
	@Autowired
	private EmployeeRepository eRepository;
	
	
	
	
//	private static List<Employee> list = new ArrayList<>();
//	
//	static {
//		Employee e = new Employee();
//		e.setAge(27L);
//		e.setDepartment("Surya");
//		e.setEmail("yahoo@mail.com");
//		e.setLocation("pune");
//		e.setName("data");
//		list.add(e);
//		
//		e.setAge(28L);
//		e.setDepartment("Dhoni");
//		e.setEmail("BCCI@mail.com");
//		e.setLocation("Chennai");
//		e.setName("ustadd");
//		list.add(e);
//	}
	

	@Override
	public List<Employee> getEmployees() {
		return eRepository.findAll();		 
//		return list;
	}
	@Override
	public List<Employee> getEmployees(int pageNumber,int pageSize) {
		Pageable pages = PageRequest.of(pageNumber, pageSize);
		return eRepository.findAll(pages).getContent();	 
//		return list;
	}
	@Override
	public List<Employee> getEmployeesDirection(int pageNumber,int pageSize) {
		Pageable pages = PageRequest.of(pageNumber, pageSize,Direction.DESC,"id");
		return eRepository.findAll(pages).getContent();	 
//		return list;
	}
	
	
	@Override
	public Employee saveEmployee(Employee employee) {
		return eRepository.save(employee);
	}
	
	@Override
	public Employee getSingleEmployee(Long id) {
		Optional<Employee> employee = eRepository.findById(id);
		if(employee.isPresent()) {
			return employee.get();	
		}
		throw new RuntimeException("Employee is not found for the id" + id);
	}

	@Override
	public void deleteEmployee(Long id) {
		eRepository.deleteById(id);
		
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return eRepository.save(employee);
	}

	@Override
	public List<Employee> getEmployeesByName(String name) {
		return eRepository.findByName(name);
	}

	@Override
	public List<Employee> getNameAndLocation(String name, String location) {
		return eRepository.findByNameAndLocation(name, location);
	}

//	@Override
//	public List<Employee> getEmployeeByKeyword(String name) {
//		return eRepository.findByNameContaining(name);
//	}
	@Override
	public List<Employee> getEmployeeByKeyword(String name) {
		Sort sort = Sort.by(Sort.Direction.DESC,"id");
		return eRepository.findByNameContaining(name,sort);
	}
	@Override
	public List<Employee> getEmployeesByNameAndLocation(String name, String location) {
		return eRepository.getEmployeesByNameAndLocation(name, location);
	}
	@Override
	public Integer deleteEmployeeByName(String name) {
		return eRepository.deleteEmployeeByName(name);
	}
	
	
	


	
	

}





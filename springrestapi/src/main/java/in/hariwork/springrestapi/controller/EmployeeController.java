package in.hariwork.springrestapi.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import in.hariwork.springrestapi.model.Employee;
import in.hariwork.springrestapi.service.EmployeeService;
import jakarta.validation.Valid;

//@Controller
@RestController     //Combination of ResponseBody + Controller
public class EmployeeController {

	@Autowired
	private EmployeeService eService;
	
	@Value("${app.name: Employee Tracker}")
	private String appName;
//	
	@Value("${app.version: v0.1}") 
	private String appVersion;
//	
	@GetMapping("/version")
	public String getAppDetails() {
		return appName + " - "+ appVersion;
	} 
	
	//localhost:8080/employees
//	@ResponseBody --> we don't need that since we are using RestController
//	@RequestMapping(value="/employees", method = RequestMethod.GET)
//	@GetMapping("/employees")  
//	public String getEmployees() {
//		return "displaying the list of the Home";
//	} 
	
	@GetMapping("/employeessimple")  
	public ResponseEntity<List<Employee>> getEmployees() {
		return new ResponseEntity<List<Employee>>(eService.getEmployees(), HttpStatus.OK);
	} 
	@GetMapping("/employees")  
	public ResponseEntity<List<Employee>> getEmployees(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
		return new ResponseEntity<List<Employee>>(eService.getEmployees(pageNumber,pageSize), HttpStatus.OK);
	} 
	@GetMapping("/employeesSorted")  
	public ResponseEntity<List<Employee>> getEmployeesDirection(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
		return new ResponseEntity<List<Employee>>(eService.getEmployeesDirection(pageNumber,pageSize), HttpStatus.OK);
	} 
	
	
	
	
//	localhost:8080/employees/12
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployee (@PathVariable ("id") Long id) {
		return new ResponseEntity<Employee>(eService.getSingleEmployee(id),HttpStatus.OK);
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employee ) {
		employee.setId(id);
		return new ResponseEntity<Employee>(eService.updateEmployee(employee),HttpStatus.OK);
	}   
	
	@PostMapping("/employees")
	public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee ) {
		return new ResponseEntity<Employee>(eService.updateEmployee(employee),HttpStatus.CREATED);

		}
	
//	localhost:8080/employees?id=79  
//	@DeleteMapping("/employees") 
//	public ResponseEntity<HttpStatus> deleteEmployee(@RequestParam("id") Long id) {
//		return new ResponseEntity<HttpStatus>(eService.deleteEmployee(id),HttpStatus.NO_CONTENT);	 
//	}
	
	@DeleteMapping("/employees") 
	public void deleteEmployee(@RequestParam Long id) {
		  eService.deleteEmployee(id);
		}
	@GetMapping("employees/filterByName")
	public ResponseEntity <List<Employee>> getEmployeesByName (@RequestParam String name){
		return new ResponseEntity<List<Employee>>(eService.getEmployeesByName(name),HttpStatus.OK);
	}
	
	
	@GetMapping("employees/filterByNameAndLocation")
	public ResponseEntity <List<Employee>> getNameAndLocation (@RequestParam String name,@RequestParam String location){
		return new ResponseEntity<List<Employee>>(eService.getNameAndLocation(name,location),HttpStatus.OK);
	}
	
	@GetMapping("employees/findByNameContaining")
	public ResponseEntity <List<Employee>> getEmployeeByKeyword (@RequestParam String name){
		return new ResponseEntity<List<Employee>>(eService.getEmployeeByKeyword(name),HttpStatus.OK);
	}
	
	@GetMapping("employees/{name}/{location}")
	public ResponseEntity <List<Employee>> getEmployeesByNameAndLocation (@PathVariable String name,@PathVariable String location){
		return new ResponseEntity<List<Employee>>(eService.getEmployeesByNameAndLocation(name,location),HttpStatus.OK);
	}
	@DeleteMapping("employees/delete/{name}")
	public ResponseEntity <String> deleteEmployeeByName (@PathVariable String name){
		return new ResponseEntity<String>(eService.deleteEmployeeByName(name) + " No. of Records Affected",HttpStatus.OK);
	}
	
	
	
	
	  
}

  



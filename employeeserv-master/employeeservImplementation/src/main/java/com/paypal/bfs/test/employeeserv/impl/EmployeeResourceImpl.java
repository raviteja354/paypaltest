package com.paypal.bfs.test.employeeserv.impl;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.model.Address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {

	@Autowired
	EmployeeRepository employeeRepository;
   
	@Override
    public ResponseEntity<Employee> employeeGetById(String id) {

        Employee employee = new Employee();
        employee.setId(Integer.valueOf(id));
        employee.setFirstName("BFS");
        employee.setLastName("Developer");
		
		//employeeRepository.findById(Integer.parseInt(id));
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

	@Override
	public ResponseEntity<?> create(Employee employee) {

		boolean flag = true;
		String message = null;
		com.paypal.bfs.test.employeeserv.model.Employee  empSaved = null;
		com.paypal.bfs.test.employeeserv.model.Employee empTem = new com.paypal.bfs.test.employeeserv.model.Employee();
		
		//Converting JSON to Etity t
		empTem.setId(employee.getId());
		empTem.setDateOfBirth(employee.getDateOfBirth());
		empTem.setFirstName(employee.getFirstName());
		empTem.setLastName(employee.getLastName());
		
		com.paypal.bfs.test.employeeserv.model.Address address = new com.paypal.bfs.test.employeeserv.model.Address();
		address.setLine1(employee.getAddress().getLine1());
		address.setLine2(employee.getAddress().getLine2());
		address.setCity(employee.getAddress().getCity());
		address.setState(employee.getAddress().getState());
		address.setCountry(employee.getAddress().getCountry());
		address.setZipcode(employee.getAddress().getZipcode());
		
		empTem.setAddress(address);
		
		try {
			 empSaved = employeeRepository.save(empTem);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			flag = false;
			message ="{\"errorMessage\":\"Internal server error\"}";
			
		}
		if(flag) {
			employee.setId(empSaved.getId());
			return new ResponseEntity<>(employee, HttpStatus.CREATED);
		}
		  return ResponseEntity
	            .status(HttpStatus.INTERNAL_SERVER_ERROR)
	            .body("Internal Server Error. Missing mandatory field");
	}
}

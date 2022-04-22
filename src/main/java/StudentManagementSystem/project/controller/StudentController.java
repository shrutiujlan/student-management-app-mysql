package StudentManagementSystem.project.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import StudentManagementSystem.project.exception.ResourceNotFoundException;
import StudentManagementSystem.project.model.Student;
import StudentManagementSystem.project.repository.StudentRepository;
import StudentManagementSystem.project.service.StudentService;



@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class StudentController {

	@Autowired
	private StudentService studentService;
	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllStudent(){
		 return new ResponseEntity<>(studentService.getAll(), HttpStatus.OK);
}
	
	@GetMapping("/students/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable("id") long id) {
		Student student = studentService.getStudentById(id).orElseThrow(()-> new ResourceNotFoundException("Student not found with id:"+id));
		return ResponseEntity.ok(student);
	} 
	@GetMapping("/students/specific")
	public ResponseEntity<Student> getStudentWithId2() {
	if(studentService.doesStudentwithIdExist(2)) {
				Student student = studentService.findStudentWithId2();
						return ResponseEntity.ok(student);
				}
		
	throw new ResourceNotFoundException("Student not found with id:2");

		
	} 
	
	@PostMapping("/students")
	public ResponseEntity<Student> createStudent(@RequestBody Student Student) {
		try {
			Student _student = studentService.addStudent(new Student(Student.getfirstName(), Student.getlastName(), Student.getemail()));
			return new ResponseEntity<>(_student, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/students/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable("id") long id, @RequestBody Student studentDetails) {
		if(studentDetails == null) {
			throw new ResourceNotFoundException("Student details cant be null");
		}
		if(studentService.doesStudentwithIdExist(id)) {
		Student updatedStudent = studentService.updateStudent(studentDetails,id);
		return ResponseEntity.ok(updatedStudent);
		}
		
			throw new ResourceNotFoundException("Student not found with id:"+id);
		
	}
	
	@DeleteMapping("/students/{id}")
	public ResponseEntity<HttpStatus> deleteStudentById(@PathVariable("id") long id) {
		try {
			studentService.deleteStudentId(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	
}

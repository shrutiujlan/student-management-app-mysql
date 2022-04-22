package StudentManagementSystem.project.service;

import java.util.List;
import java.util.Optional;

import StudentManagementSystem.project.exception.ResourceNotFoundException;
import StudentManagementSystem.project.model.Student;

public interface StudentService{


	Student addStudent(Student student);

	void deleteStudentId(long studentId) throws ResourceNotFoundException;

	Optional<Student> getStudentById(long studentId) throws ResourceNotFoundException;

	List<Student> getAll();

	Student updateStudent(Student student, long id) throws ResourceNotFoundException;
	
	Student findStudentWithId2()  throws ResourceNotFoundException;
	
	boolean doesStudentwithIdExist(long id) ;
}

package StudentManagementSystem.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import StudentManagementSystem.project.exception.ResourceNotFoundException;
import StudentManagementSystem.project.model.Student;
import StudentManagementSystem.project.repository.StudentRepository;

@Service
public class StudentServiceImpl  implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student addStudent(Student student) {
	return studentRepository.save(student);
	}
	@Override
	public void deleteStudentId(long studentId) throws ResourceNotFoundException{
	studentRepository.deleteById(studentId);
	}
	@Override
	public Optional<Student> getStudentById(long studentId) throws ResourceNotFoundException{
	return studentRepository.findById(studentId);
	}
	@Override
	public List<Student> getAll(){
	return studentRepository.findAll();
	}
	@Override
	public Student updateStudent(Student student,long id) throws ResourceNotFoundException{
	Student updatedStudent = studentRepository.getById(id);
		updatedStudent.setfirstName(student.getfirstName());
		updatedStudent.setlastName(student.getlastName());
		updatedStudent.setemail(student.getemail());
		return studentRepository.save(updatedStudent);

	}
	@Override
	public Student findStudentWithId2() throws ResourceNotFoundException{
	return studentRepository.findStudentWithId2();

	}
	@Override
	public boolean doesStudentwithIdExist(long id){
	return studentRepository.existsById(id);

	}

}

package StudentManagementSystem.project.service;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.mockito.ArgumentMatchers.anyLong;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import StudentManagementSystem.project.exception.ResourceNotFoundException;
import StudentManagementSystem.project.model.Student;
import StudentManagementSystem.project.repository.StudentRepository;
import StudentManagementSystem.project.service.StudentService;
import StudentManagementSystem.project.service.StudentServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {

	//method allows us to create a mock object of a class or an interface.
	@Mock
	private StudentRepository studentRepository;
	Student student;
	
	//reates an instance of the class and injects the mocks that are created
	@InjectMocks
	private StudentServiceImpl studentServiceMock;


	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void getAllStudents_success() throws ResourceNotFoundException {
		Student student1 = new Student("Shruti", "Ujlan", "shrutiujlan@gmail.com");
		Student student2 = new Student("Kriti", "Ujlan", "kritiujlan@gmail.com");
		List<Student> students = new ArrayList<>(Arrays.asList(student1, student2));
		when(studentRepository.findAll()).thenReturn(students);
		assertEquals(studentServiceMock.getAll(),students);
	}
	@Test
	public void getStudentById_success() throws ResourceNotFoundException{
		Student student1 = new Student("Shruti", "Ujlan", "shrutiujlan@gmail.com");
		Optional<Student> Student1 = Optional.of(student1);
		when(studentRepository.findById(student1.getid())).thenReturn(Student1); 
		assertEquals(studentServiceMock.getStudentById(student1.getid()),Student1);
	}
	@Test
	public void whenGivenId_shouldDeleteUser_ifFound(){
		Student student1 = new Student("Shruti", "Ujlan", "shrutiujlan@gmail.com");
		student1.setid(3);
//	when(studentRepository.findById(student1.getid())).thenReturn(Optional.of(student1));
	studentServiceMock.deleteStudentId(student1.getid());
	verify(studentRepository).deleteById(student1.getid());
	}
	

//	@Test
//	public void should_throw_exception_when_user_doesnt_exist() throws Exception {
//		
//	try {	
//	Student student1 = new Student("Shruti", "Ujlan", "shrutiujlan@gmail.com");
//	student1.setid(3);
//	lenient().when(studentRepository.save(student1)).thenReturn(student1);
//	      when(studentRepository.findById((long) 3)).thenReturn(Optional.of(student1));
//	      studentRepository.deleteById(student1.getid());
//	      lenient().when(studentRepository.findById((long) 3)).thenReturn(Optional.of(student1));
//	        assertNotEquals(student1, new Student());
////	        assertEquals(studentServiceMock.deleteStudentId(3));
//	}
//	 catch(Exception e){
//	    throw new RuntimeException(e);
//	 } 
//	}
	@Test
    public void removeStudentByIdTest() throws ResourceNotFoundException {
		Student student1 = new Student("Shruti", "Ujlan", "shrutiujlan@gmail.com");
		student1.setid(1);
		lenient().when(studentRepository.existsById(student1.getid())).thenReturn(true);
        studentServiceMock.deleteStudentId(student1.getid());
        verify(studentRepository).deleteById((long) 1);
    }
	@Test
    public void removeStudentByIdTest_Exception() throws Exception {
    	try {	
    		Student student1 = new Student("Shruti", "Ujlan", "shrutiujlan@gmail.com");
    		student1.setid(1);
    		lenient().when(studentRepository.existsById(student1.getid())).thenReturn(true);
           studentServiceMock.deleteStudentId(0);
    		}
    		 catch(Exception e){
    		    throw new RuntimeException(e);
    		 }
    }
	@Test
	public void whenGivenId_shouldUpdateUser_ifFound() {
		Student student1 = new Student("Shruti", "Ujlan", "shrutiujlan@gmail.com");
		student1.setid(3);
		lenient().when(studentRepository.getById((long) 3)).thenReturn(student1) ;
		 when(studentRepository.save(student1)).thenReturn(student1);
		  assertThat(studentServiceMock.updateStudent(new Student("kate", "Ujlan", "shrutiujlan@gmail.com"),3)).isEqualTo(student1);

	}
	@Test
	public void whenGivenId2(){
		Student student1 = new Student("Shruti", "Ujlan", "shrutiujlan@gmail.com");
		student1.setid(2);
		lenient().when(studentRepository.getById((long) 2)).thenReturn(student1) ;
	studentServiceMock.findStudentWithId2();
	verify(studentRepository).findStudentWithId2();
	}
	

}
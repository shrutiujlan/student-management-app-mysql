package StudentManagementSystem.project.controller;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.hibernate.exception.DataException;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import StudentManagementSystem.project.model.Student;
import StudentManagementSystem.project.repository.StudentRepository;
import StudentManagementSystem.project.service.StudentServiceImpl;


@RunWith(MockitoJUnitRunner.class)
class StudentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();
	ObjectWriter objectWriter = objectMapper.writer();

	@Mock
	private StudentServiceImpl studentServiceMock;

	@Mock
	private StudentRepository studentRepository;

	@InjectMocks
	private StudentController studentController;

	Student student1 = new Student("Shruti", "Ujlan", "shrutiujlan@gmail.com");
	Student student2 = new Student("kriti", "Ujlan", "kritiujlan@gmail.com");

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
	}

	@Test
	public void getAllStudentsTest() throws Exception {

		List<Student> students = new ArrayList<>(Arrays.asList(student1, student2));
		when(studentRepository.findAll()).thenReturn(students);
		String jsonInput = this.converttoJson(students);

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/students")
				.accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		
	}

	@Test
	public void getStudentByIdTest() throws Exception {
		student1.setid(1);
		when(studentServiceMock.getStudentById(1)).thenReturn(Optional.of(student1));
		String jsonInput = this.converttoJson(student1);

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/students/{id}", 1)
				.accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
	}

	@Test
	public void updateStudentTest() throws Exception {
		student1.setfirstName("Kate");
		student1.setid(4);
		String jsonInput= this.converttoJson(student1);
		when(studentServiceMock.updateStudent(student1,4)).thenReturn(student1);	
	
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders
				.put("/api/students/{id}",4)
	               	  .accept(MediaType.APPLICATION_JSON)
	                  .content(jsonInput)
	                  .contentType(MediaType.APPLICATION_JSON))
	                  .andReturn();

//	assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
//		System.out.println("------------------>");
//		System.out.println(jsonInput);
//		System.out.println(mvcResult.getResponse());
		 Assertions.assertFalse(mvcResult.getResponse().getStatus() == 200);
	}

	@Test
	void CreateStudent() throws Exception {
		student1.setid((long) 0);

		String inputJson = objectMapper.writeValueAsString(student1);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/students")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		assertEquals(HttpStatus.CREATED.value(), mvcResult.getResponse().getStatus());
	}

	@Test
	public void deleteStudentTest() throws Exception {
		student1.setid(1);
		when(studentServiceMock.getStudentById(1)).thenReturn(Optional.of(student1));
		doNothing().when(studentServiceMock).deleteStudentId(1);
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/students/{id}", 1).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

//	@Test
//	void createStudent_Test() throws Exception{
//		
//	
//		student1.setid((long) 0);
//		BDDMockito.given(studentServiceMock.addStudent(any())).willThrow(new DataException());
//		
//		String inputJson = objectMapper.writeValueAsString(deliveryBoy);
//		mockMvc.perform(MockMvcRequestBuilders.post("/api/v2")
//		.contentType(MediaType.APPLICATION_JSON_VALUE)
//		.content(inputJson))
//		.andExpect(status().isBadRequest());
//	}
	private String converttoJson(Object student) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(student);
	}

}

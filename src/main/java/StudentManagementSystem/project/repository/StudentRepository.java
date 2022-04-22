package StudentManagementSystem.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import StudentManagementSystem.project.model.Student;



@Repository
public interface StudentRepository extends JpaRepository<Student,Long>
{
	 @Query("SELECT u FROM Student u WHERE u.id = 2")
	    Student findStudentWithId2();
}

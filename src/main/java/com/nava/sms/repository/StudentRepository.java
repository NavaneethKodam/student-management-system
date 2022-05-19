package com.nava.sms.repository;

import com.nava.sms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository : No need to write this annotation, bcoz there is a implementation class called simpleJpaRepository of this JpaRepository interface...it has annotated with @Repository , so no need to mention it again
public interface StudentRepository extends JpaRepository<Student,Long>
{
    // here this StudentRepository gets all the crud operations to interact with database

}

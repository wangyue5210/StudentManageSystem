package service;

import java.util.List;
import java.util.Map;

import domain.Student;



public interface StudentService {
	//返回学生列表和分页信息
	Map<String, Object> getAll(String pageNoStr);

	void save(Student student);

	Student edit(String id);

	void update(Student student);

	void delete(String id);

	

	

	

}

package dao;

import java.util.List;

import domain.Student;

public interface StudentDao {
	
	
	//通过分页条件，查询学生列表
	List<Student> getAll(int skipCount,int pageCount);
	
	//取得一共有多少记录
	public int  getTotal() ;

	void save(Student student);

	Student edit(String id);

	void update(Student student);

	void delete(String id);

}

package service.impl;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.StudentDao;
import dao.impl.StudentDaoImpl;
import domain.Student;
import service.StudentService;
import util.PageUtil;

public class StudentServiceImpl implements StudentService {
		private StudentDao studentDao=new StudentDaoImpl();

		
		

		@Override
		public void save(Student student) {
			studentDao.save(student);
			
		}

		@Override
		public Student edit(String id) {
			Student s=studentDao.edit(id);
			return s;
		}

		@Override
		public void update(Student student) {
			studentDao.update(student);
			
		}

		@Override
		public void delete(String id) {
			studentDao.delete(id);
			
		}

		@Override
		public Map<String, Object> getAll(String pageNoStr) {
			//取得当前页
			int pageNo=1;
			if (pageNoStr!=null && !"".equals(pageNoStr)) {
				 pageNo=Integer.valueOf(pageNoStr);
			}
			
			//设置每一页显示多少条记录
			int pageCount=5; 	
					
			//取得共多少条记录
			int total=studentDao.getTotal();
			
			
			//取一共有多少页
			int pageSize=total/pageCount;
			if (total%pageCount>0) {
				pageSize++;
			}
			
			PageUtil pu=new PageUtil();
			pu.setPageCount(pageCount);
			pu.setPageNo(pageNo);
			pu.setPageSize(pageSize);
			pu.setTotal(total);
			
			//以上返回分页信息
			
			
			int skipCount=(pageNo-1)*pageCount;
			List<Student> sList = studentDao.getAll(skipCount, pageCount);
			
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("pu", pu);
			map.put("sList",sList);
			
			return map;
		}
}

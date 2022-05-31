package com.alj.dream.category.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alj.dream.category.dao.CategoryDao;
import com.alj.dream.category.domain.Category;

@Service
public class DeleteCategoryService {
	
	private SqlSessionTemplate sst;
	
	public DeleteCategoryService() {

	}
	
	@Autowired
	public DeleteCategoryService(SqlSessionTemplate sst) {
		this.sst = sst;
	}
	
	@Transactional
	public int deleteCategory(String idx) {

		

		// 재귀호출로 삭제한 카테고리의 모든 자식노드를 삭제처리

//		dao=sst.getMapper(CategoryDao.class);
//		
//		List<String> list =new LinkedList<String>();
//		list.add(idx);
//		count+=dao.deleteCategory(idx);
//		
//		try {
//				List<ChildCategory> childList=dao.getChildCategory(list);
//				
//				if(childList!=null) {
//					
//					for(ChildCategory children : childList) {
//						deleteCategory(children.getCat_idx(), count);
//					}
//					
//				}
//				
//				
//				
//				System.out.println("c o u n t >>>"+String.valueOf(count));
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//			count=0;
//			
//		}

		// 재귀호출을 사용할 경우 하위노드가 많을 수록 퍼포먼스가 떨어지며 DB에 여러번 다녀오는 것은 바람직하지 못하므로
		// DB 내에서 재귀 호출을 사용하는 WITH - RECURSIVE 문을 사용하여 삭제하려는 카테고리 포함 모든 하위 노드를 한번에 가져와 삭제하도록 한다.

		int result=0;

		List<Category> allChildren= sst.getMapper(CategoryDao.class).getAllChildrens(idx);
//		for(Category obj : allChildren) {
//			System.out.printf("[%s]",obj.getCat_idx());
//		}
//		System.out.println("-----------");
		 result=sst.getMapper(CategoryDao.class).deleteCategory(allChildren);



		return result;
	}
	

}

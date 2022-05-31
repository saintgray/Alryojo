package com.alj.dream.notice.service;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alj.dream.file_notice.dao.NoticeFileDao;
import com.alj.dream.file_notice.domain.NoticeFileInfo;
import com.alj.dream.notice.dao.NoticeDao;
import com.alj.dream.notice.domain.NoticeRegisterData;
import com.alj.dream.util.file.DeleteFileUtil;
import com.alj.dream.util.file.UploadFileUtil;

import security.AccountDetails;

@Service
public class RegNoticeService {

	@Autowired
	private SqlSessionTemplate sst;
	
	// 관리자가 적은 데이터(공지사항)을 notice 테이블에 추가하는 서비스 클래스의 메소드
	@Transactional
	public int insertNotice(Authentication auth, NoticeRegisterData data, HttpServletRequest req) throws IllegalStateException, IOException {
		
		// 시큐리티의 UserDetails 객체를 상속받은 AccountDetails 객체안에 있는 관리자의 idx를 얻는법
		AccountDetails logininfo = (AccountDetails)auth.getPrincipal();
		String admin_idx= logininfo.getAdmin_idx();
		
		data.setAdmin_idx(admin_idx);
		
		int result=0;
		
		
		
		result=sst.getMapper(NoticeDao.class).insertNotice(data);
		
		
		// 파일을 서버에 저장
		
		String savePathS3 = "notice/attachfiles";
		
		List<NoticeFileInfo> list = new LinkedList<NoticeFileInfo>();
		
		if(data.getFiles()!=null || !data.getFiles().isEmpty()) {
			
		
			
			for(MultipartFile file: data.getFiles()) {
				
				String file_nm=UploadFileUtil.uploadFile(savePathS3, file.getOriginalFilename(), file.getBytes());
				
				
				list.add(new NoticeFileInfo(file_nm,
	 					String.valueOf(file.getSize()/(1024)).concat("KB"), 
	 					file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.')),
	 					file.getOriginalFilename().replaceAll(" ","_"),
	 					data.getNotice_idx()));
				
				
				
				
			}
			
			
		}
		
//		String savePath= req.getSession().getServletContext().getRealPath("/resources/files/notice/attachfiles");
//		
//		System.out.println(savePath);
//		List<NoticeFileInfo> list = new LinkedList<NoticeFileInfo>();
//		
//		for(MultipartFile file: data.getFiles()) {
//			
//			String file_nm=String.valueOf(System.nanoTime());
//			
//			//File dir=new File(savePath);
//			File newfile = new File(savePath, file_nm);
//			System.out.println(newfile.exists());
//			if(!newfile.exists()) {
//				System.out.println("....");
//				
//				newfile.mkdirs();
//			}
//			System.out.println("exist()...");
//			System.out.println(newfile.exists());
//			System.out.println(newfile.getAbsolutePath());
//			file.transferTo(newfile);
//			
//			
//			list.add(new NoticeFileInfo(file_nm,
//					 					String.valueOf(file.getSize()/(1024)).concat("KB"), 
//					 					file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.')),
//					 					file.getOriginalFilename().replaceAll(" ","_"),
//					 					data.getNotice_idx()));
//			
//			
//		}
		
		
		
	    
	    
	    try{
	    	
	    	if(list.size()>0) {
	    		result=sst.getMapper(NoticeFileDao.class).insertNoticeFiles(list);
	    	}
	    	
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	for(NoticeFileInfo info : list) {
//	    		File delfile=new File(savePath,info.getFile_nm());
//	    		if(delfile.exists()) {
//	    			delfile.delete();
//	    		}
	    		
	    		DeleteFileUtil.delete(savePathS3.concat(info.getFile_nm()));
	    	}
	    
	    }
		
	
		
		return result;
	}
}

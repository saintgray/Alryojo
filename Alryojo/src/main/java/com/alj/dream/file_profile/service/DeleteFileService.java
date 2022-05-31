package com.alj.dream.file_profile.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alj.dream.file_profile.dao.ProfileFilesDao;
import com.alj.dream.util.file.DeleteFileUtil;

@Service
public class DeleteFileService {
	private SqlSessionTemplate sst;
	
	
	public DeleteFileService() {}

	@Autowired
	public DeleteFileService(SqlSessionTemplate sst) {
		this.sst = sst;
	}
	
	public int deleteFile(HttpServletRequest req, String file_nm) {
		
//		String savedFolder=req.getSession().getServletContext().getRealPath("/resources/files/member/profile_attachfiles");
		
		
		
		// 1. DB에 저장된 이 파일에 대한 행의 삭제 유무 Update
		int resultDeleteProcessDB=sst.getMapper(ProfileFilesDao.class).deleteFile(file_nm);
		
	    // 파일 삭제 처리 
		// + Scheduler 를 통하여 DB에는 삭제일이 업데이트 되었으나 삭제되지 않은 파일에 대해 삭제 처리
		
		if(resultDeleteProcessDB==1) {
//			File file = new File(savedFolder, file_nm);
//			if(file.exists()) {
//				file.delete();
//			}
			DeleteFileUtil.delete("profile/attachfiles".concat(file_nm));
		}
		
		

		
		// 이제 해당 유저가 자신의 프로필을 클릭했을 때 삭제일이 NULL 인 데이터들만 가져오도록 처리해야 한다. (: file_profileMapper.xml 의 getFiles 쿼리)
		
		
		return resultDeleteProcessDB;
		
	}
	
	
}

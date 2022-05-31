package com.alj.dream.member.service;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alj.dream.interest.dao.InterestDao;
import com.alj.dream.member.dao.MemberDao;
import com.alj.dream.member.domain.Emailinfos;
import com.alj.dream.member.domain.RegisterInfo;
import com.alj.dream.member.exception.MemberExistException;
import com.alj.dream.util.file.UploadFileUtil;
import com.alj.dream.util.mail.EMailUtil;

@Service
public class RegisterService {
	
	private SqlSessionTemplate sqt;
	private BCryptPasswordEncoder pwEncoder;
	

	public RegisterService() {}
	
	@Autowired
	public RegisterService(SqlSessionTemplate sqt, BCryptPasswordEncoder pwEncoder) {
		this.sqt = sqt;
		this.pwEncoder = pwEncoder;
	}
	
	
	
	@Transactional
	public int insertMember(HttpServletRequest req,Emailinfos email,RegisterInfo infos) throws MemberExistException, IllegalStateException, IOException {
		
		

		int insertResult=0;
		
		
		
		
		if(sqt.getMapper(MemberDao.class).selectByEmail(infos.getM_email())==null) {
			
			
			
				
				
// Tomcat 서버에 클라이언트의 파일을 저장시 코드				
//				infos.setM_nm((infos.getM_nm()==null || infos.getM_nm().length()==0)? email.getM_email_prefix(): infos.getM_nm() );
//				infos.setM_email(EMailUtil.getUserEmail(email));
//				infos.setM_password(pwEncoder.encode(infos.getM_password()));
//				String m_photo=infos.getPhoto()==null?"defaultprofile.png":String.valueOf(System.nanoTime());
//				infos.setM_photo(m_photo);
//				
//				String savePath=req.getSession().getServletContext().getRealPath("/resources/files/member");
//				File file =new File(savePath, m_photo);
//				
//				if(infos.getPhoto()!=null) {
//					infos.getPhoto().transferTo(file);
//					System.out.println("auto increment >>>>" +String.valueOf(infos.getM_idx()));
//				}
					
				
// AWS S3 를 사용시 코드 /////////////////////////////////////////
				
				infos.setM_email(EMailUtil.getUserEmail(email));
				infos.setM_password(pwEncoder.encode(infos.getM_password()));		
				infos.setM_nm(infos.getM_nm().length()==0?email.getM_email_prefix():infos.getM_nm());
				infos.setLoc_idx(infos.getLoc_idx()==null?"0":infos.getLoc_idx());
				
				if(infos.getPhoto()==null) {
					infos.setM_photo("/defaultprofile.png");
				}
				
				// 파일을 S3에 올리는 핵심 코드 
				if(infos.getPhoto()!=null) {
					String originalName=infos.getPhoto().getOriginalFilename();
					
					// S3 버킷(가장 상위 폴더) 밑에 들어갈 폴더명을 생성한다.
					// 현재 버킷명은 aljdreambucket 이며 그 아래 폴더명을 member 로 설정했기 때문에
					// aljdreambucket/member 폴더는 회원들의 프로필 파일들을 저장해두는 폴더가 된다.
					
					// 예를 들어 게시글(요청글) 의 파일을 업로드 시에는 
					// String uploadPath="post"; 
					// 공지사항에 대한 파일을 업로드 시에는
					// String uploadPath="notice"; 와 같이
					// 해당 폴더가 어떤 기능에 대한 첨부파일인지 알아볼 수 있도록만 설정해주면 된다.
					String uploadPath="member";
				
					
					
					
					// UploadFileUtil 클래스의 uploadFile 메소드를 사용하여 S3에 파일을 업로드한다.
					
					// 이는 지금까지 로컬 서버 경로에 저장했었던 file.transferTo(newFile) 의 코드를 S3 클라우드 스토리지 저장소에 
					// 저장하는 코드로 바꾼것 뿐이다. 넣어주어야 할 파라미터만 신경써서 넣어주면 된다.
					
					// 1. MultiPartFile 을 byte 배열로 읽어들인다.
					byte[] byteData= infos.getPhoto().getBytes();
					
					// 이 메소드는 S3에 파일을 업로드하면서 DB에 저장할 파일경로를 반환해준다.
					// 파라미터로 uploadPath 와 파일의 원본이름, 그리고 파일 데이터를 넣어준다.
					// 반환하는 값은 /현재일자/랜덤으로 생성한 난수키_파일원본이름 이 된다.
					// 예를 들어 myphoto.jpg 라는 파일을 2022년 02월 28일날 업로드 했다면
					// filePath 의 값은 /2022/02/28/난수키_myphoto.jpg 가 된다.
					// 이 값을 DB의 file_nm에 해당하는 컬럼에 insert 해주면 된다.
					
					// 2. 각 값들을 uploadFile 의 파라미터로 넣어준다
					String filePath=UploadFileUtil.uploadFile(uploadPath, originalName, byteData);
					System.out.println("DB에 저장될 프로필 사진의 경로 입니다");
					System.out.println(filePath);
					
					
					infos.setM_photo(filePath);	
					
				}
/////////////////////////////////////////////////////////////////////////////////////////////////////////				
			
				insertResult=sqt.getMapper(MemberDao.class).insertMember(infos);
				
				if(insertResult==1) {
					
					if(infos.getInterest()!=null) {
						sqt.getMapper(InterestDao.class).registerUserInterest(infos.getInterest(), String.valueOf(infos.getM_idx()));
					}
					
				}
			
				
			
		}else {
			
			throw new MemberExistException();
		}
		
		return insertResult;
		
	}

	
	
}

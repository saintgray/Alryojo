package com.alj.dream.profile.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alj.dream.file_profile.dao.ProfileFilesDao;
import com.alj.dream.file_profile.domain.FileInfo;
import com.alj.dream.profile.dao.ProfileDao;
import com.alj.dream.profile.domain.MyProfileInfo;
import com.alj.dream.review.dao.ReviewDao;
import com.alj.dream.review.domain.StarsInfo;
import com.alj.dream.util.Formatter;

import security.AccountDetails;

@Service
public class ProfileService {
	
	
	private SqlSessionTemplate sst;
	
	public ProfileService() {}
	
	@Autowired
	public ProfileService(SqlSessionTemplate sst) {
		this.sst = sst;
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public MyProfileInfo getUserProfile(HttpServletRequest req,String m_idx, AccountDetails logininfo) throws Exception {
		
		
		//String avgStars = sst.getMapper(ReviewDao.class).getStars(m_idx);
		MyProfileInfo prof= null;
		prof=sst.getMapper(ProfileDao.class).getProfile(m_idx);
		
		// 해당 유저의 프로필이 등록되있는 상태일 때만 (prof 가 null 이 아닐 때만)
		// 별점과 첨부파일들을 가져온다
		
		// 이 멤버의 프로필이 존재한다면
		if(prof!=null) {
			
			// 1. 해당 review 테이블과 memer 테이블을 조인하여 해당 유저의 별점을 가져와서 MyProfileInfo 객체의 변수에 할당
			// 멘토의 기본 별점은 3.5점이라 하자.
			// 총 별점 평균은 (sum+3.5)/(count+1) 이 총 평균 별점이 된다.
			StarsInfo si=sst.getMapper(ReviewDao.class).getStars(m_idx);
			double avgStars=(si.getSum()+3.5f)/(si.getCount()+1);
			prof.setAvgStars(Formatter.form.format(avgStars));
			
			
			
			// 2. 해당 유저의 고유번호를 가진 profile idx를 외래키로 하는 file_profile 의 데이터를 모두 가져온다.
			prof.setFiles(sst.getMapper(ProfileFilesDao.class).getFiles(prof.getProfile_idx()));
			System.out.println(prof.getFiles());
			 
			
			
			
			
			// 3. 해당 유저의 리뷰 리스트를 가져온다 단 본인이 본인 프로필을 볼때는 가져오지 않는다.
			if(logininfo.getM_idx()!=m_idx) {
				prof.setReviews(sst.getMapper(ReviewDao.class).getReviewList(m_idx));
			}
			
			
			
			
			
			
			
		
		}
		

		
		return prof;
		
	}
	
	
	
}

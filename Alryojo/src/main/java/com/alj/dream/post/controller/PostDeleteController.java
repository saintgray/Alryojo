package com.alj.dream.post.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alj.dream.file_post.domain.PostFileRequest;
import com.alj.dream.file_post.service.PostFileDeleteService;
import com.alj.dream.post.service.PostDeleteService;

@Controller
@RequestMapping("/post/delete")
public class PostDeleteController {

	@Autowired
	private PostDeleteService service;
	
	@Autowired
	private PostFileDeleteService fileService;
	
	@PostMapping
	@ResponseBody
	public int deletePost(HttpServletRequest request, int post_idx) {
		
		// 게시글에 deldate 추가
		int resultCnt = service.deletePost(post_idx);
		
		// 게시물 첨부파일 존재유무 확인 후 있으면 file_deldate 추가하기
		int file_count = fileService.getCountFile(post_idx);
		
		if(file_count > 0) {
			
			// 게시물 첨부파일 삭제 메소드
			// 파일 저장 경로
			String saveDir = "post/attachfiles/";
			List<PostFileRequest> fileList = fileService.selectAllFilesByPostIdx(post_idx);
			
			// deldate 추가
			fileService.addDeldate(post_idx);
			
			// 실제 파일 삭제
			fileService.deleteFiles(saveDir, fileList);
			
		}
		
		return resultCnt;
		
	}
	
}

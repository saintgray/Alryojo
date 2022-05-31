package com.alj.dream.file_post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alj.dream.file_post.domain.PostFileRequest;

public interface PostFilesDao {

	int insertPostFile(PostFileRequest fileRequest);
	
	int selectCountByPostIdx(int post_idx);
	
	int deletePostFile(int post_idx);
	
	int deleteOneFile(@Param("post_idx")int post_idx, @Param("file_nm")String file_nm);
	
	List<PostFileRequest> selectByPostIdx(int post_idx);
	
	List<PostFileRequest> selectDeletedFiles();
	
	int checkExistence(String file_nm);

}

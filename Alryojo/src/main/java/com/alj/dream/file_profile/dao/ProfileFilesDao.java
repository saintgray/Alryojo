package com.alj.dream.file_profile.dao;

import java.util.List;

import com.alj.dream.file_profile.domain.FileInfo;

public interface ProfileFilesDao {
	int insertProfileFiles(List<FileInfo> files);
	List<FileInfo> getFiles(String profile_idx);
	int deleteFile(String file_nm);
	List<String> getDeletedFiles();

}

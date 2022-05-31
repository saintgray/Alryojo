package com.alj.dream.util.file;

import com.alj.dream.util.s3.S3Util;

public class DeleteFileUtil {
	
	
	private static String bucketName="aljdreambucket";
	
	
	
	public static void delete(String oldPath) {
		
		
		S3Util s3 = new S3Util();
		
		s3.delete(bucketName, oldPath);	
	}
}

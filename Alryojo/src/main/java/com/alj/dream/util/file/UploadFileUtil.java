package com.alj.dream.util.file;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import com.alj.dream.util.s3.S3Util;

public class UploadFileUtil {
	
	private static String bucketName="aljdreambucket";
	
	// 파일을 업로드 했다고 치자.
	// 이 메소드를 호출해서 String 을 반환하는데
	// 이 반환하는 String 을 데이터베이스의 file_profile 테이블에 경로 컬럼을 추가해서 넣는다?
	public static String uploadFile(String uploadPath, String originalName, byte[] byteData) {
		// 파일을 저장할 경로 uploadPath 와 원본 파일 이름, 파일 데이터를 매개변수로 받는다.
		System.out.println("UploadFileUtil 의  uploadFile 메소드 호출");
		S3Util s3= new S3Util();
		
		// 랜덤 uid를 생성
		UUID uid=UUID.randomUUID();
		// 해당 파일의 고유이름 생성
		String saveName="/"+uid.toString()+"_"+originalName;
		
		
		// getDatePath 메소드를 호출하면서 
		//
		String savePath=getDatePath(uploadPath);
		
		// 실제 DB 컬럼에 저장할 S3에 저장된 파일의 풀 경로
		String uploadedFileName=null;
		
		uploadedFileName = (savePath+saveName).replace(File.separatorChar,'/');
		
		s3.fileUpload(bucketName,  uploadPath+uploadedFileName, byteData);
		
		
		return uploadedFileName;
		
		
	}
	
	
	private static String getDatePath(String uploadPath) {
		Calendar cal= Calendar.getInstance();
		
		String year=File.separator+cal.get(Calendar.YEAR);
		
		String month= year + File.separator + new DecimalFormat("##").format(cal.get(Calendar.MONTH)+1);
		
		String fullDate= month + File.separator + new DecimalFormat("##").format(cal.get(Calendar.DATE));
		
		makeDir(uploadPath, fullDate);
		System.out.println("fullDate>>>");
		System.out.println(fullDate);
		return fullDate;
	}
	
	
	// 파일 디렉토리를 만드는 메소드
	// 해당 경로에 폴더가 있다면 만들고 없다면 void return
	private static void makeDir(String uploadPath, String... paths) {
		if(new File(paths[paths.length-1]).exists()) {
			return;
		}
		
		for(String path:paths) {
			File dirPath=new File(uploadPath+path);
			
			if(!dirPath.exists()) {
				dirPath.mkdir();
			}
		}
	}
	
	
	public static String uploadPostFile(String uploadPath, String file_originnm, String file_exet, byte[] fileData) {
		
		System.out.println("UploadFileUtil : uploadPostFile");
		S3Util s3 = new S3Util();
		
		String file_nm = file_originnm + "_" + String.valueOf(System.nanoTime());
		
		String uploadedPath = uploadPath.replace(File.separatorChar,'/');
		
		s3.fileUpload(bucketName, uploadedPath+"/"+file_nm+"."+file_exet, fileData);
		
		return file_nm;
	}

	public static void delete(String saveDir, String fileName) {
		S3Util s3 = new S3Util();
		
		String deletePath = saveDir.replace(File.separatorChar, '/') + fileName;
		
		// delete
		boolean result = s3.deletePostFile(bucketName, deletePath);
		
		if (!result) {
			System.out.println("파일이 삭제되지 않았습니다.");
		} else {
			System.out.println("파일 삭제 : " + fileName);
		}
		
	}
	
}

package com.alj.dream.util.s3;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.event.S3EventNotification.S3Entity;

// AWS S3
import com.amazonaws.services.s3.model.S3ObjectInputStream;

// Free Secure Cloud Storage 로서 데이터를 버킷 내의 객체로 저장하는 객체 스토리지 서비스이다.
// 객체는 해당 파일을 설명하는 모든 메타데이터이다.

// 버킷 : S3 에서 생성할 수 있는 최상위 디렉토리의 개념으로 이름은 S3 리전 중에서 유일해야 한다.
// 계정별로 100개까지(리전 1개당 1개 100개의 리전이 있다면 100개의 버킷 가능) 생성 가능하며 버킷에 저장할 수 있는 객체수와 용량은 무제한이다.


// 시간이 남는다면 적용해 볼 예정입니다. // S3 에 등록되는 것은 확인함.
public class S3Util {
	
	private String accessKey="AKIAWGMAZHX34YZXHKTC";
	private String secretKey="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";
	
	private AmazonS3 conn;
	
	public S3Util() {
		AWSCredentials credentials=new BasicAWSCredentials(accessKey, secretKey);
		ClientConfiguration clientConfig=new ClientConfiguration();
		clientConfig.setProtocol(Protocol.HTTP);
		
		this.conn= AmazonS3ClientBuilder.standard().
				   withCredentials(new AWSStaticCredentialsProvider(credentials)).
				   withClientConfiguration(clientConfig).
				   withRegion(Regions.AP_NORTHEAST_2).build();
		
	}
	
	// 버킷의 생성
	public Bucket createBucket(String bucketName) {
		return conn.createBucket(bucketName);
	}
	
	// 폴더 생성
	public void createFolder(String bucketName, String folderName) {
		conn.putObject(bucketName, folderName+"/", new ByteArrayInputStream(new byte[0]), new ObjectMetadata());
	}

	
	public void fileUpload(String bucketName, String fileName, byte[] fileData) {
		// File 클래스의 separatorChar : window의 경우 원화로, unix 의 경우 /로 표기하는 디렉토리 구분자를 나타낸다.
		// 원화의 경우 escape 문자이기 때문에 C:\\myFoldder\\... 처럼 2번을 붙여 원화를 표기해야 하지만
		// 경로가 다음과 같다고 하자.
		// "C:￦Users￦OJH￦Desktop￦유영진 강사님"
		// 이 시스템은 윈도우에서 작동하므로 File.separatorChar 의 값은 ￦ 이다.
		// 이 파일 경로에 원화를 /로 바꾸어 주려면 다음과 같이 쓰면 된다.
		String filePath = fileName.replace(File.separatorChar, '/');
		
		ObjectMetadata metaData=new ObjectMetadata();
		
		metaData.setContentLength(fileData.length);
		
		ByteArrayInputStream baStream = new ByteArrayInputStream(fileData);
		
		
		System.out.println("S3 에 파일을 올립니다.");
		conn.putObject(bucketName, filePath, baStream, metaData);
		System.out.println("파일을 올렸습니다.");
		
	}
	
	public void  delete(String bucketName, String oldPath) {
		
		if(conn.doesObjectExist(bucketName, oldPath)) {
			conn.deleteObject(bucketName, oldPath);
			
		}
		
	}

	
	public boolean deletePostFile(String bucketName, String filePath) {
		// delete
		conn.deleteObject(bucketName, filePath);

		// 삭제되었는지 확인
		boolean isDeleted = false;

		try {

			ObjectMetadata objectMetadata = conn.getObjectMetadata(bucketName, filePath);

		} catch (AmazonS3Exception s3e) {
	        if (s3e.getStatusCode() == 404) {
	            // i.e. 404: NoSuchKey - The specified key does not exist
	                isDeleted = true;

	        }	
		}

		System.out.println("util: deleteObject()");

		return isDeleted;
	}
	
}

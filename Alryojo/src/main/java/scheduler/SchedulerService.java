package scheduler;

import java.io.File;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alj.dream.file_post.dao.PostFilesDao;
import com.alj.dream.file_post.domain.PostFileRequest;
import com.alj.dream.file_profile.dao.ProfileFilesDao;
import com.alj.dream.member.dao.MemberDao;
import com.alj.dream.util.file.DeleteFileUtil;

@Component
@EnableScheduling
public class SchedulerService{
	
	
	
	private SqlSessionTemplate sst;
	private ServerPath serverPath;
	
	
	public SchedulerService() {

	}
	
	
	
	@Autowired
	public SchedulerService(SqlSessionTemplate sst, ServerPath serverPath) {
		this.sst = sst;
		this.serverPath = serverPath;
	}




	// 매일 새벽 2시에 실행되는 메소드
	@Scheduled(cron = "0 0 2 * * *")
	public void deleteInvalidFiles() {
		
		
		// DB에는 삭제일이 업데이트 되었으나 파일을 삭제하는 과정에서 오류가 발생하여 미처 삭제되지 않은 파일을 일괄 삭제한다.
		
		// 1. file_profile 테이블에서 file_deldate 가 null 이 아닌 파일(삭제된 파일 데이터)을 모두 가져온다.
		List<String> list=sst.getMapper(ProfileFilesDao.class).getDeletedFiles();
		System.out.println(list);
		
		// filenm=PK
		for(String filenm : list) {
			// File file=new File(serverPath.profileFilesPath,filenm);
			
			// System.out.println(file.exists());
			
			// 2. 컨텍스트경로/resources/files/member/profile_attachfiles 경로 내에 해당 파일이 있으면
			// 	  = 삭제된 파일인데 서버에 존재한다면 >> 삭제한다.
//			if(file.exists()) {
//				file.delete();
//			}
			///////////////////////////////
			
			DeleteFileUtil.delete("profile".concat(filenm));
		}		
	}
	
	
	// 2) tempcode 만료 설정
	// 매주 일요일 새벽 2시에 실행되는 메소드
	@Scheduled(cron = "0 0 2 */1 * *")
	public void expireTempCodes() {
		sst.getMapper(MemberDao.class).expireTempCode();
	}
	
	// 요청글 파일 삭제 메소드
	@Scheduled(cron = "0 0 2 * * *")	// 초 분 시 일 월 요일 , * 모든수
	public void checkFiles() {
		System.out.println("파일 확인 중...");
		System.out.println("----------------------------");
		
		// deldate가 어제 이전인 파일 목록 가져오기
		List<PostFileRequest> list = sst.getMapper(PostFilesDao.class).selectDeletedFiles();
		System.out.println(list);
		
		// db에는 deldate가 추가되었으나 서버에 아직 파일이 남아있는 경우 삭제
		for(PostFileRequest deletedFile : list) {
			
			// 종현 수정
			// File file = new File(serverPath.postFilesPath, deletedFile.getFileName());
			
			// System.out.println("존재여부: " + file.exists());
			
//			if(file.exists()) {
//				file.delete();
//				
//				System.out.println("삭제 후 존재여부: " + file.exists());
//			}
			
			DeleteFileUtil.delete("post/attachfiles/".concat(deletedFile.getFileName()));
		}
		
		// 글작성이 정상적으로 완료되지 않아 db에 존재하지 않으나 서버에 파일이 남아있는 경우
		System.out.println("파일 확인 중...");
		
		File dir = new File(serverPath.postFilesPath);
		String[] files = dir.list();
		for(String filenm : files) {
			
			String file_nm = filenm.substring(0, filenm.lastIndexOf("."));
			
			System.out.println(filenm + " : " + file_nm);
			
			if(sst.getMapper(PostFilesDao.class).checkExistence(file_nm)==0) {
				// db에 존재하지 않는 파일 -> 삭제
				File file = new File(serverPath.postFilesPath, filenm);
				file.delete();
				System.out.println("파일 삭제: " + filenm);
			}
			
		}
		
		System.out.println("----------------------------");
		System.out.println("파일 삭제 완료");
		
	}
	
}

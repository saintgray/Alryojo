package scheduler;

import org.springframework.stereotype.Component;

@Component
public class ServerPath {
	
	public String profileFilesPath;
	public String postFilesPath;
	public String noticeFilesPath;
	public String chatFilesPath;
	
	public ServerPath() {
	
	}
	public ServerPath(String profileFilesPath, String postFilesPath, String noticeFilesPath, String chatFilesPath) {
		this.profileFilesPath = profileFilesPath;
		this.postFilesPath = postFilesPath;
		this.noticeFilesPath = noticeFilesPath;
		this.chatFilesPath = chatFilesPath;
	}
}

package com.alj.dream.reply.dao;

import com.alj.dream.reply.domain.Reply;
import com.alj.dream.reply.domain.ReplyInfo;
import com.alj.dream.reply.domain.ReplyRegisterData;

public interface ReplyDao {

	int insertReply(ReplyRegisterData data);
	ReplyInfo getReplyByQnaIdx(String qna_idx);
	Reply getContent(String reply_idx);

}

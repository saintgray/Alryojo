package com.alj.dream.match.dao;


import java.util.List;

import com.alj.dream.match.domain.Match;
import com.alj.dream.match.domain.MatchInfo;
public interface MatchDao {

	
	public int insertMatch(int postidx, int menteeidx, int mentoridx);
	
	public Match selectMatch(int post_idx, int mentee_idx, int mentor_idx);
	
	public List<MatchInfo> selectMatchInfo(int post_idx);
	
	public int updateMatchOutyn(int myidx, int matchidx, int menteeidx, int mentoridx);

	public Match getMatchByMatchidx(int matchidx);

	public int getMatchDateDiff(int matchidx);

	public int updateMatchYN(String matchYN, int matchidx);
	
}

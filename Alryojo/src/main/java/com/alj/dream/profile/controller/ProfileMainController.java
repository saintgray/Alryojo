package com.alj.dream.profile.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alj.dream.profile.domain.MyProfileInfo;
import com.alj.dream.profile.service.ProfileService;

import security.AccountDetails;

@Controller
@RequestMapping("/member/profile")
public class ProfileMainController {
	
	private ProfileService profService;

	
	public ProfileMainController() {}

	@Autowired
	public ProfileMainController(ProfileService profService) {
		this.profService = profService;
	}






	@GetMapping("/main")
	public String showProfileMain(HttpServletRequest req, @Param("m_idx")String m_idx, Authentication auth, Model model) {
		
		AccountDetails logininfo= (AccountDetails)auth.getPrincipal();
		// 다른 사람이  상대방 프로필을 보러 왔을때 => getUserProfile() 의 매개변수에는 상대방의 m_idx가 들어가고
		// 본인이 내 프로필 관리를 선택해서 들어왔을 때 = 매개변수에 m_idx가 없을 때  >> getUserProfile() 의 매개변수에는 자기 자신의 idx 가 들어간다.
		m_idx=m_idx==null?logininfo.getM_idx():m_idx;
		
		String response="member/profile/profilemain";
		
		System.out.println();
		// 프로필을 등록하다가, 혹은 프로필을 보고있다가 멘티로 전환을 할때
		// 멘티로는 프로필을 등록할 수 없으므로 홈으로 보낸다.
		// 다만 자신이 멘티일때 다른사람의 프로필은 볼 수 있어야 하며, 자신의 프로필만 볼 수 없게 해야한다.
		// 다른사람의 프로필을 볼때는 요청 파라미터에 m_idx가 넘어서 들어오고, 자신의 프로필을 볼때는 m_idx 를 로그인한 사람의 idx로 할당했으므로
		// 조건절에 로그인한 사람이 멘티이고 요청한 프로필의 m_idx 가 자신과 같다면 (자신이 멘티상태에서 프로필을 보려고 한다면)
		// 홈으로 튕겨낸다
		// (/member/profile/main?m_idx=인덱스(다른 멘토의 프로필을 볼 때), /memeber/profile/main (내 프로필을 볼 때))
		if(logininfo.getM_type().equals("mentee") && m_idx==logininfo.getM_idx()) {
			response="redirect:/";
			System.out.println(response);
		}else {
			try {
				MyProfileInfo profile=profService.getUserProfile(req, m_idx, logininfo);
				
				if(profile!=null) {
		
					model.addAttribute("profile", profile);
				}
				
				
				//model.addAttribute("error", "일시적인 오류가 발생했습니다. 잠시 후 다시 시도해주세요");
			} catch (Exception e) {
				model.addAttribute("error", "일시적인 오류가 발생했습니다. 잠시 후 다시 시도해주세요");
				e.printStackTrace();
			}finally {
				model.addAttribute("profileOwnerIdx", m_idx);
			}
		}
		
		return response;
	}
	
	

}

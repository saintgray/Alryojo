package com.alj.dream.member.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alj.dream.member.domain.Emailinfos;
import com.alj.dream.member.domain.RegisterInfo;
import com.alj.dream.member.exception.MemberExistException;
import com.alj.dream.member.service.RegisterService;

@Controller
@RequestMapping("/register")
public class RegisterController {

	private RegisterService service;
	
	public RegisterController() {
	}

	@Autowired
	public RegisterController(RegisterService service) {

		this.service = service;
		
	}

	@GetMapping
	public String showRegisterForm(String adyn, Model model) {

		model.addAttribute("adyn", adyn == null ? 'N' : 'Y');

		return "member/registerform";
	}

	@PostMapping
	@ResponseBody
	public String registerMember(HttpServletRequest req, Emailinfos email, RegisterInfo infos, @CookieValue(value="authed", required=false) String authed, HttpServletResponse resp) {
		


		String result = null;
		
		System.out.println("authed>>>");
		System.out.println(authed);

		
		if (authed==null || authed.equals("N")) {
			result = "NOTAUTHED";
		} else {

			try {
				int insertResult = service.insertMember(req, email, infos);
				if (insertResult == 1) {
					
					result = "REGISTERED";
					Cookie expiredCookie=new Cookie("authed", "N");
					expiredCookie.setMaxAge(0);
					resp.addCookie(expiredCookie);
					
					
				} else {
					result = "정상적으로 등록되지 않았습니다. 잠시 후 다시 시도하세요";
				}

			} catch (MemberExistException e) {
				result = "이미 존재하는 이메일입니다";
				e.printStackTrace();
			} catch (Exception e) {
				// IllegalStateException, IOException 모두 하나의 오류타입으로 설정
				result = "오류가 발생했습니다. 잠시 후 다시 시도하세요";
				e.printStackTrace();
			}

		}

		return result;
	}

}

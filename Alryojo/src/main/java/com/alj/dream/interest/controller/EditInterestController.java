package com.alj.dream.interest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alj.dream.interest.service.EditInterestService;

import security.AccountDetails;

@RestController
@RequestMapping("/member/edit/interest")
public class EditInterestController {
	
	private EditInterestService eiService;
	
	public EditInterestController() {

	}

	@Autowired
	public EditInterestController(EditInterestService eiService) {
		this.eiService = eiService;
	}





	@PostMapping
	public int editInterest(@RequestBody List<String> interest, Authentication auth) {
//			for(Interest inter : interest) {
//				System.out.printf("cat_idx: %s , m_idx: %s",inter.getCat_idx(),inter.getM_idx());
//			}
		int result=0;
		
		try {
			result=eiService.editInterest(((AccountDetails)auth.getPrincipal()).getM_idx(),interest);
		}catch(Exception e) {
			e.printStackTrace();
		}
			
		
		return result;
	}
}

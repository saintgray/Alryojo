package com.alj.dream.admin.controller;

import java.util.Iterator;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import security.AccountDetails;

@Controller
@RequestMapping("/customerCenter")
public class CustomerCenterController {

	@GetMapping
	public String showadminpage(Authentication auth) {

		String viewPage =  "redirect:/notice?selectPage=1&numOfNoticesPerPage=5";
		
		
		if (auth != null) {
			

			AccountDetails logininfo = (AccountDetails) auth.getPrincipal();
			Iterator<GrantedAuthority> itr = logininfo.getAuthorities().iterator();

			while (itr.hasNext()) {
				if (itr.next().getAuthority().equals("ROLE_ADMIN")) {
					viewPage = "redirect:/admin/manage";
					break;
				}
			}

		}

		return viewPage;
	}

	
}
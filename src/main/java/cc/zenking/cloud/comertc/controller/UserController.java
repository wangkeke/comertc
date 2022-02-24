/**
 * Copyright 禅境科技股份有限公司
 * @author wk
 * @email kkwang@zenking.cc
 * @Date 2022-2-18
 *     All rights reserved.
 */
package cc.zenking.cloud.comertc.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cc.zenking.cloud.comertc.domain.User;
import cc.zenking.cloud.comertc.service.UserService;
import cc.zenking.cloud.comertc.vo.LoginCondition;

/**
 * @author wk
 * @email kkwang@zenking.cc
 * @Date 2022-2-18
 * @Desc 1619cc84100b1106c7ead472e79b7220
 */
@RequestMapping("user")
@Controller
public class UserController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping("prelogin")
	public String login(HttpSession session) {
		if(session.getAttribute("user")!=null) {
			return "redirect:/index";
		}
		return "login";
	}
	
	@RequestMapping("login")
	public String login(HttpSession session , @Valid LoginCondition condition) {
		User user = service.login(condition);
		session.setAttribute("user", user);
		return "redirect:/index";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/index";
	}
	
}

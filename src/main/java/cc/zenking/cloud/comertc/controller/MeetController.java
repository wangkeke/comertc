/**
 * Copyright 禅境科技股份有限公司
 * @author wk
 * @email kkwang@zenking.cc
 * @Date 2022-2-21
 *     All rights reserved.
 */
package cc.zenking.cloud.comertc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cc.zenking.cloud.comertc.domain.Meet;
import cc.zenking.cloud.comertc.domain.User;
import cc.zenking.cloud.comertc.service.MeetService;
import cc.zenking.cloud.comertc.vo.MeetAddCondition;
import cc.zenking.cloud.comertc.vo.MeetJoinCondition;
import cc.zenking.cloud.comertc.vo.MeetLeaveCondition;
import cc.zenking.cloud.comertc.vo.PublishVo;

/**
 * @author wk
 * @email kkwang@zenking.cc
 * @Date 2022-2-21
 * @Desc 1619cc84100b1106c7ead472e79b7220
 */
@Controller
@RequestMapping("meet")
public class MeetController {
	
	@Autowired
	private MeetService service;
	
	private boolean checkUserLogged(HttpSession httpSession) {
		return !(httpSession == null || httpSession.getAttribute("user") == null);
	}
	
	@RequestMapping("list")
	public String list(HttpSession httpSession , Model model) {
		if(!checkUserLogged(httpSession)) {
			return "redirect:/user/prelogin";
		}
		List<Meet> meets = service.list();
		model.addAttribute("meets", meets);
		return "/meet/list";
	}
	
	@RequestMapping("join")
	public String join(HttpSession httpSession , @Valid MeetJoinCondition condition , Model model) {
		if(!checkUserLogged(httpSession)) {
			return "redirect:/user/prelogin";
		}
		try {			
			PublishVo vo = service.join((User)httpSession.getAttribute("user"), condition);
			model.addAttribute("connectionId", vo.getConnectionId());
			model.addAttribute("token", vo.getToken());
			model.addAttribute("meet", vo.getExtraData());
			return "/meet/live";
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			return "error";
		}
	}
	
	@RequestMapping("addView")
	public String addView(HttpSession httpSession) {
		if(!checkUserLogged(httpSession)) {
			return "redirect:/user/prelogin";
		}
		return "/meet/add";
	}
	
	@RequestMapping("add")
	public String add(HttpSession httpSession , @Valid MeetAddCondition condition) {
		if(!checkUserLogged(httpSession)) {
			return "redirect:/user/prelogin";
		}
		User user = (User)httpSession.getAttribute("user");
		service.save(user, condition);
		return "redirect:/meet/list";
	}
	
	@RequestMapping("leave")
	public String leave(HttpSession httpSession , @Valid MeetLeaveCondition condition , Model model) {
		service.leave((User)httpSession.getAttribute("user"), condition);
		return "redirect:/meet/list";
	}
	
}

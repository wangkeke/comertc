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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cc.zenking.cloud.comertc.domain.Room;
import cc.zenking.cloud.comertc.domain.User;
import cc.zenking.cloud.comertc.service.RoomService;
import cc.zenking.cloud.comertc.vo.PublishVo;
import cc.zenking.cloud.comertc.vo.RoomEndCondition;
import cc.zenking.cloud.comertc.vo.RoomStartCondition;
import cc.zenking.cloud.comertc.vo.RoomWatchCondition;
import cc.zenking.cloud.comertc.vo.SubscribeVo;

/**
 * @author wk
 * @email kkwang@zenking.cc
 * @Date 2022-2-18
 * @Desc 1619cc84100b1106c7ead472e79b7220
 */
@RequestMapping("room")
@Controller
public class RoomController {
	
	@Autowired
	private RoomService service;

	
	private boolean checkUserLogged(HttpSession httpSession) {
		return !(httpSession == null || httpSession.getAttribute("user") == null);
	}
	
	/**
	 * 开始直播
	 * @param condition 发布参数
	 * @return 发布结果
	 */
	@RequestMapping("start")
	public String start(HttpSession httpSession, @Valid RoomStartCondition condition , Model model) {
		if(!checkUserLogged(httpSession)) {
			return "redirect:/user/prelogin";
		}
		Room room = service.get(condition.getRoomCode());
		if(room==null) {
			model.addAttribute("message", "您还未创建直播间！");
			return "error";
		}
		PublishVo vo = service.startRoom((User)httpSession.getAttribute("user"), condition);
		model.addAttribute("connectionId", vo.getConnectionId());
		model.addAttribute("token", vo.getToken());
		model.addAttribute("room", room);
		return "room/publish";
	}
	
	@RequestMapping("watch")
	public String watch(HttpSession httpSession , @Valid RoomWatchCondition condition , Model model) {
		Room room = service.get(condition.getRoomCode());
		if(room==null) {
			model.addAttribute("message", "直播间不存在！");
			return "error";
		}
		SubscribeVo vo = service.watchRoom((User)httpSession.getAttribute("user"), condition);
		model.addAttribute("connectionId", vo.getConnectionId());
		model.addAttribute("token", vo.getToken());
		model.addAttribute("room", room);
		return "room/watch";
	}
	
	@RequestMapping("end")
	public String endRoom(HttpSession httpSession , @Valid RoomEndCondition condition , Model model) {
		if(!checkUserLogged(httpSession)) {
			return "redirect:/user/prelogin";
		}
		service.endRoom(condition);
		return "redirect:/index";
	}
	
}

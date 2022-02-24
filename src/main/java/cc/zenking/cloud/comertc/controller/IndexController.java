/**
 * Copyright 禅境科技股份有限公司
 * @author wk
 * @email kkwang@zenking.cc
 * @Date 2022-2-21
 *     All rights reserved.
 */
package cc.zenking.cloud.comertc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cc.zenking.cloud.comertc.domain.Room;
import cc.zenking.cloud.comertc.service.RoomService;

/**
 * @author wk
 * @email kkwang@zenking.cc
 * @Date 2022-2-21
 * @Desc 1619cc84100b1106c7ead472e79b7220
 */
@Controller
@RequestMapping
public class IndexController {
	
	@Autowired
	private RoomService roomService;
	
	@RequestMapping(value = {"/","index"})
	public String index(Model model) {
		List<Room> rooms = roomService.list();
		model.addAttribute("rooms", rooms);
		return "index";
	}
	
}

/**
 * Copyright 禅境科技股份有限公司
 * @author wk
 * @email kkwang@zenking.cc
 * @Date 2022-2-18
 *     All rights reserved.
 */
package cc.zenking.cloud.comertc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.zenking.cloud.comertc.domain.Room;
import cc.zenking.cloud.comertc.domain.User;
import cc.zenking.cloud.comertc.mapper.RoomMapper;
import cc.zenking.cloud.comertc.mapper.UserMapper;
import cc.zenking.cloud.comertc.vo.LoginCondition;

/**
 * 用户业务处理
 * @author wk
 * @email kkwang@zenking.cc
 * @Date 2022-2-18
 * @Desc 1619cc84100b1106c7ead472e79b7220
 */
@Service
@Transactional(readOnly = true)
public class UserService {
	
	@Autowired
	private UserMapper mapper;
	
	@Autowired
	private RoomMapper roomMapper;
	
	/**
	 * 用户登录
	 * @param condition
	 * @return
	 */
	public User login(LoginCondition condition) {
		User user = mapper.getUserByUsername(condition.getUsername());
		if(user==null) {
			throw new RuntimeException("用户不存在");
		}
		if(!user.getEnable()) {
			throw new RuntimeException("用户已禁用");
		}
		Room room = roomMapper.getRoom(user.getId(),null);
		user.setRoom(room);
		return user;
	}
	
}

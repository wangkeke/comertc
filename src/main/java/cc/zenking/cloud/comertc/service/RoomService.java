/**
 * Copyright 禅境科技股份有限公司
 * @author wk
 * @email kkwang@zenking.cc
 * @Date 2022-2-18
 *     All rights reserved.
 */
package cc.zenking.cloud.comertc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.zenking.cloud.comertc.domain.Room;
import cc.zenking.cloud.comertc.domain.User;
import cc.zenking.cloud.comertc.mapper.RoomMapper;
import cc.zenking.cloud.comertc.vo.PublishCondition;
import cc.zenking.cloud.comertc.vo.PublishVo;
import cc.zenking.cloud.comertc.vo.RoomEndCondition;
import cc.zenking.cloud.comertc.vo.RoomStartCondition;
import cc.zenking.cloud.comertc.vo.RoomWatchCondition;
import cc.zenking.cloud.comertc.vo.SubscribeCondition;
import cc.zenking.cloud.comertc.vo.SubscribeVo;
import io.openvidu.java.client.OpenViduRole;

/**
 * 直播间
 * @author wk
 * @email kkwang@zenking.cc
 * @Date 2022-2-18
 * @Desc 1619cc84100b1106c7ead472e79b7220
 */
@Service
@Transactional(readOnly = true)
public class RoomService {
	
	@Autowired
	private OpenViduService openViduService;
	
	@Autowired
	private RoomMapper mapper;
	
	
	public List<Room> list(){
		return mapper.selectRooms();
	}
	
	/**
	 * 获取直播间信息
	 * @param roomCode
	 * @return
	 */
	public Room get(String roomCode) {
		return mapper.getRoom(null, roomCode);
	}
	
	/**
	 * 开始直播
	 * @param condition 发布参数
	 * @return 发布结果
	 */
	public PublishVo startRoom(User user, RoomStartCondition condition) {
		PublishCondition publishCondition = new PublishCondition();
		publishCondition.setRole(OpenViduRole.MODERATOR);
		publishCondition.setRtspUrl(condition.getRtspUrl());
		publishCondition.setSessionId(condition.getRoomCode());
		publishCondition.setUser(user);
		return openViduService.publish(publishCondition);
	}
	
	/**
	 * 观看直播
	 * @param condition 订阅参数
	 * @return 订阅结果
	 */
	public SubscribeVo watchRoom(User user , RoomWatchCondition condition) {
		SubscribeCondition subscribeCondition = new SubscribeCondition();
		subscribeCondition.setSessionId(condition.getRoomCode());
		subscribeCondition.setUser(user);
		return openViduService.subscribe(subscribeCondition);
	}
	
	/**
	 * 结束直播
	 */
	public void endRoom(RoomEndCondition condition) {
		openViduService.closeSession(condition.getRoomCode());
	}
	
}

/**
 * Copyright 禅境科技股份有限公司
 * @author wk
 * @email kkwang@zenking.cc
 * @Date 2022-2-18
 *     All rights reserved.
 */
package cc.zenking.cloud.comertc.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.zenking.cloud.comertc.domain.Meet;
import cc.zenking.cloud.comertc.domain.User;
import cc.zenking.cloud.comertc.mapper.MeetMapper;
import cc.zenking.cloud.comertc.util.StreamUtil;
import cc.zenking.cloud.comertc.vo.MeetAddCondition;
import cc.zenking.cloud.comertc.vo.MeetJoinCondition;
import cc.zenking.cloud.comertc.vo.MeetLeaveCondition;
import cc.zenking.cloud.comertc.vo.PublishCondition;
import cc.zenking.cloud.comertc.vo.PublishVo;
import cc.zenking.cloud.comertc.vo.UnpublishCondition;

/**
 * 视频会议
 * @author wk
 * @email kkwang@zenking.cc
 * @Date 2022-2-18
 * @Desc 1619cc84100b1106c7ead472e79b7220
 */
@Service
@Transactional(readOnly = true)
public class MeetService {
	
	@Autowired
	private OpenViduService openViduService;
	
	@Autowired
	private MeetMapper mapper;
	
	public List<Meet> list(){
		return mapper.selectMeets(null);
	}
	
	/**
	 * 创建会议
	 * @param user
	 * @param condition
	 * @return
	 */
	@Transactional
	public Integer save(User user , MeetAddCondition condition) {
		Meet meet = new Meet();
		meet.setBeginTime(condition.getBeginTime());
		meet.setCreateTime(new Date());
		meet.setCreatorUid(user.getId());
		meet.setFlag(0);
		meet.setMeetCode(StreamUtil.getStreamCode());
		meet.setMeetName(condition.getMeetName());
		meet.setMeetPwd(condition.getMeetPwd());
		meet.setStatus(0);
		mapper.insertMeet(meet);
		return meet.getId();
	}
	
	/**
	 * 加入会议
	 * @param condition
	 * @return
	 */
	public PublishVo join(User user , MeetJoinCondition condition) {
		Meet meet = mapper.getMeet(condition.getMeetCode());
		if(meet==null) {
			throw new RuntimeException("不存在的会议！");
		}
		PublishCondition publishCondition = new PublishCondition();
		publishCondition.setUser(user);
		publishCondition.setSessionId(condition.getMeetCode());
		PublishVo vo = openViduService.publish(publishCondition);
		vo.setExtraData(meet);
		return vo;
	}
	
	/**
	 * 离开会议
	 * @param condition
	 */
	public void leave(User user , MeetLeaveCondition condition) {
		UnpublishCondition unpublishCondition = new UnpublishCondition();
		unpublishCondition.setConnectionId(condition.getConnectionId());
		unpublishCondition.setSessionId(condition.getMeetCode());
		unpublishCondition.setUser(user);
		openViduService.unpublish(unpublishCondition);
	}
	
}

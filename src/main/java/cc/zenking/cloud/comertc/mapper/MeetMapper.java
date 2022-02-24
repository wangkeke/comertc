/**
 * Copyright 禅境科技股份有限公司
 * @author wk
 * @email kkwang@zenking.cc
 * @Date 2022-2-18
 *     All rights reserved.
 */
package cc.zenking.cloud.comertc.mapper;

import java.util.List;

import cc.zenking.cloud.comertc.domain.Meet;

/**
 * 会议数据映射操作
 * @author wk
 * @email kkwang@zenking.cc
 * @Date 2022-2-18
 * @Desc 1619cc84100b1106c7ead472e79b7220
 */
public interface MeetMapper {
	
	List<Meet> selectMeets(Integer userId);
	
	int updateMeetStatusByCode(String meetCode , Integer status);
	
	Meet getMeet(String meetCode);
	
	void insertMeet(Meet meet);
	
}

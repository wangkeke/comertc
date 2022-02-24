/**
 * Copyright 禅境科技股份有限公司
 * @author wk
 * @email kkwang@zenking.cc
 * @Date 2022-2-21
 *     All rights reserved.
 */
package cc.zenking.cloud.comertc.vo;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * 离开会议
 * @author wk
 * @email kkwang@zenking.cc
 * @Date 2022-2-21
 * @Desc 1619cc84100b1106c7ead472e79b7220
 */
@Data
public class MeetLeaveCondition {
	
	/**
	 * 会议编码
	 */
	@NotNull
	private String meetCode;
	
	/**
	 * 会议视频链接ID
	 */
	@NotNull
	private String connectionId;
	
}

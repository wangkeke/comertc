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
 * 直播间发布视频
 * @author wk
 * @email kkwang@zenking.cc
 * @Date 2022-2-21
 * @Desc 1619cc84100b1106c7ead472e79b7220
 */
@Data
public class RoomStartCondition {
	
	/**
	 * 播间编号
	 */
	@NotNull
	private String roomCode;
	
	/**
	 * rtsp推流地址
	 */
	private String rtspUrl;
	
}

/**
 * Copyright 禅境科技股份有限公司
 * @author wk
 * @email kkwang@zenking.cc
 * @Date 2022-2-21
 *     All rights reserved.
 */
package cc.zenking.cloud.comertc.vo;

import javax.validation.constraints.NotNull;

import cc.zenking.cloud.comertc.domain.User;
import io.openvidu.java.client.ConnectionType;
import io.openvidu.java.client.OpenViduRole;
import lombok.Data;

/**
 * 发布视频流参数
 * @author wk
 * @email kkwang@zenking.cc
 * @Date 2022-2-21
 * @Desc 1619cc84100b1106c7ead472e79b7220
 */
@Data
public class PublishCondition {
	
	/**
	 * 用户信息
	 */
	@NotNull
	private User user;
	
	/**
	 * 会话ID
	 */
	@NotNull
	private String sessionId;
	
	/**
	 * 是否录制
	 */
	private Boolean isRecord = Boolean.FALSE;
	
	/**
	 * 视频角色
	 */
	private OpenViduRole role = OpenViduRole.PUBLISHER;
	
	/**
	 * rtsp推流地址，{@link ConnectionType type}必须为ConnectionType.IPCAM
	 */
	private String rtspUrl;
	
}

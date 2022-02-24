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
import lombok.Data;

/**
 * 取消发布参数
 * @author wk
 * @email kkwang@zenking.cc
 * @Date 2022-2-21
 * @Desc 1619cc84100b1106c7ead472e79b7220
 */
@Data
public class UnpublishCondition {
	
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
	 * 链接ID
	 */
	@NotNull
	private String connectionId;
	
}

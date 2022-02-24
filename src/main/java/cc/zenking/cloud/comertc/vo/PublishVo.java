/**
 * Copyright 禅境科技股份有限公司
 * @author wk
 * @email kkwang@zenking.cc
 * @Date 2022-2-21
 *     All rights reserved.
 */
package cc.zenking.cloud.comertc.vo;

import lombok.Data;

/**
 * 发布视频结果
 * @author wk
 * @email kkwang@zenking.cc
 * @Date 2022-2-21
 * @Desc 1619cc84100b1106c7ead472e79b7220
 */
@Data
public class PublishVo {
	
	/**
	 * 链接ID
	 */
	private String connectionId;
	
	/**
	 * 视频流链接token
	 */
	private String token;
	
	/**
	 * 额外数据信息
	 */
	private Object extraData;
	
}

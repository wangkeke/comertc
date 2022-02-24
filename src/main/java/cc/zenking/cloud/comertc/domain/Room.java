/**
 * Copyright 禅境科技股份有限公司
 * @author wk
 * @email kkwang@zenking.cc
 * @Date 2022-2-17
 *     All rights reserved.
 */
package cc.zenking.cloud.comertc.domain;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * 多租户下用户创建的播间
 * @author wk
 * @email kkwang@zenking.cc
 * @Date 2022-2-17
 * @Desc 1619cc84100b1106c7ead472e79b7220
 */
@Data
public class Room {
	
	@NotNull
	private Integer id;
	
	/**
	 * 用户ID
	 */
	@NotNull
	private Integer userId;
	
	/**
	 * 直播间名称
	 */
	@NotNull
	private String roomName;
	
	/**
	 * 播间编码，播间唯一标识码
	 */
	@NotNull
	private String roomCode;
	
	/**
	 * 删除标识，-1表示删除
	 */
	private int flag;
	
	/**
	 * 最后直播时间
	 */
	private Date lastLiveTime;
	
	private Date updateTime;
	
	private Date createTime;
	
}

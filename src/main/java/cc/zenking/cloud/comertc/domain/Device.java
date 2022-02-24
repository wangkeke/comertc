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
 * 设备信息
 * @author wk
 * @email kkwang@zenking.cc
 * @Date 2022-2-17
 * @Desc 1619cc84100b1106c7ead472e79b7220
 */
@Data
public class Device {
	
	@NotNull
	private Integer id;
	
	/**
	 * 设备名称
	 */
	private String deviceName;
	
	/**
	 * 设备编号
	 */
	private String deviceCode;
	
	/**
	 * rtsp协议推流地址
	 */
	private String rtspUrl;
	
	/**
	 * 是否启用
	 */
	private Boolean enable;
	
	/**
	 * 删除标识，-1表示删除
	 */
	private int flag;
	
	private Date updateTime;
	
	private Date createTime;
	
}

package cc.zenking.cloud.comertc.domain;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * 多租户下的创建的视频会议信息
 * @author k
 *
 */
@Data
public class Meet {
	
	private Integer id;
	
	/**
	 * 会议名称
	 */
	@NotNull
	private String meetName;
	
	/**
	 * 开始时间
	 */
	@NotNull
	private Date beginTime;
	
	/**
	 * 结束时间
	 */
	private Date endTime;
	
	/**
	 * 会议编码，唯一
	 */
	@NotNull
	private String meetCode;
	
	/**
	 * 入会密码口令
	 */
	@JsonIgnore
	private String meetPwd;
	
	/**
	 * 创建者用户ID
	 */
	@NotNull
	private Integer creatorUid;
	
	/**
	 * 状态，0未开始，1进行中，2已结束
	 */
	private Integer status;
	
	/**
	 * 删除标识，-1删除
	 */
	private Integer flag;
	
	/**
	 * 更新时间
	 */
	private Date updateTime;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
}

package cc.zenking.cloud.comertc.domain;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * 多租户下的用户信息
 * @author k
 *
 */
@Data
public class User {
	
	@NotNull
	private Integer id;
	
	/**
	 * 用户名
	 */
	@NotNull
	private String username;
	
	/**
	 * 用户昵称
	 */
	@NotNull
	private String nickname;
	
	/**
	 * 用户头像
	 */
	private String portrait;
	
	/**
	 * 用户是否启用，默认启用
	 */
	private Boolean enable;
	
	/**
	 * 删除标识，-1删除
	 */
	@JsonIgnore
	private Integer flag;
	
	/**
	 * 更新时间
	 */
	@JsonIgnore
	private Date updateTime;
	
	/**
	 * 创建时间
	 */
	@JsonIgnore
	private Date createTime;
	
	/**
	 * 用户的直播间
	 */
	private transient Room room;
	
}

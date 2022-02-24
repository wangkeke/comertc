/**
 * Copyright 禅境科技股份有限公司
 * @author wk
 * @email kkwang@zenking.cc
 * @Date 2022-2-23
 *     All rights reserved.
 */
package cc.zenking.cloud.comertc.vo;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * @author wk
 * @email kkwang@zenking.cc
 * @Date 2022-2-23
 * @Desc 1619cc84100b1106c7ead472e79b7220
 */
@Data
public class MeetAddCondition {
	
	@NotNull
	private String meetName;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date beginTime;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date endTime;
	
	@Size(min = 6)
	private String meetPwd;
	
}

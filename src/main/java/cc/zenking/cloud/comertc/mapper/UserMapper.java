/**
 * Copyright 禅境科技股份有限公司
 * @author wk
 * @email kkwang@zenking.cc
 * @Date 2022-2-18
 *     All rights reserved.
 */
package cc.zenking.cloud.comertc.mapper;

import cc.zenking.cloud.comertc.domain.User;

/**
 * 用户数据映射
 * @author wk
 * @email kkwang@zenking.cc
 * @Date 2022-2-18
 * @Desc 1619cc84100b1106c7ead472e79b7220
 */
public interface UserMapper {
	
	User getUserById(Integer id);
	
	User getUserByUsername(String username);
	
}

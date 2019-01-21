package com.huahua.crm.mapper;


import org.apache.ibatis.annotations.Param;

import com.huahua.crm.pojo.RolePermission;
import com.huahua.crm.pojo.RolePermissionExample;

/**
 * RolePermissionMapper继承基类
 * @author MybatisGenerator
 */
public interface RolePermissionMapper extends MyBatisBaseDao<RolePermission, Integer, RolePermissionExample> {

	/**
	 * 分配权限，批量插入
	 * @param permissionIds
	 * @param roleId
	 * @return
	 * @author huangqingwen
	 */
	public int insertRolePermission(@Param("permissionIds") Integer[] permissionIds, @Param("roleId") Integer roleId);
}
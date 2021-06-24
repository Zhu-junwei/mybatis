package com.zjw.dao;

import com.zjw.domain.Role;

import java.util.List;

/**
 * 角色的持久层接口
 */
public interface IRoleDao {

    /**
     * 查询所有角色
     * @return
     */
    List<Role> findAll();
}

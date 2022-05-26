package com.zjw.domain;

import lombok.Data;

import java.util.List;

/**
 * @author 朱俊伟
 */
@Data
public class QueryVo {

    private User user;

    private List<Integer> ids;
}

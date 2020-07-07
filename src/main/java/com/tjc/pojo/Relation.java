package com.tjc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: TJC
 * @Date: 2020/6/21 12:28
 * @description: TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Relation {

    private Integer fanUserId;
    private Integer followUserId;
}

package com.tjc.service;

import com.tjc.pojo.Relation;

/**
 * @Author: TJC
 * @Date: 2020/6/21 12:29
 * @description: TODO
 */
public interface RelationService {
    Relation findRelation(Integer fanUserId, Integer followUserId);

    void addRelation(Integer fanUserId, Integer followUserId);

    void removeRelation(Integer fanUserId, Integer followUserId);
}

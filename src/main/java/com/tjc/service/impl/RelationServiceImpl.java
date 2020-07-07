package com.tjc.service.impl;

import com.tjc.mapper.RelationMapper;
import com.tjc.pojo.Relation;
import com.tjc.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: TJC
 * @Date: 2020/6/21 12:30
 * @description: TODO
 */
@Service("relationService")
public class RelationServiceImpl implements RelationService {

    Class clazz = PostServiceImpl.class;
    @Autowired
    private RelationMapper relationMapper;

    @Override
    public Relation findRelation(Integer fanUserId, Integer followUserId) {
        return relationMapper.findRelation(fanUserId, followUserId);
    }

    @Override
    public void addRelation(Integer fanUserId, Integer followUserId) {
        relationMapper.addRelation(fanUserId, followUserId);
    }

    @Override
    public void removeRelation(Integer fanUserId, Integer followUserId) {
        relationMapper.removeRelation(fanUserId, followUserId);
    }
}

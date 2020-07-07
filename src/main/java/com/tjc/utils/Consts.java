package com.tjc.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: TJC
 * @Date: 2020/6/19 17:31
 * @description: TODO
 */
public class Consts {
    public static final Map<String, Integer> TYPE_MAP = new HashMap<>() {
        {
            put("提问", 0);
            put("分享", 1);
            put("讨论", 2);
            put("建议", 3);
            put("公告", 4);
            put("动态", 5);
        }
    };
}

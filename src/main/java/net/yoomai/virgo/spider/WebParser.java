/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package net.yoomai.virgo.spider;

import net.yoomai.virgo.spider.entities.Estimate;

import java.io.File;
import java.util.List;

/**
 * 解析器，规范所有页面规则解析器的工作方式，所有页面解析器都需要集成该类
 *
 * @author : LeiYu & coffeefoam@126.com & http://github.com/coffeefoam
 * @(#)WebParser.java 1.0 26/05/2014
 */
public interface WebParser {
    /**
     * 解析html文件，生成相对应的估价信息
     *
     * @param htmlFile
     * @return
     */
    public List<Estimate> parser(File htmlFile);
}

/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package net.yoomai.virgo.spider.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * 保存评估的结果，
 *
 * @author LeiYu & coffeefoam@126.com & http://github.com/coffeefoam
 * @(#)Estimate.java 1.0 26/05/2014
 */
public class Estimate implements Serializable {
    private long id;
    private String productName;
    private Date date;
    private int type; // 标示哪个市场的评估价格，1 中国市场，2 国际市场， 3 企业价格
    private String content; // 每个市场的价格表现均不同，用json串来进行存储
    private Date generateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getGenerateTime() {
        return generateTime;
    }

    public void setGenerateTime(Date generateTime) {
        this.generateTime = generateTime;
    }
}

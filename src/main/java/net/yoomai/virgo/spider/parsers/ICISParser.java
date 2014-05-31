/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package net.yoomai.virgo.spider.parsers;

import net.yoomai.virgo.spider.WebParser;
import net.yoomai.virgo.spider.entities.Estimate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 针对ICIS-China网站进行有效分析，形成估价信息
 *
 * @author LeiYu & coffeefoam@126.com & http://github.com/coffeefoam
 * @(#)ICISParser.java 1.0 26/05/2014
 */
public class ICISParser implements WebParser {
    private final static Log log = LogFactory.getLog(ICISParser.class);

    @Override
    public List<Estimate> parser(File htmlFile) {
        Document doc = null;
        try {
            doc = Jsoup.parse(htmlFile, "UTF-8");
        } catch (IOException e) {
            log.error(e.getMessage() + " : " + e.getCause());
        }

        log.info("doc: " + doc);
        if (doc != null) {
            Elements elements = doc.getElementsByClass("price-datalist");
            for (int i = 0; i < elements.size(); i++) {
                Element element = elements.get(i);
                log.info(element.toString());
            }
        }

        return null;
    }
}

/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package net.yoomai.virgo.spider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 用户模拟器，模拟用户的登录，页面访问等操作
 *
 * @author : LeiYu & coffeefoam@126.com & http://github.com/coffeefoam
 * @since : 1.7
 * @(#)Emulator.java 1.0 22/05/2014
 */
public class Emulator {
    private final static Log log = LogFactory.getLog(Emulator.class);

    public Emulator() {}

    /**
     * 模拟用户登录
     *
     * @param params
     * @param url
     */
    public String login(Map<String, String> params, String url) {
        String cookie = "";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        List<NameValuePair> nvps = generateURLParams(params);
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage() + " : " + e.getCause());
        }

        CloseableHttpResponse response = null;

        try {
            response = httpClient.execute(httpPost);
        } catch (IOException e) {
            log.error(e.getMessage() + " : " + e.getCause());
        }

        if (response != null) {
            StatusLine statusLine = response.getStatusLine();
            log.info(statusLine);
            if (statusLine.getStatusCode() == 200 || statusLine.getStatusCode() == 302) {
                Header[] headers = response.getHeaders("Set-Cookie");
                for (Header header : headers) {
                    cookie += header.getValue() + ";";
                }
            }
        }

        return cookie;
    }

    /**
     * 获取有效的页面
     *
     * @param url
     * @param cookies
     */
    public void getPage(String url, String cookies) {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Cookie", cookies);
        httpGet.setHeader("Content-Type", "text/html;charset=gb2312");

        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            log.error(e.getMessage() + " : " + e.getCause());
        }

        if (response != null) {
            StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() == 200) {
                try {
                    InputStream is = response.getEntity().getContent();
                    OutputStream outputStream = System.out;

                    byte[] bytes = new byte[1024];
                    int count = 0;
                    while ((count = is.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, count);
                    }
                } catch (IOException e) {
                    log.error(e.getMessage() + " : " + e.getCause());
                }
            }
        }
    }

    private List<NameValuePair> generateURLParams(Map<String, String> params) {
        List<NameValuePair> nvs = new ArrayList<NameValuePair>();

        Iterator<String> keys = params.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            BasicNameValuePair basicNameValuePair = new BasicNameValuePair(key, params.get(key));
            nvs.add(basicNameValuePair);
        }

        return nvs;
    }
}

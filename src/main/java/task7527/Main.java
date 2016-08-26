package task7527;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Package: task7527
 * ClassName: Main
 * Author: Founder123
 * Description:
 * CreateDate: 2016-08-26 09:32:54
 * Version: 1.0
 */
public class Main {

    public static void main(String[] args) throws Exception {

        BasicCookieStore cookieStore = new BasicCookieStore();
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(50000)
                .setConnectTimeout(50000)
                .setConnectionRequestTimeout(50000)
                .build();
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCookieStore(cookieStore)
                .setDefaultRequestConfig(defaultRequestConfig)
                .build();

        HttpGet httpget = new HttpGet("http://www.qlcoder.com/auth/login");

        String res = doRequest(httpclient, httpget, cookieStore);

        Document doc = Jsoup.parse(res);
        Element content = doc.getElementById("login-form");
        Elements _tokens = content.getElementsByAttributeValue("name","_token");
        Element _token = _tokens.get(0);
        String t = _token.attr("value");

        System.out.println("token is : "+t);
        HttpUriRequest login = RequestBuilder.post()
                .setUri(new URI("http://www.qlcoder.com/auth/login"))
                .addParameter("email", "872749212@qq.com")
                .addParameter("password", "123456")
                .addParameter("_token", t)
                .build();

        doRequest(httpclient, login, cookieStore);


        List<NameValuePair> formparams = new ArrayList<>();
        formparams.add(new BasicNameValuePair("email", "872749212@qq.com"));
        formparams.add(new BasicNameValuePair("password", "123456"));
        formparams.add(new BasicNameValuePair("_token", t));

        HttpEntity reqEntity = new UrlEncodedFormEntity(formparams, "utf-8");
        HttpPost post = new HttpPost("http://www.qlcoder.com/auth/login");
        post.setEntity(reqEntity);

        doRequest(httpclient, post, cookieStore);



        httpget = new HttpGet("http://www.qlcoder.com/home");

        doRequest(httpclient, httpget, cookieStore);

        formparams.add(new BasicNameValuePair("answer", "restful"));

        reqEntity = new UrlEncodedFormEntity(formparams, "utf-8");
        post = new HttpPost("http://www.qlcoder.com/task/4/solve");
        post.setEntity(reqEntity);

        doRequest(httpclient, post, cookieStore);

        httpclient.close();

    }


    public static String doRequest(CloseableHttpClient httpclient, HttpUriRequest httpUriRequest, BasicCookieStore cookieStore) throws IOException {
        CloseableHttpResponse response = httpclient.execute(httpUriRequest);
        HttpEntity entity = response.getEntity();

        System.out.println(response.getStatusLine());

//        System.out.println("Post logon cookies:");
//        List<Cookie> cookies = cookieStore.getCookies();
//        if (cookies.isEmpty()) {
//            System.out.println("None");
//        } else {
//            for (int i = 0; i < cookies.size(); i++) {
//                System.out.println("- " + cookies.get(i).toString());
//            }
//        }
        return EntityUtils.toString(entity);
    }

}

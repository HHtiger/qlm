package task7560;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Package: task7560
 * ClassName: Main
 * Author: Tiger
 * Description:
 * CreateDate: 2016/9/5
 * Version: 1.0
 */
public class Main {

    public static void main(String[] args) throws IOException {
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

        List<String> res = new ArrayList<>();

        int pageNum = 0;
        while (res.size()<166){
            HttpGet httpget = new HttpGet("https://movie.douban.com/top250?start=" + pageNum);
            String onePageHtml = doRequest(httpclient, httpget, cookieStore);
            Document doc = Jsoup.parse(onePageHtml);
            Elements rating_nums = doc.getElementsByClass("rating_num");
            res.addAll(rating_nums.stream().collect(
                    ArrayList::new,
                    (r,n) -> r.add(n.ownText()),
                    ArrayList::addAll)
            );
            pageNum = pageNum + 25;
        }

        BigDecimal allRes = new BigDecimal("0");

        for(int i=0;i<166;i++){
            BigDecimal bd2 = new BigDecimal(res.get(i));
            allRes = allRes.add(bd2);

        }

        System.out.println(allRes.doubleValue());

    }

    public static String doRequest(CloseableHttpClient httpclient, HttpUriRequest httpUriRequest, BasicCookieStore cookieStore) throws IOException {
        CloseableHttpResponse response = httpclient.execute(httpUriRequest);
        HttpEntity entity = response.getEntity();

        System.out.println(response.getStatusLine());

        return EntityUtils.toString(entity);
    }

}

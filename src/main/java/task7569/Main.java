package task7569;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;


public class Main {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        List<CrawlerCallable> ccl = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            ccl.add(new CrawlerCallable(i));
        }

        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future> fs = new ArrayList<>();
        for (CrawlerCallable cc : ccl) {
            fs.add(pool.submit(cc));
        }

        for (Future f : fs) {
            f.get();
        }

        System.out.println(Arrays.toString(CrawlerCallable.urls));

        pool.shutdown();

    }

}


class CrawlerCallable implements Callable {

    private static Logger logger = LoggerFactory.getLogger(task7569.CrawlerCallable.class);

    public static long[] urls = new long[11];

    private int index;

    public CrawlerCallable(int index) {
        this.index = index;

    }


    private void getTime(String orgin, long time) throws IOException, InterruptedException {

        boolean isBegin = false ;
        while (true) {

            Thread.sleep(time * 1000);
            String url = "http://www.qlcoder.com/train/spider3/" + this.index;
            CloseableHttpClient httpclient = HttpClients.custom().build();
            HttpGet httpget = new HttpGet(url);
            CloseableHttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            String now = EntityUtils.toString(entity);

//            logger.info("{},{}", index, urls[index]);

            if (isBegin) {
                urls[index] += time;
            }

            if (!now.equals(orgin)) {
                if (isBegin) {
                    logger.info("{} is over,time {}", index, urls[index]);
                    for(int i=0;i<urls.length;i++){
                        if( urls[i] == 0) continue;
                        for (int j=0;j<urls.length;j++){
                            if( urls[j] == 0 || i == j ) continue;
                            if (urls[i] == urls[j] ) {
                                logger.info("{},{} cc",i,j);
                            }
                        }
                    }
                    return;
                } else {
                    isBegin = true;
                    orgin = now;
                }
            }
        }

    }


    @Override
    public Object call() throws Exception {

        String url = "http://www.qlcoder.com/train/spider3/" + index;
        CloseableHttpClient httpclient = HttpClients.custom().build();
        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();
        String orgin = EntityUtils.toString(entity);


        long time = 5;

        getTime(orgin, time);

        return 0;

    }
}
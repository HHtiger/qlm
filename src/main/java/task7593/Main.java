package task7593;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;



/**
 * Created by Administrator on 2016/8/25.
 */
public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
//        URL url = new URL("http://www.qlcoder.com/download/noname");
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        //设置超时间为3秒
//        conn.setConnectTimeout(3 * 1000);
//        //防止屏蔽程序抓取而返回403错误
//        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
//
//        //得到输入流
//        InputStream inputStream = conn.getInputStream();

        InputStream inputStream = new FileInputStream("src/main/java/task7593/tmp/0b55b319ebc4b745d931e035cefc1e178a82156f");


        byte[] fileContent = readInputStream(inputStream);

        String type = FileType.getFileType(fileContent);
        String fileName_target = "src/main/java/task7593/tmp/res." + type;
        OutputStream outputStream = new FileOutputStream(fileName_target);
        outputStream.write(fileContent);
        outputStream.close();
        inputStream.close();
        System.out.println("target file : " + fileName_target);
    }

    /**
     * 从输入流中获取字节数组
     * @param inputStream inputStream
     * @return byte[]
     * @throws IOException
     */
    private static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

}

package task766e;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Package: task766e
 * ClassName: Main
 * Author: Tiger
 * Description:
 * CreateDate: 2016/8/29
 * Version: 1.0
 */
public class Main {

    /*
    * 照片文件在物理文件中的存放为依次的顺序存放。每个照片文件的存放规格如下:*
    * 1字节的标记位。0代表接下来的照片仍然可用，1代表接下来的照片已经被删除，2代表该物理文件接下来已经没有图片了。
    *
    * 4字节的size。标记照片的大小x。
    *
    * x字节，照片文件本身。
    **/
    public static void main(String[] args) throws IOException {
        byte[] sign = new byte[1];
        byte[] length = new byte[4];
        FileInputStream fileInputStream = new FileInputStream("src/main/java/task766e/tmp/rf.data");
        int seq = 0;
        while(fileInputStream.read(sign,0,sign.length)>=0){
            if(sign[0] == 1){
                System.out.println("data has been deleted");
                continue;
            }else if(sign[0] == 2){
                System.out.println("There is None data");
                break;
            }else if(sign[0] != 0){
                System.out.println("Error sign");
                continue;
            }

            int res = fileInputStream.read(length,0,length.length);
            if(res<0){
                throw new RuntimeException("None data");
            }

            int dataLength = byteArrayToInt(length);
            System.out.println("data length is:　" + dataLength);

            byte[] fileContent = new byte[dataLength];
            res = fileInputStream.read(fileContent,0,fileContent.length);
            if(res<0){
                throw new RuntimeException("None data");
            }

            String fileName_target = "src/main/java/task766e/tmp/res"+ ++seq +".jpg";
            OutputStream outputStream = new FileOutputStream(fileName_target);
            outputStream.write(fileContent);
            outputStream.close();
        }

        fileInputStream.close();

    }


    /**
     * int到byte[]
     * @param i
     * @return
     */
    public static byte[] intToByteArray(int i) {
        byte[] result = new byte[4];
        //由高位到低位
        result[0] = (byte)((i >> 24) & 0xFF);
        result[1] = (byte)((i >> 16) & 0xFF);
        result[2] = (byte)((i >> 8) & 0xFF);
        result[3] = (byte)(i & 0xFF);
        return result;
    }

    /**
     * byte[]转int
     * @param bytes
     * @return
     */
    public static int byteArrayToInt(byte[] bytes) {
        int value= 0;
        //由高位到低位
        for (int i = 0; i < 4; i++) {
            int shift= (4 - 1 - i) * 8;
            value +=(bytes[i] & 0x000000FF) << shift;//往高位游
        }
        return value;
    }

}

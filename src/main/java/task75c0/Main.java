package task75c0;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Administrator on 2016/8/25.
 */
public class Main {

    public static void main(String[] args) throws FormatException, ChecksumException, NotFoundException, IOException, WriterException {

        QRCodeReader reader = new QRCodeReader();


        BufferedImage image = ImageIO.read(new URL("http://121.201.63.168/uploads/144974949555397.png"));
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        Binarizer binarizer = new HybridBinarizer(source );
        BinaryBitmap imageBinaryBitmap = new BinaryBitmap(binarizer);
        Result result = reader.decode(imageBinaryBitmap);

        System.out.println("result = "+ result.toString());
        System.out.println("resultFormat = "+ result.getBarcodeFormat());
        System.out.println("resultText = "+ result.getText());

        /**二维码生成**/
        /**二维码内容**/
        String content = result.getText() ;
        /**内容的格式，如果有中文的话必须转码，**/
        content = new String(content.getBytes("UTF-8"), "ISO-8859-1") ;
        /**构造二维码，宽高分别为200、200**/
        QRCodeWriter writer = new QRCodeWriter() ;
        BitMatrix matrix = writer.encode(content, BarcodeFormat.QR_CODE, 200, 200) ;
        MatrixToImageWriter.writeToPath(matrix, "jpg", new File("src/main/java/task75c0/img.png").toPath());
    }

}

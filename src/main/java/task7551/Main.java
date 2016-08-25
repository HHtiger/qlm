package task7551;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Administrator on 2016/8/26.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedImage image = ImageIO.read(new URL("http://121.201.63.168/uploads/61244.jpg"));
        int pixel = image.getRGB(0,0);
        int red = (pixel & 0xff0000) >> 16;// red
        int green = (pixel & 0xff00) >> 8;// green
        int blue = (pixel & 0xff); // blue

        System.out.println( red + "-" + green + "-" + blue);
    }

}

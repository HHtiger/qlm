package task755a;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

/**
 * Package: task755a
 * ClassName: Main
 * Author: he_hu@founder.com.cn
 * Description:
 * CreateDate: 2016/8/25
 * Version: 1.0
 */
public class Main {

    public static void main(String[] args)throws IOException{



        Comparator<Path> comparator = (pathA, pathB) -> {try {
            if (Files.size(pathA) > Files.size(pathB)) return 1;
            else if (Files.size(pathA) == Files.size(pathB)) return 0;
            else return -1;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }};

        long startTime=System.currentTimeMillis();

        Path maxPath = Files.walk(Paths.get("src/main/java/task755a/root"), FileVisitOption.FOLLOW_LINKS)
        .parallel()
        .filter(path->!Files.isDirectory(path))
        .max(comparator).get();

        long endTime=System.currentTimeMillis();

        System.out.println("fileName:" + maxPath + "\nsize:" + Files.size(maxPath));
        System.out.println("time:" + (endTime - startTime) + "毫秒" );

    }

}

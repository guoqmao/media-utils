import com.harzone.media.core.MediaUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author fengjr
 * @date 2021/2/19.
 */
public class Tests {

    @Test
    public void testMediaUtil(){
        MediaUtil.setFFmpegPath("D:\\ffmpeg-4.3.1-2021-01-01-essentials_build\\bin\\ffmpeg.exe");
        List<String> commands = Arrays.asList("-y","-i","C:\\Users\\lucky god\\Videos\\video\\2daffa682dff45aaa2cd529d32d95279.avi","2daffa682dff45aaa2cd529d32d95279-format.mp4");
        String result = MediaUtil.executeCommand(commands);
        System.out.println(result);
    }
}

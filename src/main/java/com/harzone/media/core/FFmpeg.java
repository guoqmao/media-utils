package com.harzone.media.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

/**
 * @author fengjr
 * @date 2021/1/27.
 */
@Slf4j
@Component
public class FFmpeg {

    @Value("${system.ffmpeg-executable-path}")
    private String ffmpegExecute;


    /**
     * 将视频转换为指定格式（异步）
     * @param video 要转换的视频
     * @param newVideoName 转换后的新文件名
     */
    @Async("threadPool")
    public void transferFormatToNewVideo(File video, String newVideoName) {
        List<String> transCommand = Arrays.asList("-y", "-i", video.getName(), newVideoName);
        MediaUtil.setFFmpegPath(ffmpegExecute);
        MediaUtil.executeCommand(transCommand, video.getParentFile());
    }

    /**
     * 将视频转换为指定格式（异步）
     * @param video 要转换的视频
     * @param suffix 转换成哪种格式，如mp4
     */
    @Async("threadPool")
    public void transferFormatReplacement(File video, String suffix) {
        String name = video.getName().substring(0, video.getName().lastIndexOf("."))+"-format."+suffix;
        MediaUtil.setFFmpegPath(ffmpegExecute);

        List<String> transCommand = Arrays.asList("-y", "-i", video.getName(), name);
        MediaUtil.executeCommand(transCommand, video.getParentFile());
        try {
            renameVideo(new File(video.getParentFile(), name),
                    new File(video.getParentFile(), name.replace("-format","")));
            delVideo(video);
        } catch (IOException e) {
            log.info("[{}]覆盖[{}]失败", video.getName(), name, e);
        }
    }

    private void renameVideo(File origin, File newVideo) throws IOException {
        log.info("将文件[{}]重命名为[{}]", origin.getName(), newVideo.getName());
        Files.move(origin.toPath(), newVideo.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
    private void delVideo(File video) throws IOException {
        log.info("将文件[{}]删除", video.getName());
        Files.deleteIfExists(video.toPath());
    }

    private String doubleQuote(String str){
        return String.format("\"%s\"", str);
    }

}

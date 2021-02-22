package com.harzone.media.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * @author fengjr
 * @date 2021/1/27.
 */
@Data
public class MediaTransferFormatRequestVO {
    /**
     * 本地视频全路径
     */
    @NotNull
    private String videoPath;
    /**
     * 转换为这种格式
     */
    private String targetFormat;
    /**
     * 转换后的文件名
     */
    private String newName;

}


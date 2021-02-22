package com.harzone.media.controller;

import com.harzone.media.controller.result.JsonResult;
import com.harzone.media.core.FFmpeg;
import com.harzone.media.vo.MediaTransferFormatRequestVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * @author fengjr
 * @date 2021/1/27.
 */
@Validated
@RestController
public class VideoController {
    private final FFmpeg fFmpeg;

    public VideoController(FFmpeg fFmpeg) {
        this.fFmpeg = fFmpeg;
    }

    /**
     * 视频格式转换（替换）
     */
    @PostMapping("/v1/video/_transferReplace")
    public JsonResult<Object> _transferReplace(@RequestBody @Validated MediaTransferFormatRequestVO vo) {
        fFmpeg.transferFormatReplacement(new File(vo.getVideoPath()), vo.getTargetFormat());
        return JsonResult.success();
    }
    /**
     * 视频格式转换(重命名)
     */
    @PostMapping("/v1/video/_transferRename")
    public JsonResult<Object> _transferRename(@RequestBody @Validated MediaTransferFormatRequestVO vo) {
        fFmpeg.transferFormatToNewVideo(new File(vo.getVideoPath()), vo.getNewName());
        return JsonResult.success();
    }
}

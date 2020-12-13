package com.boss.bes.paper.api;

import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.core.data.convention.common.CommonRequest;
import com.boss.bes.paper.pojo.vo.updownpaper.PaperDownloadVO;
import com.boss.bes.paper.pojo.vo.updownpaper.PaperUploadVO;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.api
 * @Description:
 * @Date: 2020/7/12 12:36
 * @since: 1.0
 */
@RequestMapping("/paper-center/paper-updown")
public interface PaperUpDownApi {

    /**
     *  试卷上传
     * @param request 带有上传试卷信息以及模板信息的请求参数
     * @return 上传结果
     */
    @PostMapping(value = "/upload")
    CommonResponse<Boolean> doPaperUpload(@Valid @RequestBody CommonRequest<PaperUploadVO> request);


    /**
     *  模板下载
     * @param request 带有下载模板信息以及试卷信息的请求参数
     * @return 下载结果
     */
    @PostMapping(value = "/download")
    CommonResponse<Boolean> doPaperDownload(@Valid @RequestBody CommonRequest<PaperDownloadVO> request);
}

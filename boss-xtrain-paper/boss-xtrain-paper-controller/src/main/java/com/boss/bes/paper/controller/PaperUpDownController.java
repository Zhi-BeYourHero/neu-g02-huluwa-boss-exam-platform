package com.boss.bes.paper.controller;

import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.core.util.CommonResponseUtil;
import com.boss.bes.paper.pojo.vo.updownpaper.PaperDownloadVO;
import com.boss.bes.paper.utils.exception.PaperExceptionCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.boss.bes.paper.pojo.dto.updownpaper.PaperUpDownloadDTO;
import com.boss.bes.paper.pojo.vo.updownpaper.PaperUploadVO;
import com.boss.bes.paper.service.util.ConvertUtil;

import com.boss.bes.paper.service.PaperUpDownService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.controller
 * @Description:
 * @Date: 2020/7/9 11:33
 * @since: 1.0
 */
@Api("试卷上传下载接口")
@RestController
@CrossOrigin
@RequestMapping("/paperupdown")
@Slf4j
public class PaperUpDownController extends AbstractBaseController {

    @Autowired
    private PaperUpDownService paperUpOrDownService;

    /**
     *  试卷上传
     * @param request 带有上传试卷信息以及模板信息的请求参数
     * @return 上传结果
     */
    @ApiOperation("试卷上传")
    @PostMapping(value = "/upload")
    public CommonResponse<Boolean> doPaperUpload(@Valid @RequestBody CommonRequest<PaperUploadVO> request){
        PaperUpDownloadDTO paperUpOrDownloadDTO = ConvertUtil.createAndFill(request.getBody(),PaperUpDownloadDTO.class);
        if(paperUpOrDownService.uploadPaperService(paperUpOrDownloadDTO)<=0){
            return CommonResponseUtil.error(PaperExceptionCode.PAPER_CENTER_PAPER_UPLOAD_PAPER_NOT_FOUND_ERROR.getCode(), PaperExceptionCode.PAPER_CENTER_PAPER_UPLOAD_PAPER_NOT_FOUND_ERROR.getMessage());
        }
        return CommonResponseUtil.success(true);
    }


    /**
     *  模板下载
     * @param request 带有下载模板信息以及试卷信息的请求参数
     * @return 下载结果
     */
    @ApiOperation("试卷下载")
    @PostMapping(value = "/download")
    public CommonResponse<Boolean> doPaperDownload(@Valid @RequestBody CommonRequest<PaperDownloadVO> request){
        PaperUpDownloadDTO paperDownloadDTO=ConvertUtil.createAndFill(request.getBody(),PaperUpDownloadDTO.class);
        if(paperUpOrDownService.downloadPaperService(paperDownloadDTO)<=0){
            return CommonResponseUtil.error(PaperExceptionCode.PAPER_CENTER_PAPER_DOWNLOAD_TEMPLATE_NOT_FOUND_ERROR.getCode(), PaperExceptionCode.PAPER_CENTER_PAPER_DOWNLOAD_TEMPLATE_NOT_FOUND_ERROR.getMessage());
        }
        return CommonResponseUtil.success(true);
    }


}

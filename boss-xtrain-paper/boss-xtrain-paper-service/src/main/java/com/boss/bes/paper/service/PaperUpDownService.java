package com.boss.bes.paper.service;

import com.boss.bes.paper.pojo.dto.updownpaper.PaperUpDownloadDTO;


/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.service
 * @Description:
 * @Date: 2020/7/8 21:41
 * @since: 1.0
 */
public interface PaperUpDownService {

    /**
     *  试卷上传服务
     * @param paper 上传的试卷数据
     * @return 上传结果
     */
    int uploadPaperService(PaperUpDownloadDTO paper);

    /**
     *  试卷下载服务
     * @param paper 下载的试卷数据
     * @return 下载结果
     */
    int downloadPaperService(PaperUpDownloadDTO paper);

    /**
     *   查询要操作的试卷/模板数据
     * @param id 试卷/模板id
     * @param template 是否为模板
     * @return 试卷/模板数据
     */
    PaperUpDownloadDTO searchForPaper(Long id, Boolean template);
}

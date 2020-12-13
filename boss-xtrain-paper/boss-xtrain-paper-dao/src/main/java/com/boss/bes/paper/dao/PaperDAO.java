package com.boss.bes.paper.dao;

import com.boss.bes.paper.pojo.dto.exam.PaperCompanyListDTO;
import com.boss.bes.paper.pojo.dto.managepaper.crud.PaperSearchDTO;
import com.boss.bes.paper.pojo.dto.managetemplate.TemplateSearchDTO;
import com.boss.bes.paper.pojo.entity.Paper;

import java.util.List;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper
 * @Description: PaperDAO接口
 * @Date: 2020/7/8 9:45
 * @since: 1.0
 */
public interface PaperDAO {

    /**
     * 查找试卷内容，供提取试卷ID和试卷名称公司id
     * @param
     * @return 试卷列表
     */
    List<PaperCompanyListDTO> selectAllPaper();

    /**
     * 根据id删除试卷
     * @param id 试卷id
     * @return 删除结果
     */
    int deletePaper(Long id);

    /**
     *  根据试卷名查询试卷
     * @param paper 试卷名查询
     * @return 返回符合条件的试卷
     */
    List<Paper> selectPaperByName(Paper paper);

    /**
     *  修改试卷
     * @param paper 待修改的试卷数据
     * @return 修改结果
     */
    int modifyPaper(Paper paper);

    /**
     * 查询试卷份数
     * @param condition 查询条件
     * @return 符合条件的试卷份数
     */
    int selectPaperCount(PaperSearchDTO condition);

    /**
     * 根据id查询试卷
     * @param id 试卷id
     * @return id符合的试卷
     */
    Paper selectOnePaper(Long id);

    /**
     * 根据id查询模板
     * @param id 模板id
     * @return id符合的模板
     */
    Paper selectOneTemplate(Long id);

    /**
     * 插入单份试卷
     * @param paper 待插入的试卷数据
     * @return 插入结果
     */
    int insertOnePaper(Paper paper);

    /**
     * 根据条件查询试卷
     * @param condition 查询条件
     * @return 试卷列表
     */
    List<Paper> selectPaper(PaperSearchDTO condition);


    /**
     * 修改试卷状态
     * @param status 待修改的状态
     * @param id  试卷id
     * @return 修改结果
     */
    int modifyStatus(Integer status,Long id);

    /**
     * 修改试卷下载次数
     * @param id  试卷id
     * @return 修改结果
     */
    int modifyDownloadTimes(Long id);

    /**
     * 根据公司id查询试卷
     * @param id 公司id
     * @return 试卷列表
     */
    List<Paper> selectPaperByCompanyId(Long id);

    /**
     * 根据条件查询模板
     * @param condition 查询条件
     * @return 模板列表
     */
    List<Paper> selectTemplate(TemplateSearchDTO condition);

    /**
     * 根据条件查询模板份数
     * @param condition 查询条件
     * @return  符合条件的模板份数
     */
    int selectTemplateCount(TemplateSearchDTO condition);


}

package com.boss.bes.paper.dao.impl;

import com.boss.bes.paper.dao.PaperDAO;
import com.boss.bes.paper.pojo.dto.exam.PaperCompanyListDTO;
import com.boss.bes.paper.pojo.entity.Paper;
import com.boss.bes.paper.dao.mapper.PaperMapper;
import com.boss.bes.paper.pojo.dto.managepaper.crud.PaperSearchDTO;
import com.boss.bes.paper.pojo.dto.managetemplate.TemplateSearchDTO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.dao.impl
 * @Description:
 * @Date: 2020/7/8 9:22
 * @since: 1.0
 */
@Slf4j
@Repository
public class PaperDaoImpl implements PaperDAO {

    @Autowired
    private PaperMapper paperMapper;

    private static final String TEMPLATE ="template";
    private static final String UPDATED_TIME_DESC ="updated_time desc";
    private static final String COMB_EXAM_TIME ="combExamTime";
    private static final String COMPANY_ID ="companyId";
    private static final String STATUS ="status";
    private static final String COMB_EXAM_TIME_DESC ="comb_exam_time desc";

    /**
     *  根据试卷名查询试卷
     * @param condition 试卷名
     * @return 返回符合条件的试卷
     */
    public List<Paper> selectPaperByName(Paper condition) {
        Example example=new Example(Paper.class);
        Example.Criteria criteria=example.createCriteria();
        if(!"".equals(condition.getName())&&condition.getName()!=null){
            criteria.andEqualTo("name",condition.getName());
        }
        criteria.andEqualTo(TEMPLATE,false);
        return paperMapper.selectByExample(example);
    }

    /**
     *  修改试卷
     * @param paper 待修改的试卷数据
     * @return 修改结果
     */
    public int modifyPaper(Paper paper) {
        Example example=new Example(Paper.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("id",paper.getId());
        return  paperMapper.updateByExampleSelective(paper,example);
    }

    /**
     * 设置试卷查询条件
     * @param condition 试卷查询DTO参数
     * @return 查询条件Example
     */
    private Example setPaperSelectExample(PaperSearchDTO condition){
        Example example=new Example(Paper.class);
        Example.Criteria criteria=example.createCriteria();
        if(!"".equals(condition.getPaperName())&&condition.getPaperName()!=null){
            criteria.andLike("name","%"+condition.getPaperName()+"%");
        }
        if(!"".equals(condition.getCombExamMan())&&condition.getCombExamMan()!=null){
            criteria.andLike("combExamMan","%"+condition.getCombExamMan()+"%");
        }
        if(condition.getCombExamStartTime()!=null){
            criteria.andGreaterThanOrEqualTo(COMB_EXAM_TIME,condition.getCombExamStartTime());
        }
        if(condition.getCombExamEndTime()!=null){
            criteria.andLessThanOrEqualTo(COMB_EXAM_TIME,condition.getCombExamEndTime());
        }
        if(condition.getDifficulty()!=null&&condition.getDifficulty()!=0L){
            criteria.andEqualTo("difficulty",condition.getDifficulty());
        }
        if(condition.getPaperType()!=null&&condition.getPaperType()!=0L){
            criteria.andEqualTo("paperType",condition.getPaperType());
        }
        if(condition.getCompanyId()!=null){
            criteria.andEqualTo(COMPANY_ID,condition.getCompanyId());
        }
        criteria.andEqualTo(TEMPLATE,condition.getTemplate());
        example.setOrderByClause(UPDATED_TIME_DESC);
        return example;
    }

    /**
     * 查询试卷份数
     * @param condition 查询条件
     * @return 符合条件的试卷份数
     */
    @Override
    public int selectPaperCount(PaperSearchDTO condition){
        Example example=setPaperSelectExample(condition);
        return paperMapper.selectCountByExample(example);
    }

    /**
     * 根据条件查询试卷
     * @param condition 查询条件
     * @return 试卷列表
     */
    @Override
    public List<Paper> selectPaper(PaperSearchDTO condition) {
        Example example=setPaperSelectExample(condition);
        return paperMapper.selectByExample(example);
    }

    /**
     * 根据id查询试卷
     * @param id 试卷id
     * @return id符合的试卷
     */
    @Override
    public Paper selectOnePaper(Long id) {
        return paperMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据id查询模板
     * @param id 模板id
     * @return id符合的模板
     */
    @Override
    public Paper selectOneTemplate(Long id) {
        return paperMapper.selectByPrimaryKey(id);
    }


    /**
     * 插入单份试卷
     * @param paper 待插入的试卷数据
     * @return 插入结果
     */
    @Override
    public int insertOnePaper(Paper paper){
        return paperMapper.insertSelective(paper);
    }

    /**
     * 根据id删除试卷
     * @param id 试卷id
     * @return 删除结果
     */
    @Override
    public int deletePaper(Long id){
        return paperMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改试卷状态
     * @param status 待修改的状态
     * @param id  试卷id
     * @return 修改结果
     */
    @Override
    public int modifyStatus(Integer status,Long id) {
        Paper paper= paperMapper.selectByPrimaryKey(id);
        paper.setStatus(status);
        paper.setVersion(paper.getVersion()+1);
        return paperMapper.updateByPrimaryKey(paper);
    }

    /**
     * 修改试卷下载次数
     * @param id  试卷id
     * @return 修改结果
     */
    @Override
    public int modifyDownloadTimes(Long id) {
        Paper paper= paperMapper.selectByPrimaryKey(id);
        paper.setDownloadTimes(paper.getDownloadTimes()+1);
        paper.setVersion(paper.getVersion()+1);
        return paperMapper.updateByPrimaryKey(paper);
    }

    /**
     * 根据公司id查询试卷
     * @param id 公司id
     * @return 试卷列表
     */
    @Override
    public List<Paper> selectPaperByCompanyId(Long id) {
        Example example=new Example(Paper.class);
        Example.Criteria criteria=example.createCriteria();
        if(id!=null){
            criteria.andEqualTo(COMPANY_ID,id);
        }
        criteria.andNotEqualTo(STATUS,0);
        criteria.andEqualTo(TEMPLATE,false);
        example.setOrderByClause(COMB_EXAM_TIME_DESC);
        return paperMapper.selectByExample(example);
    }

    /**
     * 设置模板查询条件
     * @param condition 查询条件DTO参数
     * @return 查询条件Example
     */
    private Example setTemplateSelectExample(TemplateSearchDTO condition){
        Example example=new Example(Paper.class);
        Example.Criteria criteria=example.createCriteria();
        if(!"".equals(condition.getName())&&condition.getName()!=null){
            criteria.andLike("name","%"+condition.getName()+"%");
        }
        if(condition.getCombExamStartTime()!=null){
            criteria.andGreaterThanOrEqualTo(COMB_EXAM_TIME,condition.getCombExamStartTime());
        }
        if(condition.getCombExamEndTime()!=null){
            criteria.andLessThanOrEqualTo(COMB_EXAM_TIME,condition.getCombExamEndTime());
        }
        if(condition.getStatus()!=null){
            criteria.andEqualTo(STATUS,condition.getStatus());
        }
        criteria.andEqualTo(TEMPLATE,true);
        example.setOrderByClause(UPDATED_TIME_DESC);
        return example;
    }

    /**
     * 根据条件查询模板
     * @param condition 查询条件
     * @return 模板列表
     */
    @Override
    public List<Paper> selectTemplate(TemplateSearchDTO condition) {
        Example example=setTemplateSelectExample(condition);
        return paperMapper.selectByExample(example);
    }

    /**
     * 根据条件查询模板份数
     * @param condition 查询条件
     * @return  符合条件的模板份数
     */
    @Override
    public int selectTemplateCount(TemplateSearchDTO condition) {
        Example example=setTemplateSelectExample(condition);
        return paperMapper.selectCountByExample(example);
    }

    /**
     * 查找试卷内容，供提取试卷ID和试卷名称公司id
     * @param
     * @return 试卷列表
     */
    public List<PaperCompanyListDTO> selectAllPaper(){
        return paperMapper.getAllIds();
    }





}

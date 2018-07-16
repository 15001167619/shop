package com.znkf.shop.remote.knowledge;

/**
 * @author 武海升
 * @date 2018/7/3 18:12
 */
public interface IKnowledgeService {

    /**
     * @author 武海升
     * @param pageNo 当前页码
     * @param pageSize 每页显示数
     * @desc 获取常识列表
     */
    String getKnowledgeList(String args);

    /**
     * @author 武海升
     * @desc 获取常识详情
     * @param KnowledgeId 常识Id
     */
    String getKnowledgeDetails(Integer KnowledgeId);

    /**
     * @author 武海升
     * @desc 常识浏览量+1
     * @param KnowledgeId 常识Id
     */
    String setKnowledgeBrowseCount(Integer KnowledgeId);

}

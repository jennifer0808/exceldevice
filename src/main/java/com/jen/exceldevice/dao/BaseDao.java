package com.jen.exceldevice.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

@Repository
public class BaseDao extends SqlSessionDaoSupport {

    @Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    /**
     * 新增操作
     * @Title: save
     * @Description: TODO
     * @param @param key
     * @param @param params    设定文件
     * @return void    返回类型
     * @throws
     */
    public int save(String  key,Object params){
        return getSqlSession().insert(key,params);
    }

    /**
     * 修改操作
     * @Title: update
     * @Description: TODO
     * @param @param key
     * @param @param params    设定文件
     * @return void    返回类型
     * @throws
     */
    public int update(String key,Object params){
        return getSqlSession().update(key,params);
    }
    /**
     * 删除操作
     * @Title: delete
     * @Description: TODO
     * @param @param key
     * @param @param params    设定文件
     * @return void    返回类型
     * @throws
     */
    public int delete(String key,Object params){
        return getSqlSession().delete(key,params);
    }

    /**
     * 通过id删除
     * @Title: delete
     * @Description: TODO
     * @param @param key
     * @param @param id    设定文件
     * @return void    返回类型
     * @throws
     */
    public int delete(String key, Serializable id){
        return getSqlSession().delete(key,id);
    }

    /**
     * 获取对象
     * @Title: get
     * @Description: TODO
     * @param @param key
     * @param @param id
     * @param @return    设定文件
     * @return T    返回类型
     * @throws
     */
    public  <T> T  get(String key,Serializable  id){
        return  getSqlSession().selectOne(key, id);
    }
    /**
     * 获取对象
     * @Title: get
     * @Description: TODO
     * @param @param key
     * @param @param params
     * @param @return    设定文件
     * @return T    返回类型
     * @throws
     */
    public  <T> T  get(String key,Object  params){
        return  getSqlSession().selectOne(key, params);
    }
    /**
     * 获取对象列表
     * @Title: getList
     * @Description: TODO
     * @param @param key
     * @param @param params
     * @param @return    设定文件
     * @return List<T>    返回类型
     * @throws
     */
    public   <T> List<T> getList(String key, Object  params){
        // getSqlSession().selectList(mapId, queryKey,new RowBounds(pageId, pageSize));
        return  getSqlSession().selectList(key, params);
    }


}

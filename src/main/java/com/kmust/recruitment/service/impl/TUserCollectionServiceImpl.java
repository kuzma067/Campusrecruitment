package com.kmust.recruitment.service.impl;

import com.kmust.recruitment.dao.TUserCollectionDao;
import com.kmust.recruitment.entity.TUserCollection;
import com.kmust.recruitment.service.TUserCollectionService;
import com.kmust.recruitment.vo.TUserCollectionAndPublishPartTimeVo;
import com.kmust.recruitment.vo.TUserCollectionAndPublishRecruitVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TUserCollectionServiceImpl implements TUserCollectionService {
    @Autowired
    private TUserCollectionDao tUserCollectionDao;


    @Override
    public List<TUserCollection> findAll() {
        return tUserCollectionDao.findAll();
    }

    @Override
    public TUserCollection findById(Integer id) {
        return tUserCollectionDao.findById(id);
    }

    /**
     * 根据uid和rid查找收藏信息
     * @param uid
     * @param rid
     * @return
     */
    @Override
    public TUserCollection findByUidAndRid(Integer uid, Integer rid) {
        return tUserCollectionDao.findByUidAndRid(uid,rid);
    }
    /**
     * 根据uid和rid查找收藏信息
     * @param uid
     * @param tid
     * @return
     */
    @Override
    public TUserCollection findByUidAndTid(Integer uid, Integer tid) {
        return tUserCollectionDao.findByUidAndTid(uid,tid);
    }

    /**
     * 根据用户id查找收藏的招聘信息
     * @param uid
     * @return
     */
    @Override
    public List<TUserCollectionAndPublishRecruitVo> findRecruitByUid(Integer uid) {
        List<TUserCollectionAndPublishRecruitVo> result = tUserCollectionDao.findRecruitByUid(uid);
        System.out.println("TUserCollectionServiceImpl--> "+result);
        return result;
    }
    /**
     * 根据用户id查找收藏的兼职信息
     * @param uid
     * @return
     */
    @Override
    public List<TUserCollectionAndPublishPartTimeVo> findPartTimeByUid(Integer uid) {
        List<TUserCollectionAndPublishPartTimeVo> result = tUserCollectionDao.findPartTimeByUid(uid);
        System.out.println("TUserCollectionServiceImpl--> "+result);
        return result;
    }
    /**
     * 保存用户的收藏信息
     * @param tUserCollection
     */
    @Override
    public void save(TUserCollection tUserCollection) {
        tUserCollectionDao.save(tUserCollection);
        System.out.println("TUserCollectionServiceImpl--> 存入数据成功！");
    }

    @Override
    public void deleteById(Integer id) {
        tUserCollectionDao.deleteById(id);
        System.out.println("TUserCollectionServiceImpl--> 删除数据成功！");
    }
    /**
     * 根据uid，rid删除对应的招聘收藏信息
     * @param uid
     * @param rid
     */
    @Override
    public void deleteByUidAndRid(Integer uid, Integer rid) {
        tUserCollectionDao.deleteByUidAndRid(uid,rid);
        System.out.println("TUserCollectionServiceImpl--> 删除数据成功！");

    }
    /**
     * 根据uid，rid删除对应的兼职收藏信息
     * @param uid
     * @param tid
     */
    @Override
    public void deleteByUidAndTid(Integer uid, Integer tid) {
        tUserCollectionDao.deleteByUidAndTid(uid,tid);
        System.out.println("TUserCollectionServiceImpl--> 删除数据成功！");
    }

    @Override
    public void deleteAllByUid(Integer uid) {
        tUserCollectionDao.deleteAllByUid(uid);
        System.out.println("TUserCollectionServiceImpl--> 删除数据成功！");
    }

}

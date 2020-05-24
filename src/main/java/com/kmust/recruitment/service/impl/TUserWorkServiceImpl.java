package com.kmust.recruitment.service.impl;

import com.kmust.recruitment.dao.TUserWorkDao;
import com.kmust.recruitment.entity.TUserWork;
import com.kmust.recruitment.service.TUserWorkService;
import com.kmust.recruitment.constant.Role;
import com.kmust.recruitment.vo.ExcelVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class TUserWorkServiceImpl implements TUserWorkService {
    @Autowired
    TUserWorkDao tUserWorkDao;

    /**
     * Excel导出数据
     *
     * @return
     */
    @Override
    public List<ExcelVo> findExcelVo() {
        List<ExcelVo> result = tUserWorkDao.findAllBy(Role.STUDENT);
        log.info("findExcelVo查询数据成功！");
        return result;
    }

    /**
     * 保存数据
     * @param tUserWork
     */
    public void save(TUserWork tUserWork){
        tUserWorkDao.save(tUserWork);
        System.out.println("TUserWorkService--> save()");
    }

    /**
     * 根据id查找数据
     * @param id
     * @return
     */
    public TUserWork findById(Integer id){
        System.out.println("TUserWorkService--> findById()");
        return tUserWorkDao.findById(id);
    }

    /**
     * 根据uid查找数据
     * @param uid
     * @return
     */
    public TUserWork findByUid(Integer uid){
        System.out.println("TUserWorkService--> findByUid()");
        return tUserWorkDao.findByUid(uid);
    }

    public List<TUserWork> findAll(){
        System.out.println("TUserWorkService--> findById()");
        return tUserWorkDao.findAll();
    }

    /**
     * 根据id删除数据
     */
    public void deleteById(Integer id){
        tUserWorkDao.deleteById(id);
    }
}

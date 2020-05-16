package com.kmust.recruitment.dao;

import com.kmust.recruitment.entity.TInformationResources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TInformationResourcesDao extends JpaRepository<TInformationResources,String>, JpaSpecificationExecutor<TInformationResources> {

    List<TInformationResources> findAllByOrderByHeatDesc();
    List<TInformationResources> findAllByOrderByCreatedTimeDesc();

    TInformationResources findById(Integer id);
    void deleteById(Integer id);
}

package com.cqupt.project.manager;

import org.springframework.stereotype.Repository;

import com.cqupt.project.model.Engineering;

@Repository("engineeringDao")
public interface EngineeringMapper extends BaseDao<Engineering, Integer>{

}

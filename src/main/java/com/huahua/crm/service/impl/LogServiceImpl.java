package com.huahua.crm.service.impl;

import java.util.List;

import com.huahua.crm.mapper.LogMapper;
import com.huahua.crm.pojo.Log;
import com.huahua.crm.pojo.LogExample;
import com.huahua.crm.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author 
 * @date 2018/07/24
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class LogServiceImpl implements ILogService {

	@Autowired
	private LogMapper logMapper;
	
	@Override
	public long countByLogExample(LogExample logExample) {
		return logMapper.countByExample(logExample);
	}

	@Override
	public boolean deleteByLogExample(LogExample logExample) {
		return logMapper.deleteByExample(logExample) > 0 ? true : false;
	}

	@Override
	public boolean deleteByPrimaryKey(Integer id) {
		return logMapper.deleteByPrimaryKey(id) > 0 ? true : false;
	}

	@Override
	public boolean insertLog(Log log) {
		return logMapper.insert(log) > 0 ? true : false;
	}

	@Override
	public boolean insertSelective(Log log) {
		return logMapper.insertSelective(log) > 0 ? true : false;
	}

	@Override
	public List<Log> selectByLogExample(LogExample logExample) {
		return logMapper.selectByExample(logExample);
	}

	@Override
	public Log selectLogByPrimaryKey(Integer id) {
		return logMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean updateByLogExampleSelective(Log log, LogExample logExample) {
		return logMapper.updateByExampleSelective(log, logExample) > 0 ? true : false;
	}

	@Override
	public boolean updateByLogExample(Log log, LogExample logExample) {
		return logMapper.updateByExample(log, logExample) > 0 ? true : false;
	}

	@Override
	public boolean updateLogByPrimaryKeySelective(Log log) {
		return logMapper.updateByPrimaryKeySelective(log) > 0 ? true : false;
	}

	@Override
	public boolean updateLogByPrimaryKey(Log log) {
		return logMapper.updateByPrimaryKey(log) > 0 ? true : false ;
	}

}

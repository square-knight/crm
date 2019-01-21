package com.huahua.crm.service.impl;

import java.util.List;

import com.huahua.crm.mapper.CustomerMapper;
import com.huahua.crm.mapper.ServiceMapper;
import com.huahua.crm.mapper.UserMapper;
import com.huahua.crm.service.IServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huahua.crm.pojo.ServiceExample;

/**
 * 
 * @author WangHaoyu
 * @date 2018/07/24
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class ServiceServiceImpl implements IServiceService {

	@Autowired
	private ServiceMapper serviceMapper;
	
	@Autowired
	private CustomerMapper customerMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public long countByServiceExample(ServiceExample serviceExample) {
		return serviceMapper.countByExample(serviceExample);
	}

	@Override
	public boolean deleteByServiceExample(ServiceExample serviceExample) {
		return serviceMapper.deleteByExample(serviceExample) > 0 ? true : false;
	}

	@Override
	public boolean deleteByPrimaryKey(Integer id) {
		return serviceMapper.deleteByPrimaryKey(id) > 0 ? true : false;
	}

	@Override
	public boolean insertService(com.huahua.crm.pojo.Service service) {
	    service.setDeleteStatus(0);
		return serviceMapper.insert(service) > 0 ? true : false;
	}

	@Override
	public boolean insertSelective(com.huahua.crm.pojo.Service service) {
		return serviceMapper.insertSelective(service) > 0 ? true : false;
	}

	@Override
	public List<com.huahua.crm.pojo.Service> selectByServiceExample(ServiceExample serviceExample) {
	    
	    List<com.huahua.crm.pojo.Service> services =  serviceMapper.selectByExample(serviceExample);
	    //进行数据的二次封装
	    for (com.huahua.crm.pojo.Service service : services) {
	        if(service.getCreater() != null) {
	            service.setCreaterObject(userMapper.selectByPrimaryKey(service.getCreater()));
	        }
	        if(service.getHandler() != null) {
	            service.setHandlerObject(userMapper.selectByPrimaryKey(service.getHandler()));
	        }
	        if(service.getCustomerId() != null) {
	            service.setCustomer(customerMapper.selectByPrimaryKey(service.getCustomerId()));
	        }
        }
	    return services;
	}

	@Override
	public com.huahua.crm.pojo.Service selectServiceByPrimaryKey(Integer id) {
	    com.huahua.crm.pojo.Service service = serviceMapper.selectByPrimaryKey(id);
	    if(service.getCreater() != null) {
            service.setCreaterObject(userMapper.selectByPrimaryKey(service.getCreater()));
        }
        if(service.getHandler() != null) {
            service.setHandlerObject(userMapper.selectByPrimaryKey(service.getHandler()));
        }
        if(service.getCustomerId() != null) {
            service.setCustomer(customerMapper.selectByPrimaryKey(service.getCustomerId()));
        }
        return service;
	}

	@Override
	public boolean updateByServiceExample(com.huahua.crm.pojo.Service service, ServiceExample serviceExample) {
		return serviceMapper.updateByExample(service, serviceExample) > 0 ? true : false;
	}

	@Override
	public boolean updateByServiceExampleSelective(com.huahua.crm.pojo.Service service, ServiceExample serviceExample) {
		return serviceMapper.updateByExampleSelective(service, serviceExample) > 0 ? true : false;
	}

	@Override
	public boolean updateServiceByPrimaryKey(com.huahua.crm.pojo.Service service) {
		return serviceMapper.updateByPrimaryKey(service) > 0 ? true : false;
	}

	@Override
	public boolean updateServiceByPrimaryKeySelective(com.huahua.crm.pojo.Service service) {
		return serviceMapper.updateByPrimaryKeySelective(service) > 0 ? true : false;
	}

    /** (non-Javadoc)
     * @see IServiceService#deleteServicesByPrimaryKey(java.lang.String)
     */
    @Override
    public boolean deleteServicesByPrimaryKey(String ids) {
        boolean success;
        try {
            //先把ids字符串根据规则分割开
            String[] idsStrings = ids.split("-");
            for (String idString : idsStrings) {
                Integer id = Integer.parseInt(idString);
                com.huahua.crm.pojo.Service service = new com.huahua.crm.pojo.Service();
                service.setId(id);
                service.setDeleteStatus(1);
                //如果删除失败，则直接抛出异常
                if(serviceMapper.updateByPrimaryKeySelective(service) < 1){
                    throw new RuntimeException();
                }
            }
            success = true;
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return success;
    }

}

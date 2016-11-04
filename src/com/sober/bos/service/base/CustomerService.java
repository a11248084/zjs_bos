package com.sober.bos.service.base;

import com.sober.bos.domain.Customer;
import com.sober.bos.domain.Customer;

import java.util.List;



// 客户服务接口 
public interface CustomerService {
	// 未关联定区客户
	public List<Customer> findnoassociationCustomers();

	// 查询已经关联指定定区的客户
	public List<Customer> findhasassociationCustomers(String decidedZoneId);

	// 将未关联定区客户关联到定区上
	public void assignCustomersToDecidedZone(Integer[] customerIds, String decidedZoneId);

	//根据用户的手机号查询客户对象的功能
	public Customer findCustomerByPhone(String phone);

	public String findDecidedzoneByAddress(String addrerss);

}

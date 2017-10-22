package stu.dao;

import stu.vo.SystemManagerInfo;

public interface ManagerDAO {
	public SystemManagerInfo GetManagerInfoByName(String name);
}

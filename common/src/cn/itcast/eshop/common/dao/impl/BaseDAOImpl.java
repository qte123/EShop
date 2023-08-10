package cn.itcast.eshop.common.dao.impl;

import cn.itcast.eshop.common.dao.BaseDAO;
import cn.itcast.eshop.common.dao.IDataAccess;

public class BaseDAOImpl implements BaseDAO {
    //1.创建IDataAccess子类TXTDataAccess的对象
   protected IDataAccess dataAccess=new TXTDataAccess();
}

package com.emmaluo.IDao;

import com.emmaluo.model.StockInfo;
import org.springframework.stereotype.Component;

@Component
public interface StockInfoMapper {
    int deleteByPrimaryKey(String stockcode);

    int insert(StockInfo record);

    int insertSelective(StockInfo record);

    StockInfo selectByPrimaryKey(String stockcode);

    int updateByPrimaryKeySelective(StockInfo record);

    int updateByPrimaryKey(StockInfo record);
}
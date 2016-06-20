package com.emmaluo.com.emmaluo.pipline;

import com.emmaluo.IDao.StockInfoMapper;
import com.emmaluo.model.StockInfo;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;

/**
 * Created by zcluo on 2016/6/20.
 */
@Component("stockInfoDaoPipeline")
public class StockInfoDaoPipeline implements Pipeline{

    @Resource
    StockInfoMapper stockInfoMapper;




    @Override
    public void process(ResultItems resultItems, Task task) {
        stockInfoMapper.insert((StockInfo) resultItems.get("stockInfo"));
    }
}

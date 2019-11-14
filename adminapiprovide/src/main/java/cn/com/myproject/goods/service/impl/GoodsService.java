package cn.com.myproject.goods.service.impl;

import cn.com.myproject.goods.entity.PO.*;
import cn.com.myproject.goods.entity.VO.GoodsSpecStockVO;
import cn.com.myproject.goods.mapper.*;
import cn.com.myproject.goods.service.IGoodsService;
import cn.com.myproject.user.service.impl.UserService;
import com.aliyun.oss.common.utils.DateUtil;
import com.github.pagehelper.PageInfo;
import com.sun.media.jfxmedia.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.BinaryClient;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by LeiJia on 2017/9/15 0021.
 */
@Service
public class GoodsService implements IGoodsService {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(GoodsService.class);

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsSkuMapper goodsSkuMapper;

    @Autowired
    private StockLogMapper stockLogMapper;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private GoodsSpecMapper goodsSpecMapper;

    @Autowired
    private GoodsSaleMonthMapper goodsSaleMonthMapper;

    @Transactional
    @Override
    public String addGoods( Goods goods){
        List<GoodsImg> imgs = goods.getImgs();
        boolean flagVal = true;
        String goodsId = UUID.randomUUID().toString().replace("-", "");
        if(StringUtils.isNotBlank(goods.getGoodsName())){
            goods.setGoodsId(goodsId);
            goods.setCreateTime(Calendar.getInstance().getTimeInMillis());// 默认当前时间
            goods.setVersion(1);// 默认第一版本
            goods.setStatus((short)1);
        }
        int countVal = goodsMapper.insertGoods(goods);
        if(countVal > 0 && imgs.size() > 0){
            for(GoodsImg img: imgs){
                int i =0;
                try{
                    img.setGoodsImgId(UUID.randomUUID().toString().replace("-", ""));
                    img.setGoodsId(goodsId);
                    i =   goodsMapper.insertGoodsImg(img);
                    if(i < 1){
                        throw new RuntimeException("商品图片添加异常goodsMapper.insertGoodsImg(img)");
                    }
                }catch (Exception e){
                    logger.error("商品图片添加异常goodsMapper.insertGoodsImg(img)"+e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        if(countVal == 0){
            return "";
        }
        return goodsId;
    }

    @Transactional
    @Override
    public int removeGoods(String goodsId){
        int countVal = goodsMapper.deleteGoods(goodsId);
        if(countVal < 1){
            logger.error("删除商品异常goodsMapper.deleteGoods(goodsId)");
        //    throw new RuntimeException("删除商品异常goodsMapper.deleteGoods(goodsId)");
        }else{
            //删除s_goods_sku表记录
            int deleteGoodsSkuResult =  goodsMapper.deleteGoodsSkuByGoodsId(goodsId);
            if(deleteGoodsSkuResult < 1){
                logger.error("删除s_goods_sku表记录异常goodsMapper.deleteGoodsSkuByGoodsId(goodsId)");
             //   throw new RuntimeException("删除s_goods_sku表记录异常goodsMapper.deleteGoodsSkuByGoodsId(goodsId)");
            }
            //删除s_goods_spec表记录
            int deleteGoodsSpecResult = goodsMapper.deleteGoodsSpecByGoodsId(goodsId);
            if(deleteGoodsSpecResult < 1){
                logger.error("删除s_goods_spec表记录异常goodsMapper.deleteGoodsSkuByGoodsId(goodsId)");
             //   throw new RuntimeException("删除s_goods_spec表记录异常goodsMapper.deleteGoodsSkuByGoodsId(goodsId)");
            }
            //删除s_stock表记录
            int deleteStockResult =  goodsMapper.deleteStockByGoodsId(goodsId);
            if(deleteStockResult < 1){
                logger.error("删除s_stock表记录异常goodsMapper.deleteStockByGoodsId(goodsId)");
             //   throw new RuntimeException("删除s_stock表记录异常goodsMapper.deleteStockByGoodsId(goodsId)");
            }
            //删除s_stock_log表记录
            int deleteStockLogResult =  goodsMapper.deleteStockLogByGoodsId(goodsId);
            if(deleteStockLogResult < 1){
                logger.error("删除s_stock_log表记录异常goodsMapper.deleteStockLogByGoodsId(goodsId)");
              //  throw new RuntimeException("删除s_stock_log表记录异常goodsMapper.deleteStockLogByGoodsId(goodsId)");
            }


        }
        return countVal;
    }
    @Transactional
    @Override
    public int  modifyGoods(Goods goods){
        int result =0;
        List<GoodsImg> imgs = goods.getImgs();
        List<GoodsSpec> goodsSpecs = goods.getGoodsSpecs();
        int i = goodsMapper.updateGoods(goods);
        if(i >0){
            for(GoodsImg img: imgs){
                int j =0;
                try{
                    img.setGoodsId(goods.getGoodsId());
                    if(StringUtils.isNoneBlank(img.getGoodsImgId()) && StringUtils.isNotEmpty(img.getGoodsImgId()) ){
                        j = goodsMapper.updateGoodsImg(img);
                        if(j < 1){
                            logger.error("修改商品添加图片异常goodsMapper.insertGoodsImg(img)");
                            throw new RuntimeException("修改商品添加图片异常goodsMapper.insertGoodsImg(img)");
                        }
                    }else if((StringUtils.isNotBlank(img.getUrl())&&StringUtils.isNotEmpty(img.getUrl())) ){
                        img.setGoodsImgId(UUID.randomUUID().toString().replace("-", ""));
                        j =   goodsMapper.insertGoodsImg(img);
                        if(j < 1){
                            logger.error("修改商品添加图片异常goodsMapper.insertGoodsImg(img)");
                            throw new RuntimeException("修改商品添加图片异常goodsMapper.insertGoodsImg(img)");
                        }
                    }
                }catch (Exception e){
                    logger.error("修改商品图片异常"+e.getMessage());
                    e.printStackTrace();
                }
            }
            //更新商品规格库存与价格
           for(GoodsSpec goodsSpec: goodsSpecs) {
               goodsSpec.setGoodsId(goods.getGoodsId());
              int updateGoodsSpec = goodsSpecMapper.updateGoodsSpec(goodsSpec);
              if(updateGoodsSpec > 0){
                  Stock stock = new Stock();
                  stock.setGoodsId(goods.getGoodsId());
                  if(StringUtils.isNotBlank(goodsSpec.getGoodsSpecValuesId()))
                      stock.setGoodsSpecValueId(goodsSpec.getGoodsSpecValuesId());
                  if(StringUtils.isNotBlank(goodsSpec.getGoodsSpecValuesId1()))
                      stock.setGoodsSpecValueId1(goodsSpec.getGoodsSpecValuesId1());
                  if(StringUtils.isNotBlank(goodsSpec.getGoodsSpecValuesId2()))
                      stock.setGoodsSpecValueId2(goodsSpec.getGoodsSpecValuesId2());
                  if(StringUtils.isNotBlank(goodsSpec.getGoodsSpecValuesId3()))
                      stock.setGoodsSpecValueId3(goodsSpec.getGoodsSpecValuesId3());
                  if(StringUtils.isNotBlank(goodsSpec.getGoodsSpecValuesId4()))
                      stock.setGoodsSpecValueId4(goodsSpec.getGoodsSpecValuesId4());
                  Stock oldStock = stockMapper.searchStockDetail(stock);
                  if(goodsSpec.getStockNum()!= null){
                      stock.setNum(goodsSpec.getStockNum()); //总库存
                  }
                  stock.setStockId(oldStock.getStockId());
                  stock.setOwnNum(goodsSpec.getStockNum()-oldStock.getNum()+oldStock.getOwnNum()); //剩余库存（新的总库存-老的总库存+剩余库存）
                  int updateStcok = stockMapper.updateStock(stock);
                  if(updateStcok >0){
                      StockLog log =  new StockLog(UUID.randomUUID().toString().replace("-", ""),goods.getGoodsId(),stock.getStockId(), oldStock.getOwnNum(), goodsSpec.getStockNum()-oldStock.getNum(), stock.getOwnNum(),5);
                      int insertStockLogResult = stockLogMapper.insertStockLog(log);
                      if(insertStockLogResult <1){
                          logger.error("添加s_stock_log数据报异常stockLogMapper.insertStockLog(log)");
                          throw new RuntimeException("添加s_stock_log数据报异常stockLogMapper.insertStockLog(log)");
                      }
                  }
              }else{
                   logger.error("更新商品规格与价格异常goodsSpecMapper.updateGoodsSpec(goodsSpec)");
                   throw new RuntimeException("更新商品规格与价格异常goodsSpecMapper.updateGoodsSpec(goodsSpec)");
               }
           }

            result = 1;
        }
        return  result;
    }
    @Transactional
    @Override
    public int updateGoodsIntro(Goods goods){
        int countVal = goodsMapper.updateGoodsIntro(goods);
        return countVal;
    }
    @Override
    public PageInfo<Goods> searchGoodsList (int pageNum, int pageSize,String keyword){
        List<Goods> ctList = goodsMapper.searchGoodsList(pageNum,pageSize,keyword);
        return new PageInfo<Goods>(ctList);
    }

    @Override
    public PageInfo<Goods> searchGoodsListByCatId(int pageNum, int pageSize,String keyword,String catId,String couponId){
        List<Goods> ctList = goodsMapper.searchGoodsListByCatId(pageNum,pageSize,keyword,catId,couponId);
        return new PageInfo<Goods>(ctList);
    }


    @Override
    public Goods searchGoodsById(String goodsId){
        return goodsMapper.searchGoodsById(goodsId);
    }

    @Override
    public int checkGoodsIsExist(Goods goods){
        return goodsMapper.checkGoodsIsExist(goods);
    }

    @Override
    @Transactional
    public int addGoodsSpecAndStock(GoodsSpecStockVO vo){
           int result = 1;
           String goodsId = vo.getGoodsId();
           List<GoodsSpec> goodsSpecs = vo.getGoodsSpecs();
           GoodsSpec skuSpec = null;

           if(goodsSpecs !=null && goodsSpecs.size() >0){ //有规格商品
               for(int i =0; i<goodsSpecs.size();i++){
                   if(i==0){
                       skuSpec = goodsSpecs.get(i);
                   }
                   //添加s_goods_spec数据
                   GoodsSpec spec = goodsSpecs.get(i);
                   spec.setGoodsSpecId(UUID.randomUUID().toString().replace("-", ""));
                   spec.setGoodsId(vo.getGoodsId());
                   int insertGoodsSpecResult = goodsSpecMapper.insertGoodsSpec(spec);
                   if(insertGoodsSpecResult <1){
                       logger.error("添加商品spec报异常goodsSpecMapper.insertGoodsSpec(spec)");
                       throw new RuntimeException("添加商品spec报异常goodsSpecMapper.insertGoodsSpec(spec)");
                   }

                   addGoodsStockAndLog(spec, goodsId);

                }
           }else{//无规格商品

               skuSpec = new GoodsSpec();
               //添加s_goods_spec数据
               skuSpec.setGoodsSpecId(UUID.randomUUID().toString().replace("-", ""));
               skuSpec.setGoodsId(vo.getGoodsId());
               skuSpec.setCostPrice(vo.getCostPrice());
               skuSpec.setUserPrice(vo.getUserPrice());
               skuSpec.setVipPrice(vo.getVipPrice());
               skuSpec.setWeight(vo.getWeight());
               skuSpec.setStockNum(vo.getStockNum());
               int insertGoodsSpecResult = goodsSpecMapper.insertGoodsSpec(skuSpec);
               if(insertGoodsSpecResult <1){
                   logger.error("添加商品spec报异常goodsSpecMapper.insertGoodsSpec(spec)");
                   throw new RuntimeException("添加商品spec报异常goodsSpecMapper.insertGoodsSpec(spec)");
               }
               addGoodsStockAndLog(skuSpec, goodsId);

           }
          //添加s_goods_sku数据
           if(StringUtils.isNotEmpty(skuSpec.getSpecId())&& StringUtils.isNotBlank(skuSpec.getSpecId())){
                 int insertGoodsSkuResult =  goodsSkuMapper.insertGoodsSku(new GoodsSku(UUID.randomUUID().toString().replace("-", ""),skuSpec.getSpecId(),goodsId));
                 if(insertGoodsSkuResult <1){
                       logger.error("添加商品sku报异常 goodsSkuMapper.insertGoodsSku(new GoodsSku(UUID.randomUUID().toString().replace(\"-\", \"\"),skuSpec.getSpecId(),goodsId))");
                       throw new RuntimeException("添加商品sku报异常 goodsSkuMapper.insertGoodsSku(new GoodsSku(UUID.randomUUID().toString().replace(\"-\", \"\"),skuSpec.getSpecId(),goodsId))");
                 }
           }
           if(StringUtils.isNotEmpty(skuSpec.getSpecId1())&& StringUtils.isNotBlank(skuSpec.getSpecId1())){
               int insertGoodsSkuResult =  goodsSkuMapper.insertGoodsSku(new GoodsSku(UUID.randomUUID().toString().replace("-", ""),skuSpec.getSpecId1(),goodsId));
               if(insertGoodsSkuResult <1){
                   logger.error("添加商品sku报异常 goodsSkuMapper.insertGoodsSku(new GoodsSku(UUID.randomUUID().toString().replace(\"-\", \"\"),skuSpec.getSpecId(),goodsId))");
                   throw new RuntimeException("添加商品sku报异常 goodsSkuMapper.insertGoodsSku(new GoodsSku(UUID.randomUUID().toString().replace(\"-\", \"\"),skuSpec.getSpecId(),goodsId))");
               }
           }

           if(StringUtils.isNotEmpty(skuSpec.getSpecId2())&& StringUtils.isNotBlank(skuSpec.getSpecId2())){
               int insertGoodsSkuResult =  goodsSkuMapper.insertGoodsSku(new GoodsSku(UUID.randomUUID().toString().replace("-", ""),skuSpec.getSpecId2(),goodsId));
               if(insertGoodsSkuResult <1){
                   logger.error("添加商品sku报异常 goodsSkuMapper.insertGoodsSku(new GoodsSku(UUID.randomUUID().toString().replace(\"-\", \"\"),skuSpec.getSpecId(),goodsId))");
                   throw new RuntimeException("添加商品sku报异常 goodsSkuMapper.insertGoodsSku(new GoodsSku(UUID.randomUUID().toString().replace(\"-\", \"\"),skuSpec.getSpecId(),goodsId))");
               }
           }

           if(StringUtils.isNotEmpty(skuSpec.getSpecId3())&& StringUtils.isNotBlank(skuSpec.getSpecId3())){
               int insertGoodsSkuResult =  goodsSkuMapper.insertGoodsSku(new GoodsSku(UUID.randomUUID().toString().replace("-", ""),skuSpec.getSpecId3(),goodsId));
               if(insertGoodsSkuResult <1){
                   logger.error("添加商品sku报异常 goodsSkuMapper.insertGoodsSku(new GoodsSku(UUID.randomUUID().toString().replace(\"-\", \"\"),skuSpec.getSpecId(),goodsId))");
                   throw new RuntimeException("添加商品sku报异常 goodsSkuMapper.insertGoodsSku(new GoodsSku(UUID.randomUUID().toString().replace(\"-\", \"\"),skuSpec.getSpecId(),goodsId))");
               }
           }

           if(StringUtils.isNotEmpty(skuSpec.getSpecId4())&& StringUtils.isNotBlank(skuSpec.getSpecId4())){
               int insertGoodsSkuResult =  goodsSkuMapper.insertGoodsSku(new GoodsSku(UUID.randomUUID().toString().replace("-", ""),skuSpec.getSpecId4(),goodsId));
               if(insertGoodsSkuResult <1){
                   logger.error("添加商品sku报异常 goodsSkuMapper.insertGoodsSku(new GoodsSku(UUID.randomUUID().toString().replace(\"-\", \"\"),skuSpec.getSpecId(),goodsId))");
                   throw new RuntimeException("添加商品sku报异常 goodsSkuMapper.insertGoodsSku(new GoodsSku(UUID.randomUUID().toString().replace(\"-\", \"\"),skuSpec.getSpecId(),goodsId))");
               }
           }
           return result;
    }

    public void addGoodsStockAndLog(GoodsSpec spec,String goodsId){

        //添加s_stock数据
        String tock_id =UUID.randomUUID().toString().replace("-", "");
        Stock stock = new Stock();
        stock.setGoodsId(goodsId);
        stock.setStockId(tock_id);
        if(StringUtils.isNotEmpty(spec.getGoodsSpecValuesId()) && StringUtils.isNotBlank(spec.getGoodsSpecValuesId())){
            stock.setGoodsSpecValueId(spec.getGoodsSpecValuesId());
        }
        if(StringUtils.isNotEmpty(spec.getGoodsSpecValuesId1()) && StringUtils.isNotBlank(spec.getGoodsSpecValuesId1())){
            stock.setGoodsSpecValueId1(spec.getGoodsSpecValuesId1());
        }
        if(StringUtils.isNotEmpty(spec.getGoodsSpecValuesId2()) && StringUtils.isNotBlank(spec.getGoodsSpecValuesId2())){
            stock.setGoodsSpecValueId2(spec.getGoodsSpecValuesId());
        }
        if(StringUtils.isNotEmpty(spec.getGoodsSpecValuesId3()) && StringUtils.isNotBlank(spec.getGoodsSpecValuesId3())){
            stock.setGoodsSpecValueId3(spec.getGoodsSpecValuesId3());
        }
        if(StringUtils.isNotEmpty(spec.getGoodsSpecValuesId4()) && StringUtils.isNotBlank(spec.getGoodsSpecValuesId4())){
            stock.setGoodsSpecValueId4(spec.getGoodsSpecValuesId4());
        }
        stock.setNum(spec.getStockNum());
        stock.setUseNum(0);
        stock.setOwnNum(spec.getStockNum());
        stock.setCreateTime(Calendar.getInstance().getTimeInMillis());
        stock.setUpdateTime(Calendar.getInstance().getTimeInMillis());
        stock.setVersion(1);
        stock.setStatus((short)1);
        int insertStockReslut = stockMapper.insertStock(stock);
        if(insertStockReslut <1){
            logger.error("添加s_stock数据报异常 stockMapper.insertStock(stock)");
            throw new RuntimeException("添加s_stock数据报异常 stockMapper.insertStock(stock)");
        }
        //添加s_stock_log数据
        StockLog log = new StockLog(UUID.randomUUID().toString().replace("-", ""),goodsId,tock_id, spec.getStockNum(), 0, spec.getStockNum(),0);
        int insertStockLogResult = stockLogMapper.insertStockLog(log);
        if(insertStockLogResult <1){
            logger.error("添加s_stock_log数据报异常stockLogMapper.insertStockLog(log)");
            throw new RuntimeException("添加s_stock_log数据报异常stockLogMapper.insertStockLog(log)");
        }
    }

    @Override
    @Transactional
    public int updateGoodsSpecAndStock(GoodsSpecStockVO vo){
        int result = 1;
        String goodsId = vo.getGoodsId();
        List<GoodsSpec> goodsSpecs = vo.getGoodsSpecs();
        GoodsSpec skuSpec = null;
        //添加s_goods_spec数据
        for(int i =0; i<goodsSpecs.size();i++){
            if(i==0){
                skuSpec = goodsSpecs.get(i);
                if(StringUtils.isNotEmpty(skuSpec.getGoodsSpecId()) && StringUtils.isNotBlank(skuSpec.getGoodsSpecId())){
                    skuSpec = goodsSpecMapper.searchGoodsSpecById(skuSpec.getGoodsSpecId());
                }
            }
            int insertGoodsSpecResult =0;
            GoodsSpec spec = goodsSpecs.get(i);
            spec.setGoodsId(vo.getGoodsId());
            GoodsSpec specO = new GoodsSpec();
            if(StringUtils.isNotEmpty(skuSpec.getGoodsSpecId()) && StringUtils.isNotBlank(skuSpec.getGoodsSpecId())){
                specO = goodsSpecMapper.searchGoodsSpecById(skuSpec.getGoodsSpecId());
            }else{
                specO = goodsSpecMapper.getGoodsSpecDetail(spec);
            }
            if(specO == null){
                spec.setGoodsSpecId(UUID.randomUUID().toString().replace("-", ""));
                insertGoodsSpecResult = goodsSpecMapper.insertGoodsSpec(spec);
                if(insertGoodsSpecResult <1){
                    logger.error("添加商品spec报异常goodsSpecMapper.insertGoodsSpec(spec)");
                    throw new RuntimeException("添加商品spec报异常goodsSpecMapper.insertGoodsSpec(spec)");
                }
            }else{
                spec.setGoodsSpecId(specO.getGoodsSpecId());
            }
            insertGoodsSpecResult =goodsSpecMapper.updateGoodsSpec(spec);
            if(insertGoodsSpecResult <1){
                logger.error("更新商品spec报异常goodsSpecMapper.updateGoodsSpec(spec)");
                throw new RuntimeException("更新商品spec报异常goodsSpecMapper.updateGoodsSpec(spec)");
            }

            //添加s_stock数据
            String tock_id =UUID.randomUUID().toString().replace("-", "");
            Stock stock = new Stock();
            stock.setGoodsId(goodsId);
            if(StringUtils.isNotEmpty(spec.getGoodsSpecValuesId()) && StringUtils.isNotBlank(spec.getGoodsSpecValuesId())){
                stock.setGoodsSpecValueId(spec.getGoodsSpecValuesId());
            }
            if(StringUtils.isNotEmpty(spec.getGoodsSpecValuesId1()) && StringUtils.isNotBlank(spec.getGoodsSpecValuesId1())){
                stock.setGoodsSpecValueId1(spec.getGoodsSpecValuesId1());
            }
            if(StringUtils.isNotEmpty(spec.getGoodsSpecValuesId2()) && StringUtils.isNotBlank(spec.getGoodsSpecValuesId2())){
                stock.setGoodsSpecValueId2(spec.getGoodsSpecValuesId());
            }
            if(StringUtils.isNotEmpty(spec.getGoodsSpecValuesId3()) && StringUtils.isNotBlank(spec.getGoodsSpecValuesId3())){
                stock.setGoodsSpecValueId3(spec.getGoodsSpecValuesId3());
            }
            if(StringUtils.isNotEmpty(spec.getGoodsSpecValuesId4()) && StringUtils.isNotBlank(spec.getGoodsSpecValuesId4())){
                stock.setGoodsSpecValueId4(spec.getGoodsSpecValuesId4());
            }
            stock.setNum(spec.getStockNum());
            stock.setUseNum(0);
            stock.setOwnNum(spec.getStockNum());
            stock.setCreateTime(Calendar.getInstance().getTimeInMillis());
            stock.setUpdateTime(Calendar.getInstance().getTimeInMillis());
            stock.setVersion(1);
            stock.setStatus((short)1);
            int insertStockReslut= 0;
            Stock stockO = stockMapper.searchStockDetail(stock);
            if(stockO == null){
                stock.setStockId(tock_id);
                 insertStockReslut = stockMapper.insertStock(stock);
                if(insertStockReslut <1){
                    logger.error("添加s_stock数据报异常 stockMapper.insertStock(stock)");
                    throw new RuntimeException("添加s_stock数据报异常 stockMapper.insertStock(stock)");
                }
            }else{
                stock.setStockId(stockO.getStockId());
            }
            insertStockReslut = stockMapper.updateStock(stock);
            if(insertStockReslut <1){
                logger.error("修改s_stock数据报异常 stockMapper.insertStock(stock)");
                throw new RuntimeException("修改s_stock数据报异常 stockMapper.insertStock(stock)");
            }
            //添加s_stock_log数据
            StockLog log = new StockLog();
            log.setGoodsId(goodsId);
            log.setStockId(stock.getStockId());
            log.setStartNum(spec.getStockNum());
            log.setEndNum(spec.getStockNum());
            log.setHandleNum(0);
            log.setType(0); //0:初始化;1:预占；2释放预占；3：购买；4：退货；5：管理员手动
            int insertStockLogResult =0;
            StockLog logO = stockLogMapper.searchStockLogDetail(log);
            if(logO == null){
                log.setStockId(UUID.randomUUID().toString().replace("-", ""));
                insertStockLogResult = stockLogMapper.insertStockLog(log);
                if(insertStockLogResult <1){
                    logger.error("添加s_stock_log数据报异常stockLogMapper.insertStockLog(log)");
                    throw new RuntimeException("添加s_stock_log数据报异常stockLogMapper.insertStockLog(log)");
                }
            }else{
                log.setStockId(logO.getStockId());
            }
            insertStockLogResult = stockLogMapper.updateStockLog(log);
            if(insertStockLogResult <1){
                logger.error("修改s_stock_log数据报异常stockLogMapper.insertStockLog(log)");
                throw new RuntimeException("修改s_stock_log数据报异常stockLogMapper.insertStockLog(log)");
            }

        }
        //添加s_goods_sku数据
        if(StringUtils.isNotEmpty(skuSpec.getSpecId())&& StringUtils.isNotBlank(skuSpec.getSpecId())){
            GoodsSku skuO = goodsSkuMapper.searchGoodsSkuDetail(new GoodsSku(skuSpec.getSpecId(),goodsId));
            if(skuO == null ){
                int insertGoodsSkuResult =  goodsSkuMapper.insertGoodsSku(new GoodsSku(UUID.randomUUID().toString().replace("-", ""),skuSpec.getSpecId(),goodsId));
                if(insertGoodsSkuResult <1){
                    logger.error("添加商品sku报异常 goodsSkuMapper.insertGoodsSku(new GoodsSku(UUID.randomUUID().toString().replace(\"-\", \"\"),skuSpec.getSpecId(),goodsId))");
                    throw new RuntimeException("添加商品sku报异常 goodsSkuMapper.insertGoodsSku(new GoodsSku(UUID.randomUUID().toString().replace(\"-\", \"\"),skuSpec.getSpecId(),goodsId))");
                }
            }
        }
        if(StringUtils.isNotEmpty(skuSpec.getSpecId1())&& StringUtils.isNotBlank(skuSpec.getSpecId1())){
            GoodsSku sku1O = goodsSkuMapper.searchGoodsSkuDetail(new GoodsSku(skuSpec.getSpecId1(),goodsId));
            if(sku1O == null ) {
                int insertGoodsSkuResult = goodsSkuMapper.insertGoodsSku(new GoodsSku(UUID.randomUUID().toString().replace("-", ""), skuSpec.getSpecId1(), goodsId));
                if (insertGoodsSkuResult < 1) {
                    logger.error("添加商品sku报异常 goodsSkuMapper.insertGoodsSku(new GoodsSku(UUID.randomUUID().toString().replace(\"-\", \"\"),skuSpec.getSpecId(),goodsId))");
                    throw new RuntimeException("添加商品sku报异常 goodsSkuMapper.insertGoodsSku(new GoodsSku(UUID.randomUUID().toString().replace(\"-\", \"\"),skuSpec.getSpecId(),goodsId))");
                }
            }
        }

        if(StringUtils.isNotEmpty(skuSpec.getSpecId2())&& StringUtils.isNotBlank(skuSpec.getSpecId2())){
            GoodsSku sku2O = goodsSkuMapper.searchGoodsSkuDetail(new GoodsSku(skuSpec.getSpecId2(),goodsId));
            if(sku2O == null ) {
                int insertGoodsSkuResult = goodsSkuMapper.insertGoodsSku(new GoodsSku(UUID.randomUUID().toString().replace("-", ""), skuSpec.getSpecId2(), goodsId));
                if (insertGoodsSkuResult < 1) {
                    logger.error("添加商品sku报异常 goodsSkuMapper.insertGoodsSku(new GoodsSku(UUID.randomUUID().toString().replace(\"-\", \"\"),skuSpec.getSpecId(),goodsId))");
                    throw new RuntimeException("添加商品sku报异常 goodsSkuMapper.insertGoodsSku(new GoodsSku(UUID.randomUUID().toString().replace(\"-\", \"\"),skuSpec.getSpecId(),goodsId))");
                }
            }
        }

        if(StringUtils.isNotEmpty(skuSpec.getSpecId3())&& StringUtils.isNotBlank(skuSpec.getSpecId3())){
            GoodsSku sku3O = goodsSkuMapper.searchGoodsSkuDetail(new GoodsSku(skuSpec.getSpecId3(),goodsId));
            if(sku3O == null ) {
                int insertGoodsSkuResult =  goodsSkuMapper.insertGoodsSku(new GoodsSku(UUID.randomUUID().toString().replace("-", ""),skuSpec.getSpecId3(),goodsId));
                if(insertGoodsSkuResult <1){
                    logger.error("添加商品sku报异常 goodsSkuMapper.insertGoodsSku(new GoodsSku(UUID.randomUUID().toString().replace(\"-\", \"\"),skuSpec.getSpecId(),goodsId))");
                    throw new RuntimeException("添加商品sku报异常 goodsSkuMapper.insertGoodsSku(new GoodsSku(UUID.randomUUID().toString().replace(\"-\", \"\"),skuSpec.getSpecId(),goodsId))");
                }
            }
        }

        if(StringUtils.isNotEmpty(skuSpec.getSpecId4())&& StringUtils.isNotBlank(skuSpec.getSpecId4())){
            GoodsSku sku4O = goodsSkuMapper.searchGoodsSkuDetail(new GoodsSku(skuSpec.getSpecId4(),goodsId));
            if(sku4O == null ) {
                int insertGoodsSkuResult =  goodsSkuMapper.insertGoodsSku(new GoodsSku(UUID.randomUUID().toString().replace("-", ""),skuSpec.getSpecId4(),goodsId));
                if(insertGoodsSkuResult <1){
                    logger.error("添加商品sku报异常 goodsSkuMapper.insertGoodsSku(new GoodsSku(UUID.randomUUID().toString().replace(\"-\", \"\"),skuSpec.getSpecId(),goodsId))");
                    throw new RuntimeException("添加商品sku报异常 goodsSkuMapper.insertGoodsSku(new GoodsSku(UUID.randomUUID().toString().replace(\"-\", \"\"),skuSpec.getSpecId(),goodsId))");
                }
            }
        }
        return result;
    }

    //判断是否是被移除的规格值
    public boolean isRemoveElement(GoodsSpec specO,List<GoodsSpec> goodsSpecs){
        boolean flag = false;
        for(GoodsSpec specN:goodsSpecs){
            if(specO.getGoodsId().equals(specN.getGoodsId())){
                if(StringUtils.isNotEmpty(specO.getSpecId()) && StringUtils.isNotEmpty(specN.getSpecId())){
                    if(StringUtils.isNotEmpty(specO.getGoodsSpecValuesId())&& StringUtils.isNotEmpty(specN.getGoodsSpecValuesId())){
                            if(!specO.getGoodsSpecValuesId().equals(specN.getGoodsSpecValuesId())){
                                flag=true;
                            }
                    }
                }
                if(StringUtils.isNotEmpty(specO.getSpecId1()) && StringUtils.isNotEmpty(specN.getSpecId1())){
                    if(StringUtils.isNotEmpty(specO.getGoodsSpecValuesId1())&& StringUtils.isNotEmpty(specN.getGoodsSpecValuesId1())){
                        if(!specO.getGoodsSpecValuesId1().equals(specN.getGoodsSpecValuesId1())){
                            flag=true;
                        }
                    }
                }

                if(StringUtils.isNotEmpty(specO.getSpecId2()) && StringUtils.isNotEmpty(specN.getSpecId2())){
                    if(StringUtils.isNotEmpty(specO.getGoodsSpecValuesId2())&& StringUtils.isNotEmpty(specN.getGoodsSpecValuesId2())){
                        if(!specO.getGoodsSpecValuesId2().equals(specN.getGoodsSpecValuesId2())){
                            flag=true;
                        }
                    }
                }
                if(StringUtils.isNotEmpty(specO.getSpecId3()) && StringUtils.isNotEmpty(specN.getSpecId3())){
                    if(StringUtils.isNotEmpty(specO.getGoodsSpecValuesId3())&& StringUtils.isNotEmpty(specN.getGoodsSpecValuesId3())){
                        if(!specO.getGoodsSpecValuesId3().equals(specN.getGoodsSpecValuesId3())){
                            flag=true;
                        }
                    }
                }

                if(StringUtils.isNotEmpty(specO.getSpecId4()) && StringUtils.isNotEmpty(specN.getSpecId4())){
                    if(StringUtils.isNotEmpty(specO.getGoodsSpecValuesId4())&& StringUtils.isNotEmpty(specN.getGoodsSpecValuesId4())){
                        if(!specO.getGoodsSpecValuesId4().equals(specN.getGoodsSpecValuesId4())){
                            flag=true;
                        }
                    }
                }
            }
        }

        return flag;
    }
    @Override
    public int selectGoodsMonthlySalesByGoodsId(GoodsSaleMonth goodsSaleMonth){
        int month_sale_mount=0;
        GoodsSaleMonth month  =   goodsSaleMonthMapper.searchGoodsSaleMonthDetail(goodsSaleMonth);
        if(month == null){
            month_sale_mount = 0;
        }else{
            month_sale_mount = month.getSaleNum();
        }

        return month_sale_mount
                ;
    }

    @Override
    public List<GoodsSku> searchGoodsSkuListByGoodsId(String goodsId){
        return goodsSkuMapper.searchGoodsSkuListByGoodsId(goodsId);
    }

}



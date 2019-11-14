package cn.com.myproject.goods.service;
import cn.com.myproject.goods.entity.PO.SpecValues;
import com.github.pagehelper.PageInfo;

/*
 * Created by LeiJia on 2017-09-14
 * desc：规格Service接口
 */
public interface ISpecValueService {
    public boolean addSpecValues(SpecValues specValues);

    public boolean removeSpecValues(String goodsSpecValuesId);

    public boolean modifySpecValues(SpecValues spec);

    public PageInfo<SpecValues> searchSpecValuesList(int pageNum,
                                         int pageSize,
                                         String valuesName,
                                         String sepcId);

    public SpecValues searchSpecValuesById(String goodsSpecValuesId);

}
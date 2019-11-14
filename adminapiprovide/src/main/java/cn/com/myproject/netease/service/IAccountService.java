package cn.com.myproject.netease.service;

import cn.com.myproject.netease.VO.Result;
import cn.com.myproject.netease.VO.ResultInfo;
import cn.com.myproject.netease.VO.ResultUinfos;
import cn.com.myproject.netease.VO.account.*;

/**
 * 网易云通信账号相关
 * Created by liyang-macbook on 2017/6/28.
 */
public interface IAccountService {

    /**
     * 创建网易云通信ID
     * @param createVO
     * @return
     *
     *  {
     *      "code":200,
     *      "info":{"token":"xx","accid":"xx","name":"xx"}
     *  }
     */
    ResultInfo create(IMCreateVO createVO);

    /***
     * 网易云通信ID更新
     * @param vo
     * @return
     */
    Result update(IMUpdateVO vo);

    /***
     * 更新并获取新token
     * @param vo
     * @return
     *
     * {
     *   "code":200,
     *   "info":{"token":"xxx","accid":"xx" }
     * }
     */
    ResultInfo refreshToken(IMRefreshTokenVO vo);

    /***
     * 封禁网易云通信ID
     * @param vo
     * @return
     */
    Result block(IMBlockVO vo);

    /**
     * 解禁网易云通信ID
     * @param vo
     * @return
     */
    Result unBlock(IMUnBlockVO vo);

    /**
     * 更新用户名片
     * @param vo
     * @return
     */
    Result updateUinfo(IMUpdateUinfoVO vo);

    /***
     * 获取用户名片
     * @param vo
     * @return
     * {
     *      "code":200,
     *      "uinfos":
     *      [
     *          {"email":"t1@163.com","accid":"t1","name":"abc","gender":1,"mobile":"18645454545"},
     *          {"accid":"t2","name":"def","gender":0}
     *      ]
     *  }
     */
    ResultUinfos getUinfos(IMGetUinfosVO vo);


    /***
     * 设置桌面端在线时，移动端是否需要推送
     * @param vo
     * @return
     */
    Result setDonnop(IMSetDonnopVO vo);
}

package cn.com.myproject.netease.service;


import cn.com.myproject.netease.VO.*;
import cn.com.myproject.netease.VO.chatroom.*;

/**
 * 聊天室
 * Created by liyang-macbook on 2017/6/29.
 */
public interface IIMChatroomService {
    /**
     *创建聊天室
     * @return
     *{
     *  "chatroom": {
     *      "roomid": 66,
     *      "valid": true,
     *      "announcement": null,
     *      "name": "mychatroom",
     *      "broadcasturl": "xxxxxx",
     *      "ext": "",
     *      "creator": "zhangsan"
     *  },
     *  "code": 200
     * }
     *
     * */
    ResultChatroom create(IMCreateVO vo);

    /**
     *查询聊天室信息
     * @return
     *{
     *  "chatroom": {
     *      "roomid": 66,
     *      "valid": true,
     *      "muted":false, //聊天室是否处于全体禁言状态，全体禁言时仅管理员和创建者可以发言
     *      "announcement": null,
     *      "name": "mychatroom",
     *      "broadcasturl": "xxxxxx",
     *      "onlineusercount": 1,
     *      "ext": "",
     *      "creator": "zhangsan"
     *  },
     *  "code": 200
     *}
     *
     * */
    ResultChatroom get(IMGetVO vo);

    /**
     *更新聊天室信息
     *{
     *  "chatroom": {
     *      "roomid": 66,
     *      "valid": true,
     *      "announcement": "这是聊天室",
     *      "name": "mychatroom",
     *      "broadcasturl": "xxxxxx",
     *      "ext": "",
     *      "creator": "zhangsan"
     *  },
     *  "code": 200
     *}
     *
     * */
    ResultChatroom update(IMUpdateVO vo);

    /**
     * 修改聊天室开/关闭状态
     * {
     *  "desc": {
     *      "roomid": 13,
     *      "valid": true,
     *      "announcement": "这是聊天室",
     *      "name": "myChatroom",
     *      "broadcasturl": "http://www.xxxx.com/xxxxxx",
     *      "ext": "",
     *      "creator": "zhangsan"
     *  },
     *  "code": 200
     * }
     * */
    ResultChatroomDesc toggleCloseStat(IMToggleCloseStatVO vo);

    /**
     *设置聊天室内用户角色
     *
     * {
     *  "desc": {
     *      "roomid": 16,
     *      "level": 10,
     *      "accid": "zhangsan",
     *      "type": "COMMON"
     *  },
     *  "code": 200
     * }
     *
     * 备注：
     * 返回的type字段可能为：
     * LIMITED,          //受限用户,黑名单+禁言
     * COMMON,           //普通固定成员
     * CREATOR,          //创建者
     * MANAGER,          //管理员
     * TEMPORARY,        //临时用户,非固定成员
     * */
    ResultChatroomDesc setMemberRole(IMSetMemberRoleVO vo);

    /**
     *请求聊天室地址
     * */
    IMRequestAddrReturnVO requestAddr(IMRequestAddrVO vo);

    /**
     *往聊天室内发消息
     * {
     *  "code":200,
     *  "desc":{
     *      "time": "1456396333115",
     *      "fromAvator":"http://b12026.nos.netease.com/MTAxMTAxMA==/bmltYV84NDU4OF8xNDU1ODczMjA2NzUwX2QzNjkxMjI2LWY2NmQtNDQ3Ni0E2LTg4NGE4MDNmOGIwMQ==",
     *      "msgid_client": "c9e6c306-804f-4ec3-b8f0-573778829419",
     *      "fromClientType": "REST",
     *      "attach": "This+is+test+msg",
     *      "roomId": "36",
     *      "fromAccount": "zhangsan",
     *      "fromNick": "张三",
     *      "type": "0",
     *      "ext": ""
     *   }
     * }
     * */
    ResultChatroomDesc sendMsg(IMSendMsgVO vo);

    /**
     *往聊天室内添加机器人
     * @return
     * {
     *  "desc": {
     *      "failAccids": "[\"hzzhangsan\"]",
     *      "successAccids": "[\"hzlisi\"]"
     *  },
     *  "code": 200
     * }
     * */
    ResultChatroomDesc addRobot(IMAddRobotVO vo);


    /**
     *从聊天室内删除机器人
     *@return
     * {
     *  "desc": {
     *      "failAccids": "[\"hzzhangsan\"]",
     *      "successAccids": "[\"hzlisi\"]"
     *  },
     *  "code": 200
     * }
     * */
    ResultChatroomDesc removeRobot(IMAddRobotVO vo);


    /**
     *设置临时禁言状态
     * */
    ResultChatroomDesc temporaryMute(IMTemporaryMuteVO vo);


    /**
     *往聊天室有序队列中新加或更新元素
     *
     * {
     *  "desc": {
     *      "muteDuration": 300
     *  },
     *  "code": 200
     * }
     * */
    Result queueOffer(IMQueueOfferVO vo);

    /**
     *从队列中取出元素
     * @return
     *
     * {
     *  "desc": {
     *      "value": "66666",
     *      "key": "1111"
     *  },
     *  "code": 200
     * }
     * */
    ResultChatroomDesc queuePoll(IMQueuePollVO vo);


    /**
     *排序列出队列中所有元素
     * @return
     * {
     *  "desc": {
     *      "list": [
     *          {
     *              "33333": "33333"
     *          }
     *      ]
     *  },
     *  "code": 200
     * }
     * */
    ResultChatroomDesc queueList(IMQueuePollVO vo);

    /**
     *删除清理整个队列
     * */
    Result queueDrop(IMQueuePollVO vo);

    /**
     *初始化队列
     * */
    Result queueInit(IMQueueInitVO vo);

    /**
     *设置聊天室整体禁言状态（仅创建者和管理员能发言）
     * @return
     *
     * {
     *  "desc": {
     *      "success": true
     *  },
     *  "code": 200
     * }
     * */
    ResultChatroomDesc muteRoom(IMMuteRoomVO vo);

    /**
     *查询聊天室统计指标TopN
     * 1、根据时间戳，按指定周期列出聊天室相关指标的TopN列表
     * 2、当天的统计指标需要到第二天才能查询
     *
     * @return
     *
     * {
     *  "code": 200,
     *  "data": [
     *      {
     *          "activeNums": 5955,       // 该聊天室内的活跃数
     *          "datetime": 1471712400,   // 统计时间点，单位秒，按天统计的是当天的0点整点；按小时统计的是指定小时的整点
     *          "enterNums": 18621,       // 进入人次数量
     *          "msgs": 2793,             // 聊天室内发生的消息数
     *          "period": "HOUR",         // 统计周期，HOUR表示按小时统计；DAY表示按天统计
     *          "roomId": 3571337         // 聊天室ID号
     *      },
     *      {
     *          "activeNums": 6047,
     *          "datetime": 1471708800,
     *          "enterNums": 15785,
     *          "msgs": 2706,
     *          "period": "HOUR",
     *          "roomId": 3573737
     *      },
     *      {
     *          "activeNums": 5498,
     *          "datetime": 1471708800,
     *          "enterNums": 14590,
     *          "msgs": 2258,
     *          "period": "HOUR",
     *          "roomId": 3513774
     *      }
     *  ]
     * }
     * */
    ResultData topn(IMTopnVO vo);

    /**
     *分页获取成员列表
     * {
     * "desc": {
     *  "data": [
     *  {
     *  "roomid": 111,
     *  "accid": "abc",
     *  "nick": "abc",
     *  "avator": "http://nim.nos.netease.com/MTAxMTAwMg==/bmltYV8xNzg4NTA1NF8xNDU2Mjg0NDQ3MDcyX2E4NmYzNWI5LWRhYWEtNDRmNC05ZjU1LTJhMDUyMGE5MzQ4ZA==",
     *  "ext": "ext",
     *  "type": "MANAGER",
     *  "level": 2,
     *  "onlineStat": true,
     *  "enterTime": 1487145487971,
     *  "blacklisted": true,
     *  "muted": true,
     *  "tempMuted": true,
     *  "tempMuteTtl": 120,
     *  "isRobot": true,
     *  "robotExpirAt":120
     *  }
     *  ]
     *  },
     *  "code": 200
     * }
     * */
    ResultDescData membersByPage(IMMembersByPageVO vo);

    /**
     *批量获取在线成员信息
     * @return
     * {
     *  "desc": {
     *      "data": [
     *      {
     *          "roomid": 111,
     *          "accid": "abc",
     *          "nick": "cba",
     *          "type": 1,
     *          "onlineStat": true
     *      }
     *      ]
     *  },
     *  "code": 200
     * }
     * */
    ResultDescData queryMembers(IMQueryMembersVO vo);

    /**
     *变更聊天室内的角色信息
     * */
    Result updateMyRoomRole(IMUpdateMyRoomRoleVO vo);
}

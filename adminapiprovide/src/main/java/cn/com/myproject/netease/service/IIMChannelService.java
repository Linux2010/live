package cn.com.myproject.netease.service;

import cn.com.myproject.netease.VO.channel.*;

/**
 *
 * 直播频道相关
 * Created by liyang-macbook on 2017/7/3.
 */
public interface IIMChannelService {
    /**
     *创建一个直播频道
     *
     * @return
     *
     * //返回结果格式
     * "Content-Type": "application/json; charset=utf-8"
     * {
     *  "code" : XXX,
     *  "msg" : XXX,
     *  "ret" : {
     *      "cid" : XXX,
     *      "ctime" : XXX,
     *      "pushurl" : XXX,
     *      "httpPullUrl" : XXX,
     *      "hlsPullUrl" : XXX,
     *      "rtmpPullUrl" : XXX
     *  }
     * }
     * */
    ResultChannel create(IMCreateVO vo);

    /**
     *修改直播频道信息
     * @return
     *"Content-Type": "application/json; charset=utf-8"
     *{
     *  "code" : XXX,
     *  "msg" : XXX,
     *  "ret" : {
     *
     *  }
     *}
     *
     * */
    ResultChannel update(IMUpdateVO vo);

    /**
     * 删除一个直播频道
     *@return
     *
     * "Content-Type": "application/json; charset=utf-8"
     * {
     *  "code" : XXX,
     *  "msg" : XXX,
     *  "ret" : {
     *
     *  }
     * }
     *
     * */
    ResultChannel delete(IMDeleteVO vo);

    /**
     *获取一个直播频道的信息
     *
     * //返回结果格式
     *"Content-Type": "application/json; charset=utf-8"
     *{
     *  "code" : XXX,
     *  "msg" : XXX,
     *  "ret" : {
     *      "ctime" : XXX,
     *      "cid" : XXX,
     *      "name" : XXX,
     *      "status" : XXX,
     *      "uid" : XXX,
     *      "needRecord" : XXX,
     *      "format" : XXX,
     *      "duration" : XXX,
     *      "filename" : XXX
     *  }
     *}
     *
     * ctime	Long	创建频道的时间戳
     * cid	String	频道ID，32位字符串
     * name	String	频道名称
     * status	int	频道状态（0：空闲； 1：直播； 2：禁用； 3：直播录制）
     * type	int	频道类型 ( 0 : rtmp, 1 : hls, 2 : http)
     * uid	Long	用户ID，是用户在网易云视频与通信业务的标识，用于与其他用户的业务进行区分。通常，用户不需关注和使用。
     * needRecord	int	1-开启录制； 0-关闭录制
     * format	int	1-flv； 0-mp4
     * duration	int	录制切片时长(分钟)，默认120分钟
     * filename	String	录制后文件名
     * recordStatus	String	网易云内部维护用字段，用户不需关注。后续版本将删除，请勿调用
     * */
    ResultChannel channelstats(IMDeleteVO vo);



    /**
     *获取用户直播频道列表
     *@return
     *
     * "Content-Type": "application/json; charset=utf-8"
     * {
     *  "code" : XXX,
     *  "msg" : XXX,
     *  "ret" : {
     *      "list" : [
     *          {
     *          "ctime" : XXX,
     *          "cid" : XXX,
     *          "name" : XXX,
     *          "status" : XXX,
     *          "uid" : XXX,
     *          "needRecord" : XXX,
     *          "format" : XXX,
     *          "duration" : XXX,
     *          "filename" : XXX
     *          },
     *
     *          {
     *          "ctime" : XXX,
     *          "cid" : XXX,
     *          "name" : XXX,
     *          "status" : XXX,
     *          "uid" : XXX,
     *          "needRecord" : XXX,
     *          "format" : XXX,
     *          "duration" : XXX,
     *          "filename" : XXX
     *          },
     *
     *           ...
     *
     *      ]
     *  }
     * }
     *
     *
     * ctime	Long	创建频道的时间戳
     * cid	String	频道ID，32位字符串
     * name	String	频道名称
     * status	int	频道状态（0：空闲； 1：直播； 2：禁用； 3：直播录制）
     * type	int	频道类型 ( 0 : rtmp, 1 : hls, 2 : http)
     * uid	Long	用户ID，是用户在网易云视频与通信业务的标识，用于与其他用户的业务进行区分。通常，用户不需关注和使用。
     * needRecord	int	1-开启录制； 0-关闭录制
     * format	int	1-flv； 0-mp4
     * duration	int	录制切片时长(分钟)，默认120分钟
     * filename	String	录制后文件名
     * msg	String	错误信息
     * */
    ResultChannelMap channellist(IMChannellistVO vo);


    /**
     *用户创建频道时获取的推流地址失效时，重新获取推流地址。
     * @return
     *
     * "Content-Type": "application/json; charset=utf-8"
     * {
     *  "code" : XXX,
     *  "msg" : XXX,
     *  "ret" : {
     *      "pushUrl" : XXX,
     *      "httpPullUrl" : XXX,
     *      "hlsPullUrl" : XXX,
     *      "rtmpPullUrl" : XXX
     *  }
     * }
     *
     * code	int	状态码
     * pushUrl	String	推流地址
     * httpPullUrl	String	http拉流地址
     * hlsPullUrl	String	hls拉流地址
     * rtmpPullUrl	String	rtmp拉流地址
     * msg	String	错误信息
     * */
    ResultChannel address(IMDeleteVO vo);

    /**
     *设置频道为录制状态，用户推流时，即可录制为视频文件。
     * */
    ResultChannel setSetalwaysrecord(IMSetSetalwaysrecordVO vo);

    /**
     *禁用用户正在直播的频道
     * */
    ResultChannel pause(IMDeleteVO vo);


    /**
     * 禁用一组用户正在直播的频道。（注意：每应用每天对禁用和恢复频道的接口总调用上限为400次。单次和批量接口统一计算，
     * 批量操作每频道每次操作计一次调用。超限后，批量禁用或恢复接口可能部分执行失败，请以successList返回内容为准。）
     *
     * @return
     *
     * "Content-Type": "application/json; charset=utf-8"
     * {
     *  "code" : XXX,
     *  "msg" : XXX,
     *  "ret" : {
     *      "successList" : [
     *          XXX,
     *          XXX,
     *          XXX,
     *          ...
     *      ]
     *  }
     * }
     * */
    ResultChannelList pauseList(IMPauseListVO vo);

    /**
     *恢复用户被禁用的频道。
     * */
    ResultChannel resume(IMDeleteVO vo);

    /**
     *恢复一组用户正在直播的频道。（注意：每应用每天对禁用和恢复频道的接口总调用上限为400次。单次和批量接口统一计算，
     * 批量操作每频道每次操作计一次调用。超限后，批量禁用或恢复接口可能部分执行失败，请以successList返回内容为准。）
     * */
    ResultChannelList resumeList(IMPauseListVO vo);


    /**
     *获取某频道录制视频文件列表，按生成时间由近至远提供分页。
     *@return
     *
     * "Content-Type": "application/json; charset=utf-8"
     * {
     *  "code" : XXX,
     *  "msg" : XXX,
     *  "ret" : {
     *      "pnum" : XXX,
     *      "totalRecords" : XXX,
     *      "totalPnum" : XXX,
     *      "records" : XXX,
     *      "videoList" : [
     *      {
     *          "video_name" : XXX,
     *          "orig_video_key" : XXX,
     *          "uid" : XXX,
     *          "vid" : XXX
     *      },
     *
     *      {
     *          "video_name" : XXX,
     *          "orig_video_key" : XXX,
     *          "uid" : XXX,
     *          "vid" : XXX
     *      },
     *
     *      ...
     *
     *      ]
     *  }
     * }
     * */
    ResultChannel videolist(IMVideolistVO vo);

    /**
     *获取某一时间范围的录制视频文件列表
     * @return
     *
     * "Content-Type": "application/json; charset=utf-8"
     * {
     *  "code" : XXX,
     *  "msg" : XXX,
     *  "ret" : {
     *  "videoList" : [
     *  {
     *      "name" : XXX,
     *      "url" : XXX,
     *      "vid" : XXX
     *  },
     *
     *  {
     *      "name" : XXX,
     *      "url" : XXX,
     *      "vid" : XXX
     *  },
     *
     *  ...
     *
     *  ]
     *  }
     *  }
     *
     *
     * code	int	状态码
     * msg	String	错误信息
     * videoList	JsonArray	录制视频列表
     * name	String	录制后文件名，格式为filename_YYYYMMDD-HHmmssYYYYMMDD-HHmmss, 文件名录制起始时间（年月日时分秒) -录制结束时间（年月日时分秒)
     * url	String	视频文件在点播桶中的存储路径
     * vid	Long	视频文件ID
     *
     *
     * */
    ResultChannelMap vodvideolist(IMVodvideolistVO vo);

    /**
     * 用户录制文件生成后，会将生成文件信息推送到该地址, 目前支持HTTP POST方式。
     * @return
     *
     * "Content-Type": "application/json; charset=utf-8"
     * {
     *  "code" : XXX,
     *  "ret" : {
     *      result : XXX
     *  }
     * }
     *
     * 回调内容示例
     * {"vid":"7563","orig_video_key":"8bfe052367414ef1bf8baa5b118111_1480499359291_1480499570111_2541778-00002.flv",
     * "video_name":"创建频道1_20161130-174919_20161130-175250","uid":"10000","nId":"nId1144","beginTime":"1480499359291",
     * "endTime":"1480499570111","cid":"1234XXX"}
     * 注意：计算MD5签名时须考虑body所有字段，请以实际收到的body字段为准，以下仅为body字段示例。
     *
     * 参数	说明
     * video_name	录制后文件名，格式为filename_YYYYMMDD-HHmmssYYYYMMDD-HHmmss, 文件名录制起始时间（年月日时分秒) -录制结束时间（年月日时分秒)
     * origUrl	视频文件原地址
     * orig_video_key	视频文件在点播桶中的存储路径
     * uid	用户ID，是用户在网易云视频与通信业务的标识，用于与其他用户的业务进行区分。通常，用户不需关注和使用。
     * vid	视频文件ID
     * cid	频道ID
     * beginTime	录制文件起始时间戳(毫秒)
     * endTime	录制文件结束时间戳(毫秒)
     * nId	消息ID，同一条消息nId全局唯一，网络超时或接收方返回非200状态码时根据业务规则进行重发，接收方接到多条通知情况下可用于进行消息去重
     * sign(http请求头)	对回调body内容按指定格式转换后进行MD5加密生成的签名，sign字段为http请求头内容。
     * 签名规则：将body所有字段按key进行字典排序(升序)组成待签名字符串content，对字符串content+signKey进行MD5签名，
     * 如：beginTime=1483406830579&cid=6355099987a648bfb8fb265847&endTime=1483406857109&nId=nId1000
     * &origUrl=http://bucket.vod.126.net/bucket/6355099987a648bfbec0c53.mp4&orig_video_key=6355099987a648bfbec0c53.mp4
     * &uid=100&vid=1000&video_name=092710_20170103signKey,对其进行MD5签名
     * */
    ResultChannel setcallback(IMSetCallbackVO vo);


    /**
     *用该秘钥对回调内容生成MD5签名，用于用户接口的校验。
     * 可以不设置，默认为“vcloud”。该秘钥对用户所有设置的回调地址生效。
     * @return
     *"Content-Type": "application/json; charset=utf-8"
     *{
     *  "code" : XXX,
     *  "ret" : {
     *      result : XXX
     *  }
     *}
     *
     *
     * */
    ResultChannel setSignKey(IMSetSignKeyVO vo);


    /**
     * 对于同一次录制产生的切片文件，合并成一个文件，通过查询录制文件列表接口可获取。目前支持同MP4格式间或同flv格式间的文件合并，待合并文件的分辨率要求相同，
     * 且同时在合并的任务数不能超过3个, 待合并视频总时长不得超过8小时，
     * 1分钟接口调用不能超过10次。如果用户设置了回调地址，也会将合并好的视频回调给用户（回调内容不包含beginTime，endTime），参看接口2.14 （设置视频录制回调地址）
     *
     * @return
     *"Content-Type": "application/json; charset=utf-8"
     *{
     *  "code" : XXX,
     *  "ret" : {
     *      result : XXX
     *  }
     *}
     *
     *
     * */
    ResultChannel merge(IMMergeVO vo);

    /**
     *  语音视频通话
     *
     *  获取语音视频安全认证签名
     *
     *  @return
     *
     *
     * */
    GetTokenReturnVO getToken(GetTokenVO vo);
}

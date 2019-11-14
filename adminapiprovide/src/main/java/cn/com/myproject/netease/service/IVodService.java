package cn.com.myproject.netease.service;

import cn.com.myproject.netease.VO.vod.*;

import java.io.InputStream;

/**
 * 网易云点播
 * Created by liyang-macbook on 2017/7/17.
 */
public interface IVodService {

    /**
     * 创建视频分类
     *
     * @return
     * {
     *  "ret" : {
     *   "typeId" : 1
     *  },
     *  "code" : 200
     * }
     *
     * */
    ResultVod createType(CreateTypeVO vo);

    /**
     * 获取视频分类列表
     *
     *@return
     * {
     *  "ret": {
     *      "typeName": "movie",
     *      "createTime": 1467006327149,
     *      "isDel": 1,
     *      "desc": "电影",
     *      "number": 0,
     *      "typeId": 227
     *  },
     *  "code": 200
     * }
     *
     * */
    ResultVod getType(GetTypeVO vo);

    /**
     *获取视频分类列表
     *
     * @return
     * code	Int	状态码
     * currentPage	Int	当前取得页数
     * pageSize	Int	一页的记录数目
     * pageNum	Int	总页数
     * totalRecords	Int	获取视频分类列表的分类数目
     * list	List	获取的视频分类列表
     * list.typeId	Int	视频分类Id
     * list.typeName	String	视频分类的名称
     * list.desc	String	视频分类的描述信息
     * list.number	Int	该类别下的视频文件数量
     * list.isDel	Int	是否允许被删除（默认分类不允许删除），1：允许，0：不允许
     * list.createTime	Int	视频分类的创建时间（单位：毫秒）
     *
     * {
     *  "ret": {
     *  "pageSize": 2,
     *  "totalRecords": 4,
     *  "list": [
     *  {
     *      "typeName": "默认分类",
     *      "createTime": 1464229521940,
     *      "isDel": 0,
     *      "desc": "默认分类",
     *      "number": 47,
     *      "typeId": 38
     *  },
     *  {
     *      "typeName": "直播录制",
     *      "createTime": 1464229521940,
     *      "isDel": 0,
     *      "desc": "直播录制",
     *      "number": 1,
     *      "typeId": 39
     *  }
     *  ],
     *  "currentPage": 1,
     *  "pageNum": 2
     *  },
     *  "code": 200
     * }
     *
     * */
    ResultVod listType(ListTypeVO vo);


    /**
     *修改视频分类
     * @return
     *
     * {
     * "ret": {},
     * "code": 200
     * }
     * */
    ResultVod updateType(UpdateTypeVO vo);


    /**
     *删除视频分类
     * @return
     * {
     * "ret": {},
     * "code": 200
     * }
     *
     * */
    ResultVod deleteType(DeleteTypeVO vo);

    /**
     *  设置视频的分类
     *  @return
     *
     * {
     * "ret": {},
     * "code": 200
     * }
     * */
    ResultVod setType(SetTypeVO vo);

    /**
     *创建视频转码模板
     *
     * @return
     * {
     * "ret": {
     * "presetId": 184
     * },
     * "code": 200
     * }
     *
     * presetId	Int	新生成的视频转码模板Id
     * */
    ResultVod createPreSet(CreatePreSetVO vo);


    /**
     * 获取视频转码模板
     * @return
     * {
     *  "ret": {
     *      "sdHls": 0,
     *      "isDel": 1,
     *      "shdHls": 0,
     *      "hdHls": 1,
     *      "presetId": 184,
     *      "sdMp4": 0,
     *      "sdFlv": 0,
     *      "shdMp4": 0,
     *      "shdFlv": 0,
     *      "presetName": "全高清",
     *      "hdFlv": 1,
     *      "hdMp4": 1
     *  },
     *  "code": 200
     * }
     * */
    ResultVod getPreSet(GetPreSetVO vo);



    /**
     * 获取视频转码模板列表
     * {
     *  "ret": {
     *  "pageSize": 2,
     *  "totalRecords": 8,
     *  "list": [
     *      {
     *          "sdHls": 0,
     *          "isDel": 0,
     *          "shdHls": 0,
     *          "hdHls": 0,
     *          "presetId": 39,
     *          "sdMp4": 0,
     *          "sdFlv": 0,
     *          "shdMp4": 0,
     *          "shdFlv": 0,
     *          "presetName": "默认模板",
     *          "hdFlv": 0,
     *          "hdMp4": 0
     *      },
     *      {
     *          "sdHls": 0,
     *          "isDel": 1,
     *          "shdHls": 0,
     *          "hdHls": 0,
     *          "presetId": 40,
     *          "sdMp4": 1,
     *          "sdFlv": 1,
     *          "shdMp4": 0,
     *          "shdFlv": 0,
     *          "presetName": "dd",
     *          "hdFlv": 0,
     *          "hdMp4": 0
     *      }
     *       ],
     *      "currentPage": 1,
     *      "pageNum": 4
     *  },
     *  "code": 200
     *  }
     *
     * */
    ResultVod listPreSet(ListPreSetVO vo);


    /**
     * 修改视频转码模板
     *@return
     * {
     * "ret": {},
     * "code": 200
     * }
     * */
    ResultVod updatePreSet(UpdatePreSetVO vo);

    /**
     * 删除视频转码模板
     * @return
     * {
     * "ret": {},
     * "code": 200
     * }
     * */
    ResultVod deletePreSet(DeleteTypeVO vo);


    /**
     * 创建视频水印模板
     * 重要提示：不建议设置水印的scale参数，缩放或拉伸水印图片会影响水印图片的效果，可能会出现模糊现象
     *
     * @return
     * {
     *  "ret": {
     *      "watermarkId": 16
     *  },
     *  "code": 200
     * }
     * */
    ResultVod createWaterMark(CreateWaterMarkVO vo);

    /**
     * 获取视频水印模板
     * 获取单个视频水印模板信息，包括：Id、视频水印模板名称、视频水印模板对应的配置参数。
     *
     * @return
     * {
     *  "ret": {
     *      "scale": null,
     *      "watermarkName": "梅花水印",
     *      "coordinate": "8%_6%",
     *      "description": "梅花水印",
     *      "watermarkId": 16,
     *      "imageId": 21,
     *      "imageUrl": "http://vodk32ywxdf.nosdn.127.net/fda0bee8-6b96-45e4-9455-0490f84acc8a.jpg",
     *      "isDel": 1
     *  },
     *  "code": 200
     * }
     * */
    ResultVod getWaterMark(GetWaterMarkVO vo);


    /**
     *  获取视频水印模板列表
     * @return
     * {
     *  "ret": {
     *      "pageSize": 2,
     *      "totalRecords": 9,
     *      "list": [
     *      {
     *          "scale": "10%x15%",
     *          "watermarkName": "小熊水印",
     *          "coordinate": "20%_12%",
     *          "description": "ss",
     *          "watermarkId": 6,
     *          "imageId": 21,
     *          "imageUrl": "http://vodk32ywxdf.nosdn.127.net/fda0bee8-6b96-45e4-9455-0490f84acc8a.jpg",
     *          "isDel": 1
     *      },
     *      {
     *          "scale": "10%x15%",
     *          "watermarkName": "royen_test",
     *          "coordinate": "5%_5%",
     *          "description": null,
     *          "watermarkId": 7,
     *          "imageId": 21,
     *          "imageUrl": "http://vodk32ywxdf.nosdn.127.net/fda0bee8-6b96-45e4-9455-0490f84acc8a.jpg",
     *          "isDel": 1
     *      }
     *      ],
     *      "currentPage": 1,
     *      "pageNum": 5
     *  },
     *  "code": 200
     * }
     *
     * 参数	类型	说明
     * code	Int	状态码
     * currentPage	Int	当前取得页数
     * pageSize	Int	一页的记录数目
     * pageNum	Int	总页数
     * totalRecords	Int	获取视频水印转码模板列表的分类数目
     * list	List	获取的视频水印模板列表
     * list.watermarkId	Int	视频水印模板Id
     * list.watermarkName	String	视频水印模板的名称
     * list.description	String	视频水印的描述信息
     * list.coordinate	String	视频水印左上角的坐标，其值为相对值，比如：
     * 8%_6%表示在视频左上角的偏右8%视频宽度、偏下6%视频高度的位置。
     * 如果不选填，默认设置为5%_5%
     * list.scale	String	视频水印的长宽，其值为相对值，比如：
     * 10%x15%表示长为视频宽度的10%，宽为视频宽度的15%。
     * 如果不选填，默认设置为不缩放或拉伸
     * list.imageId	Int	水印图片Id
     * list.imageUrl	String	水印图片URL
     * list.isDel	Int	是否允许被删除（默认水印不允许删除），1:允许，0:不允许
     * */
    ResultVod listWaterMark(ListWaterMarkVO vo);


    /**
     * 修改视频水印模板
     *
     * @return
     * {
     *  "ret": {},
     *  "code": 200
     * }
     * */
    ResultVod updateWaterMark(UpdateWaterMarkVO vo);

    /**
     * 删除视频水印模板
     * @return
     * {
     *  "ret": {},
     *  "code": 200
     * }
     * **/
    ResultVod deleteWaterMark(DeleteWaterMarkVO vo);

    /**
     * 获取视频文件信息
     * @return
     * {
     *  "ret": {
     *      "createTime": 1466578358729,
     *      "origUrl": "http://vodk32ywxdf.vod.126.net/vodk32ywxdf/44d30332-7402-4b2f-82c8-154dbb6b4e14.mp4",
     *      "downloadOrigUrl": "http://vodk32ywxdf.nosdn.127.net/44d30332-7402-4b2f-82c8-154dbb6b4e14.mp4?NOSAccessKeyId=ab1856bb39044591939d7b94e1b8e5ee&Expires=1498558005&download=watermark_test_1.mp4&Signature=%2BGu%2BvgiUP1rL4pbx%2B52GH4QCo%2FOHAtlhgzCzPV9f0vc%3D",
     *      "shdMp4Url": "http://vodk32ywxdf.vod.126.net/vodk32ywxdf/nos/mp4/2016/06/22/v32_shd.mp4",
     *      "sdMp4Size": 17906823,
     *      "videoName": "watermark_test_1",
     *      "downloadSdMp4Url": "http://vodk32ywxdf.nosdn.127.net/nos%2Fmp4%2F2016%2F06%2F22%2Fv32_sd.mp4?NOSAccessKeyId=ab1856bb39044591939d7b94e1b8e5ee&Expires=1498558006&download=%25E6%25A0%2587%25E6%25B8%2585_watermark_test_1.mp4&Signature=64FOWYzLciWyTe8hmLMRVCYRCsQLbEOWtNqcB9rUj18%3D",
     *      "description": null,
     *      "hdMp4Size": 25227850,
     *      "downloadSdFlvUrl": "http://vodk32ywxdf.nosdn.127.net/nos%2Fflv%2F2016%2F06%2F22%2Fv32_sd.flv?NOSAccessKeyId=ab1856bb39044591939d7b94e1b8e5ee&Expires=1498558006&download=%25E6%25A0%2587%25E6%25B8%2585_watermark_test_1.flv&Signature=pGmKYIjWq%2FZE9Gu8K1MZcuqQYG83cI2bnuiikLO8TCk%3D",
     *      "vid": 32,
     *      "shdMp4Size": 39874022,
     *      "sdFlvUrl": "http://vodk32ywxdf.vod.126.net/vodk32ywxdf/nos/flv/2016/06/22/v32_sd.flv",
     *      "sdFlvSize": 18013302,
     *      "hdMp4Url": "http://vodk32ywxdf.vod.126.net/vodk32ywxdf/nos/mp4/2016/06/22/v32_hd.mp4",
     *      "status": 40,
     *      "updateTime": 1466663164342,
     *      "sdMp4Url": "http://vodk32ywxdf.vod.126.net/vodk32ywxdf/nos/mp4/2016/06/22/v32_sd.mp4",
     *      "downloadHdMp4Url": "http://vodk32ywxdf.nosdn.127.net/nos%2Fmp4%2F2016%2F06%2F22%2Fv32_hd.mp4?NOSAccessKeyId=ab1856bb39044591939d7b94e1b8e5ee&Expires=1498558006&download=%25E9%25AB%2598%25E6%25B8%2585_watermark_test_1.mp4&Signature=kMUKJGcW8aSFauL2836pXA5UrEeowq2hLdpgMpjmr44%3D",
     *      "downloadShdMp4Url": "http://vodk32ywxdf.nosdn.127.net/nos%2Fmp4%2F2016%2F06%2F22%2Fv32_shd.mp4?NOSAccessKeyId=ab1856bb39044591939d7b94e1b8e5ee&Expires=1498558006&download=%25E8%25B6%2585%25E6%25B8%2585_watermark_test_1.mp4&Signature=ch30%2BtX20b54UeskhFZs37r55jkt2WIV87jlaZgKmPY%3D",
     *      "typeName": "默认分类",
     *      "duration": 195,
     *      "snapshotUrl": "http://vodk32ywxdf.nosdn.127.net/6c4a9501-ee3c-4e00-9a5f-20f6616f0ad3.jpg",
     *      "initialSize": 15601202,
     *      "typeId": 38,
     *      "completeTime": 1466663164342
     *  },
     *  "code": 200
     * }
     *
     *
     *
     * 参数	类型	说明
     * code	Int	状态码
     * videoName	String	视频的名称
     * status	Int	视频的状态，10表示初始，20表示失败，30表示处理中，40表示成功，50表示屏蔽
     * description	String	视频的描述信息
     * completeTime	Int	转码完成时间
     * duration	Int	视频播放时长（单位：秒）
     * durationMsec	Int	视频播放时长（单位：毫秒）
     * width	String	视频宽度，默认为"0"
     * height	String	视频高度，默认为"0"
     * typeId	Int	视频所属分类Id
     * typeName	String	视频所属分类名称
     * snapshotUrl	String	视频封面截图URL地址
     * origUrl	String	原始视频的播放地址
     * downloadOrigUrl	String	原始视频的下载地址
     * initialSize	Int	原始视频文件大小（单位：字节）
     * sdMp4Url	String	标清Mp4视频格式文件播放地址
     * downloadSdMp4Url	String	标清Mp4视频格式文件下载地址
     * sdMp4Size	Int	标清Mp4视频格式文件的大小（单位：字节）
     * sdMp4Width	String	标清Mp4视频宽度，默认为"0"
     * sdMp4Height	String	标清Mp4视频高度，默认为"0"
     * hdMp4Url	String	高清Mp4视频格式文件播放地址
     * downloadHdMp4Url	String	高清Mp4视频格式文件下载地址
     * hdMp4Size	Int	高清Mp4视频格式文件的大小（单位：字节）
     * hdMp4Width	String	高清Mp4视频宽度，默认为"0"
     * hdMp4Height	String	高清Mp4视频高度度，默认为"0"
     * shdMp4Url	String	超清Mp4视频格式文件播放地址
     * downloadShdMp4Url	String	超清Mp4视频格式文件下载地址
     * shdMp4Size	Int	超清Mp4视频格式文件的大小（单位：字节）
     * shdMp4Width	String	超清Mp4视频宽度，默认为"0"
     * shdMp4Height	String	超清Mp4视频高度，默认为"0"
     * sdFlvUrl	String	标清Flv视频格式文件播放地址
     * downloadSdFlvUrl	String	标清Flv视频格式文件下载地址
     * sdFlvSize	Int	标清Flv视频格式文件的大小（单位：字节）
     * sdFlvWidth	String	标清Flv视频宽度，默认为"0"
     * sdFlvHeight	String	标清Flv视频高度，默认为"0"
     * hdFlvUrl	String	高清Flv视频格式文件播放地址
     * downloadHdFlvUrl	String	高清Flv视频格式文件下载地址
     * hdFlvSize	Int	高清Flv视频格式文件的大小（单位：字节）
     * hdFlvWidth	String	高清Flv视频宽度，默认为"0"
     * hdFlvHeight	String	高清Flv视频高度，默认为"0"
     * shdFlvUrl	String	超清Flv视频格式文件播放地址
     * downloadShdFlvUrl	String	超清Flv视频格式文件下载地址
     * shdFlvSize	Int	超清Flv视频格式文件的大小（单位：字节）
     * shdFlvWidth	String	超清Flv视频宽度，默认为"0"
     * shdFlvHeight	String	超清Flv视频高度，默认为"0"
     * sdHlsUrl	String	标清Hls视频格式文件播放地址
     * downloadSdHlsUrl	String	标清Hls视频格式文件下载地址
     * sdHlsSize	Int	标清Hls视频格式文件的大小（单位：字节）
     * sdHlsWidth	String	标清Hls视频宽度，默认为"0"
     * sdHlsHeight	String	标清Hls视频高度，默认为"0"
     * hdHlsUrl	String	高清Hls视频格式文件播放地址
     * downloadHdHlsUrl	String	高清Hls视频格式文件下载地址
     * hdHlsSize	Int	高清Hls视频格式文件的大小（单位：字节）
     * hdHlsWidth	String	高清Hls视频宽度，默认为"0"
     * hdHlsHeight	String	高清Hls视频高度，默认为"0"
     * shdHlsUrl	String	超清Hls视频格式文件播放地址
     * downloadShdHlsUrl	String	超清Hls视频格式文件下载地址
     * shdHlsSize	Int	超清Hls视频格式文件的大小（单位：字节）
     * shdHlsWidth	String	超清Hls视频宽度，默认为"0"
     * shdHlsHeight	String	超清Hls视频高度，默认为"0"
     * createTime	Int	视频上传时间（单位：毫秒）
     * updateTime	Int	视频更新时间（单位：毫秒）
     * msg	String	错误信息
     * */
    ResultVod getVideo(GetVideoVO vo);

    /**
     * 获取视频文件信息列表
     * @return
     * {
     *  "ret": {
     *      "pageSize": 2,
     *      "totalRecords": 28,
     *      "list": [
     *      {
     *          "typeName": "默认分类",
     *          "createTime": 1467007983618,
     *          "duration": 0,
     *          "origUrl": "http://vodk32ywxdf.vod.126.net/vodk32ywxdf/02a32b58-39fa-4d04-aeca-d7defa7e8972.jpg",
     *          "downloadOrigUrl": "http://vodk32ywxdf.nosdn.127.net/02a32b58-39fa-4d04-aeca-d7defa7e8972.jpg?NOSAccessKeyId=ab1856bb39044591939d7b94e1b8e5ee&Expires=1498558273&download=qwqwqw.jpg&Signature=0b5yMclktt%2FpDBQIZU8bLB6suouXLMfGZhqECFDp8%2Bw%3D",
     *          "status": 40,
     *          "updateTime": 1467007983618,
     *          "description": null,
     *          "snapshotUrl": null,
     *          "initialSize": 6354,
     *          "videoName": "qwqwqw",
     *          "typeId": 38,
     *          "completeTime": null,
     *          "vid": 39
     *      },
     *      {
     *          "typeName": "默认分类",
     *          "createTime": 1467007653656,
     *          "duration": 0,
     *          "origUrl": "http://vodk32ywxdf.vod.126.net/vodk32ywxdf/dcffdc0d-0735-41e1-8d30-d1d19450137f.jpg",
     *          "downloadOrigUrl": "http://vodk32ywxdf.nosdn.127.net/dcffdc0d-0735-41e1-8d30-d1d19450137f.jpg?NOSAccessKeyId=ab1856bb39044591939d7b94e1b8e5ee&Expires=1498558273&download=Chrysanthemum.jpg&Signature=oxa5%2F60xl8dmhtLoS21p97J%2BrnsRWFP12c30oA6Sh3o%3D",
     *          "status": 40,
     *          "updateTime": 1467007653656,
     *          "description": null,
     *          "snapshotUrl": null,
     *          "initialSize": 879394,
     *          "videoName": "Chrysanthemum",
     *          "typeId": 38,
     *          "completeTime": null,
     *          "vid": 38
     *      }
     *      ],
     *      "currentPage": 1,
     *      "pageNum": 14
     *  },
     *  "code": 200
     * }
     *
     * */
    ResultVod listVideo(ListVideoVO vo);


    /**
     *  视频文件信息编辑
     *
     * @return
     * {
     *  "ret": {},
     *  "code": 200
     * }
     * */
    ResultVod editVideo(EditVideoVO vo);


    /**
     * 删除单个转码输出视频
     *
     * @return
     * {
     *  "ret": {},
     *  "code": 200
     * }
     *
     * */
    ResultVod deleteSingleVideo(DeleteSingleVideoVO vo);

    /**
     * 删除视频文件
     * @return
     * {
     *  "ret": {},
     *  "code": 200
     * }
     * */
    ResultVod deleteVideo(DeleteVideoVO vo);

    /**
     * 视频屏蔽
     * @return
     * {
     *  "ret": {},
     *  "code": 200
     * }
     * */
    ResultVod disableVideo(DisableVideoVO vo);

    /**
     * 视频恢复
     * @return
     * {
     *  "ret": {},
     *  "code": 200
     * }
     * */
    ResultVod recoverVideo(RecoverVideoVO vo);

    /**
     * 批量删除视频
     * @return
     * {
     *  "code" : 724,
     *  "ret" : {
     *      "failedVids" : [23,24]
     *  }
     * }
     * */
    ResultVod deleteBatchVideo(DeleteBatchVideoVO vo);


    /**
     * 文件上传初始化
     * 用于文件上传的初始化，获取xNosToken（上传凭证）、bucket（存储对象的桶名）、object（生成的唯一对象名）。
     * @return
     * {
     * "code" : 200,
     * "ret" : {
     * "xNosToken" : "UPLOAD ab1856bb39044591939d7b94e1b8e5ee:n5VKrOLVFkLM7JIBDaCyPjzVKswqfxBZak2U2fXh0xM=:eyJCdWNrZXQiOiJ2b2RrMzJ5d3hkZiIsIk9iamVjdCI6ImEzM2U1MmQ3LTUxZGMtNDZhNS1iMTFlLWFiNmMyMmJiOTk2ZC5KUEciLCJFeHBpcmVzIjoxNDY5NTg3NTc5LCJDYWxsYmFja1VybCI6Imh0dHA6Ly8xMDYuMi40NC4yNDgveGhyL3ZvZC9ub3MvY2FsbGJhY2siLCJDYWxsYmFja0JvZHkiOiJmaWxlTmFtZT10ZXN0LkpQRyZvYmplY3ROYW1lPWEzM2U1MmQ3LTUxZGMtNDZhNS1iMTFlLWFiNmMyMmJiOTk2ZC5KUEcmJChPYmplY3RTaXplKSZ1aWQ9MjQxMzMmdHlwZUlkPTAmcHJlc2V0SWQ9MCZ3YXRlcm1hcmtJZHM9bnVsbCZkZXNjcmlwdGlvbj1udWxsJnZpZGVvQ2FsbGJhY2s9bnVsbCYkKEFWaW5mby5WaWRlby5EdXJhdGlvbikmJChBVmluZm8uVmlkZW8uSGVpZ2h0KSYkKEFWaW5mby5WaWRlby5XaWR0aCkmMCYwJjAmbnVsbCZ1cGxvYWRTdGFydD0xNDY2OTk1NTc5NjU4In0=",
     * "bucket" : "vodk32ywxdf",
     * "object" : "d37906a7-0119-4dd9-820d-4c66a71952ad.mp4"
     * }
     * }
     *
     * code	Int	状态码
     * xNosToken	String	上传凭证
     * bucket	String	存储上传文件的桶名
     * object	String	存储上传文件的对象名
     * msg	String	错误信息
     *
     * */
    ResultVod initUpload(InitUploadVO vo);



    /**
     * 获取上传加速节点地址
     * 用于获取上传加速节点地址。
     *
     * @return
     *
     * {"lbs":"http://223.252.196.39/lbs","upload"：["http://223.252.216.48","http://223.252.216.49"]}
     *
     * */
    GetLbsReturnVO getLbs(String bucket);




    void uploadFile(UploadFileVO vo, InputStream inputStream, Long size);

    /**
     * 上传完成根据对象名查询视频或水印图片主Id
     *
     * @return
     *
     * {
     *  "code" : 200,
     *  "ret" : {
     *      list : [
     *      {
     *          "objectName" : "33cf71b1-86ac-4555-a071-d70db07b9685.mp4",
     *          "vid" : 1008
     *      }
     *      ]
     *  }
     * }
     *
     * */
    ResultVod queryVideo(QueryVideoVO vo);

    /**
     * 获取视频截图地址
     * 针对具体文件，获取其不同尺寸下的截图URL地址。支持截图的文件格式：
     * mpegts|mov,mp4,m4a,3gp,3g2,mj2|rm|asf|flv|avi|matroska,webm|mpeg|mpeg|
     * mpegtsraw|mpegvideo|mtv|mv|mvi|nuv|3gp|avs|avsvideo|adxl|dv|dvd|flic|flac|h261|
     * h263|h264|hls|m4v|mjpeg|rawvideo|rpl|avcd|truehd|vc-1|vcd。
     * 如有不支持格式视频，请进行转码，接口会根据转码后的视频截取图片。
     * 截取图片比例如果和视频尺寸比例不相符合，会自动填充黑边，保证图片显示正常。
     *
     * @return
     * {
     *  "ret" : {
     *      "url" : "http://vodk32ywxdf.nosdn.127.net/fsfs-gdgs-gdgd-gdgds_snapshot_0_640.jpg",
     *      "width" : "640",
     *      "height" : "320"
     *  },
     *  "code" : 200
     * }
     *
     * */
    ResultVod createSnapshot(CreateSnapshotVO vo);


    /**
     * 设置视频封面
     *
     * @return
     * {
     *  "ret" : {
     *      "url" : "http://vodk32ywxdf.nosdn.127.net/fsfs-gdgs-gdgd-gdgds_snapshot_0_640.jpg"
     *  },
     *  "code" : 200
     * }
     *
     * **/
    ResultVod setSnapshot(SetSnapshotVO vo);


    /**
     * 视频文件转码
     *
     * 用于视频转码，批量进行视频转码（只有处于正常或失败的视频才允许执行此操作）。
     *
     * @return
     * {
     * "ret": {
     * "passCount": 0,
     * "failCount": 0,
     * "successCount": 2
     * },
     * "code": 200
     * }
     *
     * code	Int	状态码
     * successCount	Int	转码成功的数量
     * failCount	Int	转码失败的数量
     * passCount	Int	转码忽略的数量，即已经存在该转码格式视频
     * msg	String	错误信息
     * **/
    ResultVod resetmultiTranscode(ResetMultiTranscodeVO vo);

    /**
     * 设置上传回调地址
     * @return
     * {
     *  "msg" : "",
     *  "code" : 200
     * }
     *
     * 注：上传完成后回调给客户地址为POST请求，包体为json格式数据，
     * 类似：{"name":"snow.mp4","origAddr":"http://vodk32ywxdf.vod.126.net/vodk32ywxdf/b3d259f4-a7bc-4119-ae24-60e0eb09216e.mp4",
     * "type":"upload","vid":1022,"user_defined":"userId=123456"}
     * **/
    ResultVod setCallbackUpload(SetCallbackUploadVO vo);

    /**
     * 设置转码回调地址
     * @return
     * {
     * "msg" : "",
     * "code" : 200
     * }
     *
     * 注：转码完成后回调给客户地址为POST请求，包体为json格式数据，
     * 类似：{"sdFlvAddr":"http://vodk32ywxdf.vod.126.net/vodk32ywxdf/flv/rDM5V7HM_1021_sd.flv",
     * "name":"snow.mp4","type":"transcode","vid":1021,"user_defined":"userId=123456"}
     * */
    ResultVod setCallbackTrancode(SetCallbackTranscodeVO vo);

    /**
     * 流量统计查询
     * @return
     * {
     *  "ret" : {
     *      "startDate" : 1459478685000,
     *      "endDate" : 1459478685000,
     *      "totalFlow" : 1234.23,
     *      "type" : 2,
     *      "flowList" : [
     *      {
     *          "begintime" : 1459478685000,
     *          "endtime" : 1459478685,
     *          "flow" : 123.21
     *      },
     *      ...
     *      ],
     *      "currentPage" : 1,
     *      "pageSize" : 10,
     *      "pageNum" : 2,
     *      "totalRecords" : 18
     *  },
     *  "code" : 200
     * }
     *
     * code	Int	状态码
     * totalFlow	Double	查询时段的总流量（单位：MB）
     * startDate	Int	需要查询的开始时间戳（单位：毫秒）
     * endDate	Int	需要查询的结束时间戳（单位：毫秒）
     * currentPage	Int	当前取得页数
     * pageSize	Int	一页的记录数目
     * pageNum	Int	总页数
     * totalRecords	Int	获取流量数据的数目
     * list	List	流量数据列表
     * list.begintime	Int	起始时间（单位：毫秒）
     * list.endtime	Int	结束时间（单位：毫秒）
     * list.flow	Double	流量值（单位：MB）
     * msg	String	错误信息
     * */
    ResultVod flowStats(FlowStatsVO vo);

    /**
     * 带宽统计查询
     * @return
     * {
     *  "ret" : {
     *      "startDate" : 1459478685000,
     *      "endDate" : 1459478685000,
     *      "maxBand" : 1234.23,
     *      "type" : 2,
     *      "bandList" : [
     *      {
     *          "begintime" : 1459478685000,
     *          "endtime" : 1459478685,
     *          "band" : 123.21
     *      },
     *      ...
     *      ],
     *      "currentPage" : 1,
     *      "pageSize" : 10,
     *      "pageNum" : 2,
     *      "totalRecords" : 18
     *  },
     *  "code" : 200
     * }
     *
     * code	Int	状态码
     * maxBand	Double	查询时段的最大带宽值（单位：Mb/s）
     * startDate	Int	需要查询的开始时间戳（单位：毫秒）
     * endDate	Int	需要查询的结束时间戳（单位：毫秒）
     * currentPage	Int	当前取得页数
     * pageSize	Int	一页的记录数目
     * pageNum	Int	总页数
     * totalRecords	Int	获取带宽数据的数目
     * bandList	List	获取的带宽列表
     * bandList.begintime	Int	起始时间（单位：毫秒）
     * bandList.endtime	Int	结束时间（单位：毫秒）
     * bandList.band	Double	带宽值（单位：Mb/s）
     * msg	String	错误信息
     * */
    ResultVod bandStats(BandStatsVO vo);

    /**
     * 存储统计查询
     * @return
     * {
     *  "ret" : {
     *      "startDate" : 1459478685000,
     *      "endDate" : 1459478685000,
     *      "totalStorage" : 1234.23,
     *      "type" : 2,
     *      "storageList" : [
     *      {
     *          "begintime" : 1459478685000,
     *          "endtime" : 1459478685,
     *          "storage" : 123.21
     *      },
     *      ...
     *      ],
     *      "currentPage" : 1,
     *      "pageSize" : 10,
     *      "pageNum" : 2,
     *      "totalRecords" : 18
     *  },
     *  "code" : 200
     * }
     *
     * */
    ResultVod storageStats(StorageStatsVO vo);
}
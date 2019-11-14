package cn.com.myproject.api.live.controller;


import cn.com.myproject.api.service.ISearchService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.live.entity.PO.Course;
import cn.com.myproject.user.entity.VO.APITearcherUser;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LeiJia  2017/08/28
 */
@RestController
@RequestMapping("/api/search")
@Api(value="教头详情、教头动态、教头作品、教头问答",tags = "教头")
public class TeacherController {


}

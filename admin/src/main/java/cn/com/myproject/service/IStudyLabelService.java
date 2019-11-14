package cn.com.myproject.service;

import cn.com.myproject.user.entity.PO.StudyLabel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Created by JYP on 2017/8/22 0022.
 */
@FeignClient(name = "admin-api-provide", url = "${feignclient.url}")
public interface IStudyLabelService {

    @GetMapping("/stu_label/all_label")
    List<StudyLabel> selectAllLable();
}

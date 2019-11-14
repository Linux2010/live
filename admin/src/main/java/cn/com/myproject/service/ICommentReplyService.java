package cn.com.myproject.service;

import cn.com.myproject.live.entity.PO.CommentReply;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by Administrator on 2017/8/15 0015.
 */
@FeignClient(name = "admin-api-provide", url = "${feignclient.url}")
public interface ICommentReplyService {

    @PostMapping("/reply/delreplay")
    void delreplay(@RequestBody String commid);

    @PostMapping("/reply/addreply")
    void addreply(@RequestBody CommentReply commentReply);
}

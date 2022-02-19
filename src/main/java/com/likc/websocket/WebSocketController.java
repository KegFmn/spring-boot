package com.likc.websocket;

import com.likc.common.lang.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Author: likc
 * @Date: 2022/02/19/20:36
 * @Description: websocket控制层
 */
@RestController
public class WebSocketController {

    /**
     *  单个推送接口
     * @param cid
     * @param message
     * @return
     */
    @RequestMapping("/socket/push/{cid}")
    public Result pushToOne(@PathVariable String cid, String message) throws IOException {
        WebSocketServer.sendInfo(message, cid);
        return new Result(200, "推送成功");
    }

    /**
     *  群发推送接口
     * @param message
     * @return
     */
    @RequestMapping("/socket/push")
    public Result pushToAll(String message) throws IOException {
        WebSocketServer.sendAll(message);
        return new Result(200, "推送成功");
    }
}

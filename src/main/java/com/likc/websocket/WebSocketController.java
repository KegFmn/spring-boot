package com.likc.websocket;

import com.likc.common.lang.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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
            return Result.succ(null);
    }

    /**
     *  群发推送接口
     * @param message
     * @return
     */
    @RequestMapping("/socket/push")
    public Result pushToAll(String message) throws IOException {
        WebSocketServer.sendAll(message);
        return Result.succ(null);
    }
}

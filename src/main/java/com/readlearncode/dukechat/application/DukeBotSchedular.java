package com.readlearncode.dukechat.application;


import com.readlearncode.dukechat.utils.Messages;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import java.util.logging.Logger;

/**
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
@Stateless
class DukeBotSchedular {

    @Schedule(minute = "*/20", hour = "*")
    private void interrupt() {
        ChatServerEndpoint.getRooms().forEach((s, room) -> room.sendMessage(Messages.objectify("Hello from Duke Bot")));
    }

}

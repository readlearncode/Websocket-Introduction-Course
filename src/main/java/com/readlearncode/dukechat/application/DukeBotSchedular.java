package com.readlearncode.dukechat.application;


import com.readlearncode.dukechat.utils.Messages;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import java.util.logging.Logger;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
@Stateless
class DukeBotSchedular {

    private static final Logger logger = Logger.getLogger(DukeBotSchedular.class.getName());

    @Schedule(minute = "*/1", hour = "*")
    private void interrupt() {
        ChatServerEndpoint.getRooms().forEach((s, room) -> room.sendMessage(Messages.objectify("Hello from Duke Bot")));
    }

    // TODO: send random message from selection of messages.


}

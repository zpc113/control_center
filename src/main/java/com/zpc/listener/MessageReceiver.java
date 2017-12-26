package com.zpc.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;
import java.nio.charset.Charset;

/**
 * Created by 和谐社会人人有责 on 2017/11/29.
 */
@Component
public class MessageReceiver implements MessageListener {

    public void onMessage(Message message) {
        System.out.println(new String(message.getBody() , Charset.forName("UTF-8")));
        System.out.println(new String(message.getBody() , Charset.forName("UTF-8")));
        System.out.println(new String(message.getBody() , Charset.forName("UTF-8")));
        System.out.println(new String(message.getBody() , Charset.forName("UTF-8")));
        System.out.println(new String(message.getBody() , Charset.forName("UTF-8")));
        System.out.println(new String(message.getBody() , Charset.forName("UTF-8")));
        System.out.println(new String(message.getBody() , Charset.forName("UTF-8")));
        System.out.println(new String(message.getBody() , Charset.forName("UTF-8")));
        System.out.println(new String(message.getBody() , Charset.forName("UTF-8")));
        System.out.println(new String(message.getBody() , Charset.forName("UTF-8")));
        System.out.println(new String(message.getBody() , Charset.forName("UTF-8")));
        System.out.println(new String(message.getBody() , Charset.forName("UTF-8")));
        System.out.println(new String(message.getBody() , Charset.forName("UTF-8")));
        System.out.println(new String(message.getBody() , Charset.forName("UTF-8")));
        System.out.println(new String(message.getBody() , Charset.forName("UTF-8")));
        System.out.println(new String(message.getBody() , Charset.forName("UTF-8")));
        System.out.println(new String(message.getBody() , Charset.forName("UTF-8")));
    }

}

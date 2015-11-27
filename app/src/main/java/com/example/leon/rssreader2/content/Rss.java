package com.example.leon.rssreader2.content;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Leon on 20.11.2015.
 */
@Root(name = "rss", strict = false)
public class Rss {

    @Element(name = "channel")
    Channel mChannel;

    public Channel getChannel() {
        return mChannel;
    }
}

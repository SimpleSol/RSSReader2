package com.example.leon.rssreader2.content;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Leon on 18.11.2015.
 */
@Root(name = "channel")
public class Channel {

    @Element(name = "title")
    private String mTitle;

    @Element(name = "link")
    private String mLink;

    @ElementList(name = "item", inline = true)
    private List<News> mNews;

}

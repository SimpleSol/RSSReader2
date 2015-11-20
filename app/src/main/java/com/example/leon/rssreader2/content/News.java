package com.example.leon.rssreader2.content;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Leon on 20.11.2015.
 */
@Root (name = "item")
public class News {

    @Element(name = "title")
    private String mTitle;

    @Element(name = "link")
    private String mLink;

    @Element(name = "pubDate")
    private String mPubDate;

}

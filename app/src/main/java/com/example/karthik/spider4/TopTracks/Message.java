package com.example.karthik.spider4.TopTracks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by karthik on 25-06-2018.
 */
public class Message {


    @SerializedName("body")
    @Expose
    private Body body;

    @SerializedName("header")
    @Expose
    private Header header;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }


    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

}

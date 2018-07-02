package com.example.karthik.spider4.TopTracks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by karthik on 25-06-2018.
 */

public class TrackListResponse {


        @SerializedName("message")
        @Expose
        private Message message;

        public Message getMessage() {
            return message;
        }

        public void setMessage(Message message) {
            this.message = message;
        }


}

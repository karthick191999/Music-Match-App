package com.example.karthik.spider4.ArtistGet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by karthik on 30-06-2018.
 */

public class Artistget {







        @SerializedName("message")
        @Expose
        private com.example.karthik.spider4.ArtistGet.Message message;

        public com.example.karthik.spider4.ArtistGet.Message getMessage() {
            return message;
        }

        public void setMessage(com.example.karthik.spider4.ArtistGet.Message message) {
            this.message = message;
        }




}

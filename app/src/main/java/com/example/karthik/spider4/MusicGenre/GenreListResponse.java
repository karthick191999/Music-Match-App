package com.example.karthik.spider4.MusicGenre;

import com.example.karthik.spider4.MusicGenre.Message;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by karthik on 26-06-2018.
 */

public class GenreListResponse {

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

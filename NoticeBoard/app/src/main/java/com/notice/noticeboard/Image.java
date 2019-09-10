package com.notice.noticeboard;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Image {
    public String key;
    public String userId;
    public String downloadUrl;
   // public String notices;

    // these properties will not be saved to the database
    @Exclude
    public User user;

    @Exclude
    public int likes = 0;

    @Exclude
    public boolean hasLiked = false;

    @Exclude
    public String userLike;

    public Image() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Image(String key, String userId, String downloadUrl) {
        this.key = key;
        this.userId = userId;
        this.downloadUrl = downloadUrl;
      //  this.notices = notices;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

   /* public String getNotices() {
        return notices;
    }*/

    public void addLike() {
        this.likes++;
    }

    public void removeLike() {
        this.likes--;
    }
}

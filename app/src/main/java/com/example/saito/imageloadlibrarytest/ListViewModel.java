package com.example.saito.imageloadlibrarytest;

import android.provider.ContactsContract;
import android.view.LayoutInflater;

/**
 * Created by saito on 2016/03/14.
 */
public class ListViewModel {
    private static final String TAG = ContactsContract.Profile.class.getSimpleName();
    private String name;
    private String hobby;
    private int thumbnailId;
    private String thumbnailUrl;

    LIBRARY_FLG libraryFlg;

    enum LIBRARY_FLG {
        PICASSO,
        GLIDE,
        FRESCO,
    }

    public ListViewModel(String name, String hobby, int thumbnailId, LIBRARY_FLG libraryFlg) {
        this.name = name;
        this.hobby = hobby;
        this.thumbnailId = thumbnailId;
        this.libraryFlg = libraryFlg;
    }
    public ListViewModel(String name, String hobby, String thumbnailUrl, LIBRARY_FLG libraryFlg) {
        this.name = name;
        this.hobby = hobby;
        this.thumbnailUrl = thumbnailUrl;
        this.libraryFlg = libraryFlg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public int getThumbnailId() {
        return thumbnailId;
    }

    public void setThumbnailId(int thumbnailId) {
        this.thumbnailId = thumbnailId;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
    public LIBRARY_FLG getLibraryFlg() {
        return libraryFlg;
    }

    public void setLibraryFlg(LIBRARY_FLG libraryFlg) {
        this.libraryFlg = libraryFlg;
    }

}

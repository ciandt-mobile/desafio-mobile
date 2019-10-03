package com.pereira.tiago.desafio.mobile.databasemodels;

import org.greenrobot.greendao.annotation.Entity;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class CastMovie implements Serializable {

    private final static long serialVersionUID = -4441501032846397316L;
    
    private int castId;
    private String character;
    private String name;
    private String profilePath;
    @Generated(hash = 1943938404)
    public CastMovie(int castId, String character, String name,
            String profilePath) {
        this.castId = castId;
        this.character = character;
        this.name = name;
        this.profilePath = profilePath;
    }
    @Generated(hash = 2103388771)
    public CastMovie() {
    }
    public int getCastId() {
        return this.castId;
    }
    public void setCastId(int castId) {
        this.castId = castId;
    }
    public String getCharacter() {
        return this.character;
    }
    public void setCharacter(String character) {
        this.character = character;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getProfilePath() {
        return this.profilePath;
    }
    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }
}

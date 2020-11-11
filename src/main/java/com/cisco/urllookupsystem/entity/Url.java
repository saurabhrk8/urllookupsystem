package com.cisco.urllookupsystem.entity;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Url implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(unique = true)
    private String hostName;

    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn
    private List<UrlProfile> urlProfileList;

    public Url() {
    }

    public Url(@NonNull String hostName,List<UrlProfile>urlProfileList) {
        this.hostName = hostName;
        this.urlProfileList=urlProfileList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getHostName() {
        return hostName;
    }

    public void setHostName(@NonNull String hostName) {
        this.hostName = hostName;
    }

    public List<UrlProfile> getUrlProfileList() {
        return urlProfileList;
    }

    public void setUrlProfileList(List<UrlProfile> urlProfileList) {
        this.urlProfileList = urlProfileList;
    }
}

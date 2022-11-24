package org.ppke.itk.listen.model;

import lombok.Data;

@Data
public class NewUserTrack {

    private String name;
    private String pictureUrl;
    private String audioUrl;
    private Long ownerUserId;
}

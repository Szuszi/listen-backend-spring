package org.ppke.itk.listen.domain.usertrack.data;

import org.ppke.itk.listen.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
// import lombok.EqualsAndHashCode;

@Data
// @EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
public class UserTrack extends BaseEntity {
    private Integer id;
    private String name;
    private String pictureUrl;
    private String audioUrl;

    //owner userId

    //Favorites 
}

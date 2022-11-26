package org.ppke.itk.listen.domain.usertrack.data;

import org.ppke.itk.listen.domain.BaseEntity;
import org.ppke.itk.listen.domain.user.data.User;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="user_tracks")
public class UserTrack extends BaseEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "picture_url")
    private String pictureUrl;

    @Column(name = "audio_url")
    private String audioUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="owner_user_id")
    private User ownerUser;

    //Favorites 
}

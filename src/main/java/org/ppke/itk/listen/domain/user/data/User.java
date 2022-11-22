package org.ppke.itk.listen.domain.user.data;

import org.ppke.itk.listen.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Column(name = "avatar_url")
    private String avatarUrl;

    //Owned UserTraccks

    //Favorited UserTracks

    //Followed Users

    //Followers
}


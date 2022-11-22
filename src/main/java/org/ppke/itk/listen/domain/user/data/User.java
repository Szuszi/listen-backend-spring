package org.ppke.itk.listen.domain.user.data;

import org.ppke.itk.listen.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany
    @JoinTable(
        name = "user_followings", 
        joinColumns = @JoinColumn(name = "follower_user_id"), 
        inverseJoinColumns = @JoinColumn(name = "followed_user_id"))
    @Builder.Default
    @JsonIgnore
    private List<User> followedUsers = new ArrayList<>();

    @ManyToMany(mappedBy = "followedUsers")
    @Builder.Default
    @JsonIgnore
    private List<User> followerUsers = new ArrayList<>();
}


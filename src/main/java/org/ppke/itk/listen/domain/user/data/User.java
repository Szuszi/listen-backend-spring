package org.ppke.itk.listen.domain.user.data;

import org.ppke.itk.listen.domain.BaseEntity;
import org.ppke.itk.listen.domain.usertrack.data.UserTrack;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy ="ownerUser", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserTrack> userTracks;

    //Favorited UserTracks

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_followings", 
        joinColumns = @JoinColumn(name = "follower_user_id"), 
        inverseJoinColumns = @JoinColumn(name = "followed_user_id"))
    @Builder.Default
    @JsonIgnore
    private List<User> followedUsers = new ArrayList<>();

    @ManyToMany(mappedBy = "followedUsers", fetch = FetchType.LAZY)
    @Builder.Default
    @JsonIgnore
    private List<User> followerUsers = new ArrayList<>();
}


package com.flash.hairmagic01.utils.set;

import java.util.Set;

/**
 * 功能：
 * 作者：whitebuless
 * 日期：2024/5/3 23:20
 */
public class FollowersResponse {
    private Set<Integer> followers;

    public FollowersResponse(Set<Integer> followers) {
        this.followers = followers;
    }

    public Set<Integer> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<Integer> followers) {
        this.followers = followers;
    }
}
package com.tjc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @Author: TJC
 * @Date: 2020/6/16 15:19
 * @description: TODO
 */

public class SecurityUser extends User implements UserDetails {

    public SecurityUser(User user) {
        this.setId(user.getId());
        this.setEmail(user.getEmail());
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setSex(user.getSex());
        this.setCoin(user.getCoin());
        this.setCreateTime(user.getCreateTime());
        this.setLocation(user.getLocation());
        this.setIntroduction(user.getIntroduction());
        this.setLevel(user.getLevel());
        this.setAvatar(user.getAvatar());
        this.setQq(user.getQq());
        this.setWeibo(user.getWeibo());
        this.setReplyNum(user.getReplyNum());
        this.setRoles(user.getRoles());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
//        String username = this.getUsername();
        if (this.getRoles() != null) {
            for (Role role : this.getRoles()) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role.getRoleName());
                authorities.add(authority);
            }
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

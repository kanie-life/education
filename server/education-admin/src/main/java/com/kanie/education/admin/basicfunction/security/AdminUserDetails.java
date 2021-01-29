package com.kanie.education.admin.basicfunction.security;

import com.kanie.education.admin.basicfunction.po.UmResource;
import com.kanie.education.admin.basicfunction.po.UmUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SpringSecurity的用户信息
 *
 * @author ：kanie
 * @date ：Created in 2020/11/13 14:35
 */
public class AdminUserDetails implements UserDetails {

    private UmUser umUser;

    private List<UmResource> resourceList;

    public AdminUserDetails(UmUser umUser, List<UmResource> resourceList) {
        this.umUser = umUser;
        this.resourceList = resourceList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的角色
        return resourceList.stream()
                .map(role -> new SimpleGrantedAuthority(role.getResourceId() + ":" + role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return umUser.getPassword();
    }

    @Override
    public String getUsername() {
        return umUser.getLoginAccount();
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
        return umUser.getStatus().equals("Y");
    }
}

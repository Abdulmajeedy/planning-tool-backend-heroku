package com.hmy.planning.systemconfiguration.models;

import java.util.List;

public class myUserDetails implements UserDetails {

    private static final long serialVersionUID = 275347623L;
    private int id;
    private String username;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;
    private String company;
    private String department;

    public UUID getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UUID userProfile) {
        this.userProfile = userProfile;
    }

    private UUID userProfile;

public MyUserDetails(User user) {
this.id = user.getId();
this.username = user.getUsername();
this.password = user.getPassword();
this.active = user.isActive();

//new fields addition
this.company=user.getCompany();
this.department=user.getDepartment();
this.userProfile=getUserProfile();
this.authorities = Arrays.stream(user.getRoles().split(","))
.map(SimpleGrantedAuthority::new)
.collect(Collectors.toList());
}

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

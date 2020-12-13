package boss.xtrain.security.domain;

import java.util.Set;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-13
 * @since
 */
public class BesUserInfo {
    private Long userId;
    private String companyId;
    private String avatar;
    private String userName;
    private Set<String> orgIds;
    private Set<String> roles;
    private Set<String> resourcesPermission;

    public BesUserInfo(Long userId, String userName, Set<String> roles, Set<String> resourcesPermission) {
        this.userId = userId;
        this.userName = userName;
        this.roles = roles;
        this.resourcesPermission = resourcesPermission;
    }

    public BesUserInfo(Long userId, String avatar, String userName, Set<String> roles, Set<String> resourcesPermission) {
        this.userId = userId;
        this.avatar = avatar;
        this.userName = userName;
        this.roles = roles;
        this.resourcesPermission = resourcesPermission;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getResourcesPermission() {
        return resourcesPermission;
    }

    public void setResourcesPermission(Set<String> resourcesPermission) {
        this.resourcesPermission = resourcesPermission;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Set<String> getOrgIds() {
        return orgIds;
    }

    public void setOrgIds(Set<String> orgIds) {
        this.orgIds = orgIds;
    }

    @Override
    public String toString() {
        return "BesUserInfo{" +
                "userId=" + userId +
                ", avatar='" + avatar + '\'' +
                ", userName='" + userName + '\'' +
                ", roles=" + roles +
                ", resourcesPermission=" + resourcesPermission +
                '}';
    }
}

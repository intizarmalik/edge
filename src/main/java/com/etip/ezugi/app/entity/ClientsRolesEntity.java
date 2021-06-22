package com.etip.ezugi.app.entity;

import com.etip.ezugi.app.models.ClientsRolesModel;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document(collection = "clientRoles")
public class ClientsRolesEntity {
    @NotBlank
    private String roleId;
    @NotBlank
    private ClientsRolesModel roleName;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public ClientsRolesModel getRoleName() {
        return roleName;
    }

    public void setRoleName(ClientsRolesModel roleName) {
        this.roleName = roleName;
    }
}

package pl.atipera.task.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class RepositoryDTO {
    private String repositoryName;
    private String ownerLogin;
    private List<RepoBranchesDTO> branches;

    @Override
    public String toString() {
        return "RepositoryDTO{" +
                "repositoryName='" + repositoryName + '\'' +
                ", ownerLogin='" + ownerLogin + '\'' +
                ", branches=" + branches +
                '}';
    }
}

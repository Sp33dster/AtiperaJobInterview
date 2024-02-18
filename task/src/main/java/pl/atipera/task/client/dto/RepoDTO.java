package pl.atipera.task.client.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class RepoDTO {
    private String name;
    private OwnerDTO owner;
    private Boolean fork;
    private List<BranchesDTO> branchesDTOList;
}

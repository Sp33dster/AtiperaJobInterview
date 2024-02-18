package pl.atipera.task.client.dto;

import lombok.Getter;

@Getter
public class BranchesDTO {
    private String name;
    private CommitBranchDTO commit;
}

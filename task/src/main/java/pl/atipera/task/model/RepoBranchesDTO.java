package pl.atipera.task.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class RepoBranchesDTO {
    private String name;
    private String sha;

    @Override
    public String toString() {
        return "RepoBranchesDTO{" +
                "name='" + name + '\'' +
                ", sha='" + sha + '\'' +
                '}';
    }
}

package pl.atipera.task.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.atipera.task.client.dto.BranchesDTO;
import pl.atipera.task.client.dto.RepoDTO;
import pl.atipera.task.error.ExceptionMessage;
import pl.atipera.task.model.RepoBranchesDTO;
import pl.atipera.task.model.RepositoryDTO;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class UserRepoFinderClient {

    private static final String GITHUB_API_URL = "https://api.github.com/users/{username}/repos";
    private static final String GITHUB_BRANCHE_API_URL = "https://api.github.com/repos/{owner}/{repositoryName}/branches";
        private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();
    private String acceptHeader = "application/json";

    public ResponseEntity<Object> getUserRepo(String username, String bearerToken) {

        headers.set("Accept", acceptHeader);
        headers.setBearerAuth(bearerToken);

        List<RepositoryDTO> userReposDTO = new ArrayList<>();

        try {
            ResponseEntity<RepoDTO[]> userRepoDTO = restTemplate.getForEntity(GITHUB_API_URL, RepoDTO[].class, username);

            for (RepoDTO repoDTO : userRepoDTO.getBody()) {
                if (!repoDTO.getFork()) {
                    RepositoryDTO repository = new RepositoryDTO();
                    repository.setRepositoryName(repoDTO.getName());
                    repository.setOwnerLogin(repoDTO.getOwner().getLogin());

                    BranchesDTO[] branchesDTOS = restTemplate.getForObject(GITHUB_BRANCHE_API_URL, BranchesDTO[].class, repository.getOwnerLogin(), repository.getRepositoryName());
                    List<RepoBranchesDTO> branchesDTO = new ArrayList<>();
                    for (BranchesDTO branchDTO : branchesDTOS) {
                        RepoBranchesDTO repoBranchesDTO = new RepoBranchesDTO();
                        repoBranchesDTO.setName(branchDTO.getName());
                        repoBranchesDTO.setSha(branchDTO.getCommit().getSha());
                        branchesDTO.add(repoBranchesDTO);
                    }
                    repository.setBranches(branchesDTO);
                    userReposDTO.add(repository);
                }
            }
            return new ResponseEntity<>(userReposDTO, HttpStatus.OK);
        } catch (HttpClientErrorException ex) {
            return new ResponseEntity<>(new ExceptionMessage(ex.getStatusCode().value(), ex.getStatusText()), ex.getStatusCode());
        }
    }
}
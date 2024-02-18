package pl.atipera.task.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.atipera.task.client.UserRepoFinderClient;
import pl.atipera.task.model.RepositoryDTO;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class RepoFinderService {

    private final UserRepoFinderClient userRepoFinderClient;
    public ResponseEntity<Object> getUserRepo(String username, String bearerToken)  {
        return userRepoFinderClient.getUserRepo(username, bearerToken);
    }
}

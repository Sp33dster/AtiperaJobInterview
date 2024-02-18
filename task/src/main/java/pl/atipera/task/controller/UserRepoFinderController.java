package pl.atipera.task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.atipera.task.model.RepositoryDTO;
import pl.atipera.task.service.RepoFinderService;
import java.util.List;
@RestController
@RequestMapping(path = "/api", produces = "application/json")
@RequiredArgsConstructor
public class UserRepoFinderController {

    private final RepoFinderService repoFinderService;

    @GetMapping("/repo")
    public ResponseEntity<Object> getUserListOfRepositories(@RequestParam(value = "username") String username, @RequestParam(value = "bearerToken") String bearerToken) throws Exception {
        return repoFinderService.getUserRepo(username, bearerToken);
    }
}

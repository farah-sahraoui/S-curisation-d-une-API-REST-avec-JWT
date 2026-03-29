package ma.fst.security.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class TestController {
    @GetMapping("/user/profile")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String getUserProfile(Principal principal) {
        return "Bienvenue, " + principal.getName() + ". Voici votre profil utilisateur.";
    }

    @GetMapping("/admin/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAdminDashboard(Principal principal) {
        return "Tableau de bord ADMIN. Bonjour, " + principal.getName() + ".";
    }
}
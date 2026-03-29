package ma.fst.security.web;

import ma.fst.security.jwt.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtTokenProvider;
    public AuthController(AuthenticationManager authManager,
                          UserDetailsService userDetailsService,
                          JwtUtil jwtTokenProvider) {
        this.authManager = authManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    @PostMapping("/login")
    public Map<String, Object> authenticateUser(@RequestBody Map<String, String> loginRequest) {
        String userName = loginRequest.get("username");
        String passWord = loginRequest.get("password");

        authManager.authenticate(new UsernamePasswordAuthenticationToken(userName, passWord));

        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

        String jwt = jwtTokenProvider.generateToken(userDetails.getUsername());

        Map<String, Object> response = new HashMap<>();
        response.put("accessToken", jwt);
        response.put("username", userName);
        response.put("tokenType", "Bearer");

        return response;
    }
}
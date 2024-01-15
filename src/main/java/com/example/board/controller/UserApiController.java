package com.example.board.controller;

import com.example.board.config.auth.PrincipalDetailService;
import com.example.board.dto.ResponseDto;
import com.example.board.model.User;
import com.example.board.service.UserService;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;
    private final PrincipalDetailService principalDetailService;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Boolean> save(@RequestBody User user) {
        userService.signUp(user);
        return new ResponseDto<>(HttpStatus.OK, true);
    }

    @PutMapping("/user")
    public ResponseDto<Boolean> update(@RequestBody User user, HttpSession session) {
        userService.updateUserInfo(user);
        UserDetails currUserDetails = principalDetailService.loadUserByUsername(user.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(currUserDetails,
                currUserDetails.getPassword(), currUserDetails.getAuthorities());
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
        return new ResponseDto<>(HttpStatus.OK, true);
    }

    @GetMapping("/auth/username/{username}")
    public ResponseDto<Boolean> checkUserName(@PathVariable String username) {
        ResponseDto<Boolean> response = new ResponseDto<>(HttpStatus.OK, true);
        User selectedUser = userService.findUser(username);
        if (selectedUser.getUsername() != null) {
            response = new ResponseDto<>(HttpStatus.OK, false);
        }
        return response;
    }
}

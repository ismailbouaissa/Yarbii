package com.example.demo.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.DTO.JwtResponseDTO;
import com.example.demo.DTO.LoginRequestDTO;
import com.example.demo.DTO.SignupRequestDTO;
import com.example.demo.entities.ConfirmationToken;
import com.example.demo.entities.User;
import com.example.demo.repositories.ConfirmationTokenRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.AuthenticationService;

@RequestMapping("/api/v1/auth")
@RestController
public class AuthenticationControllers {

	@Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
	@Autowired
	private UserRepository userRepository ;

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody @Validated SignupRequestDTO createUserDto){
        authenticationService.signup(createUserDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping("/signin")
    public JwtResponseDTO signin(@RequestBody @Validated LoginRequestDTO loginRequestDTO){
        return authenticationService.signin(loginRequestDTO);
    }
    @GetMapping("/confirm-account")
    public void  confirmaccount(@RequestParam("token")String confirmationToken)
      {
    	ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        System.out.println(token);
    	if(token != null)
        {
        	User user = userRepository.findByEmail(token.getUser().getEmail());
        	System.out.println(user.isEnabled());
        	user.setIs_enabled(true);
            userRepository.save(user);
        }
    	}

}

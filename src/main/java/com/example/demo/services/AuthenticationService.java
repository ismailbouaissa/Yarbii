package com.example.demo.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.catalina.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.example.demo.DTO.JwtResponseDTO;
import com.example.demo.DTO.LoginRequestDTO;
import com.example.demo.DTO.SignupRequestDTO;
import com.example.demo.entities.ConfirmationToken;
import com.example.demo.entities.Role;
import com.example.demo.entities.Role.RoleType;
import com.example.demo.entities.User;
import com.example.demo.jwt.JwtUtils;
import com.example.demo.repositories.ConfirmationTokenRepository;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;

@Import(SecurityConfig.class)
@Service
@Component
public class AuthenticationService {
	
	@Autowired
    private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository ;
	@Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
    private AuthenticationManager authenticationManager;
	@Autowired
    private JwtUtils jwtUtils;
	@Autowired
	ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired
    private EmailSenderService emailSenderService;

    private static final Map<RoleType, Role>  roles = new HashMap<>();
    
    
    @PostConstruct
    public void postConstruct(){
        roleRepository.findAll().stream().forEach(role -> roles.put(role.getName(), role));
    }
    

    public ResponseEntity<String>  signup(SignupRequestDTO createUserDto) {
        Role role = roles.get(RoleType.ROLE_ETUDIANT);
        if(userRepository.count() == 0){
            role = roles.get(RoleType.ROLE_ADMIN);
        }
    	HashSet<Role> set = new HashSet<Role>();
   	 	set.add(role);
        if(userRepository.findByUsername(createUserDto.getUsername()).isPresent()){
        	
         throw new RuntimeException(String.format("Username %s already exist", createUserDto.getUsername()));
        }
        if (userRepository.existsByEmail(createUserDto.getEmail())) {
        	throw new RuntimeException(String.format("Email %s already exist", createUserDto.getEmail()));
		}
        
        User user = new User(createUserDto.getUsername(),passwordEncoder.encode(createUserDto.getPassword()),createUserDto.getEmail(),set);
        userRepository.save(user);
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
		confirmationTokenRepository.save(confirmationToken);
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("chand312902@gmail.com");
        mailMessage.setText("To confirm your account, please click here : "
        +"http://localhost:8080/api/v1/auth/confirm-account?token="+confirmationToken.getConfirmationToken());
        emailSenderService.sendEmail(mailMessage);
        return ResponseEntity.status(HttpStatus.CREATED).body("client creer avec success");
    }

    public JwtResponseDTO signin(LoginRequestDTO loginRequestDTO) {
    	Optional<User> user = userRepository.findByUsername(loginRequestDTO.getUsername());
    	System.out.println(" cc "+user.get().getUsername());
    	if (!user.get().isIs_enabled()) {
			
    		System.out.print("not verified");
		}
    	else {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(),loginRequestDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        return new JwtResponseDTO(jwt);
    	}
    	
		return null;
    }
}
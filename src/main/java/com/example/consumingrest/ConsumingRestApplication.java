package com.example.consumingrest;

import com.example.consumingrest.data.*;
import com.example.consumingrest.model.AuthorizationModel;
import com.example.consumingrest.model.JwtModel;
import com.example.consumingrest.model.RegisterModel;
import com.example.consumingrest.model.UserModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SpringBootApplication
public class ConsumingRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumingRestApplication.class, args);
	}

	@PostMapping(value = "/auth/login")
	public @ResponseBody ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
		String username = loginRequest.getUsername();
		String password = loginRequest.getPassword();

		//fetch user
		try {
			User user = UserModel.getUser(username);

			// compare password
			if (!password.equals(user.getPassword())) {
				return new ResponseEntity<>(new ErrorResponse("error").toString(), HttpStatus.FORBIDDEN);
			}

			Account account = new Account(user.getId(), user.getUsername(), user.getRole());
			LoginResponse loginResponse = new LoginResponse(JwtModel.newJwt(user), account);

			/*
			JSONObject response = new JSONObject();
			response.put("token", loginResponse.getToken());
			response.put("account", account);
			root.put("place", place);
			String json = root.toJSONString();
			 */

			return new ResponseEntity<>(loginResponse.toString(), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponse("error").toString(), HttpStatus.FORBIDDEN);
		}

	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@PostMapping(value = "/accounts/register")
	public @ResponseBody ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
		String username = registerRequest.getUsername();
		String password = registerRequest.getPassword();
		String role = "user";

		//is password valid
		if (!RegisterModel.isPasswordValid(password)) {
			return new ResponseEntity<>(
					new ErrorResponse("invalid password").toString(), HttpStatus.UNPROCESSABLE_ENTITY
			);
		}

		//is username free
		try {
			if (!RegisterModel.isUsernameFree(username)) {
				return new ResponseEntity<>(
						new ErrorResponse("Username is already taken").toString(), HttpStatus.CONFLICT
				);
			}
			//create account into database
			RegisterModel.createAccount(username, password, role);
			Long id = UserModel.getUserId(username);
			return new ResponseEntity<>(
					new RegisterResponse(id, username, role).toString(), HttpStatus.CREATED
			);

		} catch (Exception e) {
			return new ResponseEntity<>(
					new ErrorResponse("error").toString(), HttpStatus.I_AM_A_TEAPOT
			);
		}

	}

	@PostMapping(value = "/session/authorization")
	public @ResponseBody ResponseEntity<String> authorization(@RequestBody AuthorizationRequest authorizationRequest) {
		String ressource = authorizationRequest.getRessource();
		String token = authorizationRequest.getToken();

		if (AuthorizationModel.isPermissionValid(ressource, token)) {
			return new ResponseEntity<>(
					new AuthorizationResponse().toString(), HttpStatus.OK
			);
		}

		return new ResponseEntity<>(
				new ErrorResponse("Access denied").toString(), HttpStatus.FORBIDDEN
		);
	}
}

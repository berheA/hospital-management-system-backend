
package com.berheamare.hospitalmanagementsystem.services;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.berheamare.hospitalmanagementsystem.models.Token;
import com.berheamare.hospitalmanagementsystem.models.User;
import com.berheamare.hospitalmanagementsystem.repositories.EmailSender;
import com.berheamare.hospitalmanagementsystem.repositories.UserRepository;
import com.berheamare.hospitalmanagementsystem.validations.EmailValidator;

import lombok.AllArgsConstructor;

@Service
//@AllArgsConstructor
public class UserService implements UserDetailsService{

//	@Autowired
   //private final User user;
	
		//@Autowired
		private final EmailValidator emailValidator;
		
		//@Autowired
		 private final EmailSender emailSender;
		
	@Autowired
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final TokenService tokenService;
	private final static String USER_NOT_FOUND_MSG=
			"user with email $s not found";
	
	public UserService(UserRepository userRepository, 
			BCryptPasswordEncoder bCryptPasswordEncoder,
			TokenService tokenService, 
			EmailValidator emailValidator, 
			EmailSender emailSender
			//User user
			) {
		//super();
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.tokenService=tokenService;
		this.emailSender=emailSender;
		this.emailValidator=emailValidator;
		//this.user=user;
				
	}


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		return userRepository.findByEmail(email).
				orElseThrow(
						()-> new UsernameNotFoundException(
								String.format(USER_NOT_FOUND_MSG, email))
				);
		
		
	}
	// goes to link
	public String signUp(User user) {
		boolean userExist= userRepository.findByEmail(user.getUsername()).isPresent();
		if(userExist) {
			throw new IllegalStateException("email already taken");
		}
		String encodedPassword=bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		userRepository.save(user);
		// defual takes 0 for booleaan I dont know why
		// to do  send confirmation token
		String token=UUID.randomUUID().toString();
		
		Token confirmationToken= new Token(
							token,
							LocalDateTime.now(), 
		// token expiring in 2 minutes
							LocalDateTime.now().plusMinutes(2), 
							user);
		
		// lets save it
				tokenService.saveToken(confirmationToken);
		//return "it works with signup";
				// todo send email
				return token;
	}


	 public int enableUser(String email) {
        return userRepository.enableUser(email);
    }
	 
	 
		public String register( User user) {
			// TODO Auto-generated method stub
			// lets check email valid
			
			boolean isValidEmail=  emailValidator.test(user.getEmail());
			if(!isValidEmail) {
				throw new IllegalStateException("email not valid");
			}
			
			String token= signUp(
					new User(user.getFirstName(),
								user.getLastName(),
								user.getEmail(),
								user.getPassword(),
								user.getUserRole())
					);
			
			String link= "http://localhost:8081/hms/confirmToken?token="+ token;
			emailSender.send(  user.getEmail(), 
					
					buildEmail(user.getFirstName(), link));
			
			return token;
		}
		
		
		
		@Transactional
		public String confirmToken(String token) {
			Token confirmationToken=tokenService.
					getToken(token)
					.orElseThrow(
							
							()-> new IllegalStateException("Token not found"));
							
			if(confirmationToken.getConfirmedAt()!=null) {
				throw new IllegalStateException("email already confirmed");
			}
			
			LocalDateTime expiredAt=confirmationToken.getExpiredAt();
			if(expiredAt.isBefore(LocalDateTime.now())) {
				throw new IllegalStateException("token expired");
			}
			
			tokenService.setConfirmedAt(token);
			
			enableUser(
					confirmationToken.getUser().getEmail());
			return "confirmed";
		}

		
		private String buildEmail(String name, String link) {
	        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
	                "\n" +
	                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
	                "\n" +
	                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
	                "    <tbody><tr>\n" +
	                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
	                "        \n" +
	                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
	                "          <tbody><tr>\n" +
	                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
	                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
	                "                  <tbody><tr>\n" +
	                "                    <td style=\"padding-left:10px\">\n" +
	                "                  \n" +
	                "                    </td>\n" +
	                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
	                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
	                "                    </td>\n" +
	                "                  </tr>\n" +
	                "                </tbody></table>\n" +
	                "              </a>\n" +
	                "            </td>\n" +
	                "          </tr>\n" +
	                "        </tbody></table>\n" +
	                "        \n" +
	                "      </td>\n" +
	                "    </tr>\n" +
	                "  </tbody></table>\n" +
	                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
	                "    <tbody><tr>\n" +
	                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
	                "      <td>\n" +
	                "        \n" +
	                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
	                "                  <tbody><tr>\n" +
	                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
	                "                  </tr>\n" +
	                "                </tbody></table>\n" +
	                "        \n" +
	                "      </td>\n" +
	                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
	                "    </tr>\n" +
	                "  </tbody></table>\n" +
	                "\n" +
	                "\n" +
	                "\n" +
	                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
	                "    <tbody><tr>\n" +
	                "      <td height=\"30\"><br></td>\n" +
	                "    </tr>\n" +
	                "    <tr>\n" +
	                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
	                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
	                "        \n" +
	                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 2 minutes. <p>See you soon</p>" +
	                "        \n" +
	                "      </td>\n" +
	                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
	                "    </tr>\n" +
	                "    <tr>\n" +
	                "      <td height=\"30\"><br></td>\n" +
	                "    </tr>\n" +
	                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
	                "\n" +
	                "</div></div>";
	    }

}
 
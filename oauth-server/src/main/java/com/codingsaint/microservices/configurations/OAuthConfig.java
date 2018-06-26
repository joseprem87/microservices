package com.codingsaint.microservices.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
@Configuration
public class OAuthConfig extends AuthorizationServerConfigurerAdapter {

	private String clientId = "my-awesome-client";
	private String clientSecret = "secret";
	
	@Autowired
	PasswordEncoder encoder;

	private String publicKey = "-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArijTzvNxM6Blxxa7FPwF\r\n" + 
			"DUIiEPtT6gmyhfIu4Vf8WTI7EKAwDj9OrBMwxqvhSnS0IahelKXx02fgbfrIA/SH\r\n" + 
			"ggdrPFodyffggGft80ITWlYxOxKD3gh91IzvaHtQ+9k19eJrAqDhzHymGtoFVkpX\r\n" + 
			"VPCSjdWJEn7i0nl2Zz6FRD69nljoVATgO3sNR17i8vffzMkTTZH6c97I/JGanf2X\r\n" + 
			"n3WiQ6PitW+gWhPvx+2PGprV72VOJuGh0UbY9qLzVxoPoCeDl6IMBklketPA3Uif\r\n" + 
			"YAFlkKqPes6h/sOBT8Am7vXglYCHqsT1S154YXuuzzHpI6wDGCMgVyncWbmfmLCd\r\n" + 
			"WwIDAQAB\r\n" + 
			"-----END PUBLIC KEY-----";
	private String privateKey = "-----BEGIN RSA PRIVATE KEY-----\r\n" + 
			"MIIEpAIBAAKCAQEArijTzvNxM6Blxxa7FPwFDUIiEPtT6gmyhfIu4Vf8WTI7EKAw\r\n" + 
			"Dj9OrBMwxqvhSnS0IahelKXx02fgbfrIA/SHggdrPFodyffggGft80ITWlYxOxKD\r\n" + 
			"3gh91IzvaHtQ+9k19eJrAqDhzHymGtoFVkpXVPCSjdWJEn7i0nl2Zz6FRD69nljo\r\n" + 
			"VATgO3sNR17i8vffzMkTTZH6c97I/JGanf2Xn3WiQ6PitW+gWhPvx+2PGprV72VO\r\n" + 
			"JuGh0UbY9qLzVxoPoCeDl6IMBklketPA3UifYAFlkKqPes6h/sOBT8Am7vXglYCH\r\n" + 
			"qsT1S154YXuuzzHpI6wDGCMgVyncWbmfmLCdWwIDAQABAoIBAFPPTe5/dHB1tcTT\r\n" + 
			"CYCVrxCsTJyDdeRgekq/OxQyNTvvUkEz9SxGBuQD8CsLN8WO18iCr9E08mTbzkLe\r\n" + 
			"tjZBDJzkWRSYAVHaOW1Rlk7D83A7nkFhLZhB2dvZXrSW82ffJXqIVRrUtRSkv4KY\r\n" + 
			"fI0EBXbYto4OvoHqEdlbnkEc8yDdjRYvEUchlPNCjobqU7wlqLEjNujqmzNqVJE3\r\n" + 
			"Vtck2AnMleUROm/85GMny90RdV41lZtkgavwXH72qeypFhwlkwJ4ScPrVRQdaMKN\r\n" + 
			"LENkxOkc4hh+lHhA0Dx1loOteEsOD4od8j+aTc9Fg/ZpqkaDXzd0mCyBfis0KCEU\r\n" + 
			"txL8McECgYEA3Eo4J77NfO3EdvQVN2BNTSTo0qRTuaq7Cbkfn9bRGjdB+oi/Fh5f\r\n" + 
			"y/joxkhz1jV5rM81gbnqZZtRhFs7JkzTGCrHMk8e8VbGiY15EJJl4qGdh72wBBaL\r\n" + 
			"Hd1wTGH7DyCrD4UHs9xO8tRQHHTKAw0/+jdF8l7PozSl3o5M/Pzi3ksCgYEAymQ/\r\n" + 
			"V5wXvxYANj0B3Dk4QoAJng5zxuGscNa9lSnAZEmHq62GE1iMdTq9heWk2nyWrs7V\r\n" + 
			"1c41ntLrToInHe0S+YlAuTbOx+UMVkrltppksOjo3XCHJIYaHU63wS7/8V/xu9g1\r\n" + 
			"MSRBVXVsAVZZtRtujPVckjugQXfCe6IuooN9kzECgYEAhOtjptfCNbdh7ESq6Jb7\r\n" + 
			"DalPsonk2wrhgKRrKI797MuazAjnA/mf4/t0uO/OtVne6R6+MmBzxotJTIufzSRd\r\n" + 
			"2x80wm5rRUQO18C9Y/px76WE3/rc0hqoJoUO1YfpbN9RWDP0ydoM6QiirgjqO50r\r\n" + 
			"FVd0pSIAN4H086z5V01ftrECgYEAylFGBKU0E7/BfHal6miEte1aL9cQo2oqwC3a\r\n" + 
			"HEc9XMMoTVTMJ4Cn//Q1SqlSj1pmIt7+YZmefmMn+DYhNkgsZKhwu/Vu5eIw3/5E\r\n" + 
			"/bH/H93kEqCPZ9JqPovXItWB4t9fPcEXA0XTzcLwsbGJQYLnkx8fZ8sVGvzFvGNW\r\n" + 
			"rPw2xrECgYBvlTiSNxoFrvcOp4lapPza0zAlGud6y5rt4ZjIY12FAdha42XD+i+O\r\n" + 
			"JnmA/HPzeD6lrdhqMICn5ej5F7Nuz5Q0TEa8fRIlroIM8Kjp3rL151udvduoHb24\r\n" + 
			"8ZFf90LzxXMQrvpEQl6TKWqtyHn5Wdxn7MLEZ0gKlUxx8UI/Y6fGug==\r\n" + 
			"-----END RSA PRIVATE KEY-----";

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	@Bean
	public JwtAccessTokenConverter tokenEnhancer() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(privateKey);
		converter.setVerifierKey(publicKey);
		return converter;
	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(tokenEnhancer());
	}

	
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore()).tokenEnhancer(tokenEnhancer());
	}

	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient(clientId).secret(encoder.encode(clientSecret)).scopes("read", "write")
				.authorizedGrantTypes("password", "refresh_token")
				.accessTokenValiditySeconds(200000)
				.refreshTokenValiditySeconds(20000);
	}

}

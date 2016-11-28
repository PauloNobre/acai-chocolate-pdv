package ufc.quixada.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import ufc.quixada.model.Funcionario;
import ufc.quixada.repository.FuncionarioRepository;

@Service
public class AuthenticationProviderPDV implements AuthenticationProvider{

	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Funcionario funcionario = funcionarioRepository.findByNome(authentication.getName());
		
		String senha = (String) authentication.getCredentials();
		
		if(funcionario == null) {
			throw new BadCredentialsException("Login Inválido");
		}
		
		if(!funcionario.getPassword().equals(senha)) {
			throw new BadCredentialsException("Login Inválido");
		}
		
		Collection<? extends GrantedAuthority> authorities = funcionario.getAuthorities();
		return new UsernamePasswordAuthenticationToken(funcionario, senha, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}

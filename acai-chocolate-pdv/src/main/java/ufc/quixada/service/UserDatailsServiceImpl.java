package ufc.quixada.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ufc.quixada.model.Funcionario;
import ufc.quixada.repository.FuncionarioRepository;

@Service
public class UserDatailsServiceImpl implements UserDetailsService{

	@Autowired
	FuncionarioRepository funcionarioRepository;
	@Override
	public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
		Funcionario funcionario = funcionarioRepository.findByNome(nome);

		if (funcionario == null) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}

		return funcionario;
	}

}

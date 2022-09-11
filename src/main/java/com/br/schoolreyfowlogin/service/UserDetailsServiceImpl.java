package com.br.schoolreyfowlogin.service;

import com.br.schoolreyfowlogin.model.Role;
import com.br.schoolreyfowlogin.model.UserModel;
import com.br.schoolreyfowlogin.model.dto.UserDto;
import com.br.schoolreyfowlogin.model.mapper.ModelDaoOMapper;
import com.br.schoolreyfowlogin.repository.UserRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ValidationException;
import java.util.List;

import static java.lang.String.format;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    final ModelDaoOMapper mapper = Mappers.getMapper(ModelDaoOMapper.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final var user = repository
                .findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(format("User with username - %s, not found", username)));
        return new User(user.getName(), user.getPassword(), true, true, true, true, user.getRoles());
    }

    public Iterable<UserModel> getAll(){
        return repository.findAll();
    }

    public UserModel getUser(String email) {
        return repository.findById(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

    }

    public UserModel create(UserDto userDto) throws ValidationException {
        if (!userDto.getRePassword().equals(userDto.getPassword())) {
            throw new ValidationException("Passwords don't match!");
        }

        final var user = mapper.create(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(List.of(new Role(Role.COORDINATOR)));
        return repository.save(user);
    }

}

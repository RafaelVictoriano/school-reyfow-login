package com.br.schoolreyfowlogin.service;

import com.br.schoolreyfowlogin.model.Role;
import com.br.schoolreyfowlogin.model.UserModel;
import com.br.schoolreyfowlogin.model.dto.UserDto;
import com.br.schoolreyfowlogin.model.dto.UserResponseDTO;
import com.br.schoolreyfowlogin.model.mapper.ModelDaoOMapper;
import com.br.schoolreyfowlogin.repository.UserRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.xml.bind.ValidationException;
import java.util.Set;

import static java.lang.String.format;

@Transactional
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    final UserRepository userRepository;
    final ModelDaoOMapper mapper = Mappers.getMapper(ModelDaoOMapper.class);

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(format("User with username - %s, not found", username)));
       // return new User(user.getUsername(), user.getPassword(), true, true, true, true, user.getAuthorities());
    }

    public UserModel getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

    }

    @Transactional
    public UserResponseDTO create(UserDto userDto) throws ValidationException {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new ValidationException("Username exists!");
        }
        if (!userDto.getRePassword().equals(userDto.getPassword())) {
            throw new ValidationException("Passwords don't match!");
        }

        UserModel userModel = this.mapper.create(userDto);

        final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userModel.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userModel.setRoles(Set.of(new Role(Role.COORDINATOR)));
        userRepository.save(userModel);

        return this.mapper.userToUserResponseDTO(userModel);
    }

}

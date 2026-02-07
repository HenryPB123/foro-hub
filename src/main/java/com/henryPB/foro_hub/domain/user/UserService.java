package com.henryPB.foro_hub.domain.user;

import com.henryPB.foro_hub.domain.pageResponse.PageResponseData;
import com.henryPB.foro_hub.infra.exceptions.ResourceAlreadyExistsException;
import com.henryPB.foro_hub.infra.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;


@Service
public class UserService {

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private  PasswordEncoder passwordEncoder;


    public User createUser(RegisterUserData data) {
        if (userRepository.existsByEmail(data.email())) {
            throw new ResourceAlreadyExistsException("Este email ya corresponde a un usuario");
        }

        User user = new User(
                data.email().toLowerCase(),
                passwordEncoder.encode(data.password())
        );

        return userRepository.save(user);
    }


    public PageResponseData<RegisterDetailUserData> getAllUsers(Pageable pageable) {

        Page<User> page = userRepository.findAll(pageable);

        // Control de error si no hay usuarios
        if (page.isEmpty()) {
            throw new EntityNotFoundException("No hay usuarios registrados");
        }

        Page<RegisterDetailUserData> dtoPage =
                page.map(RegisterDetailUserData::new);

        return new PageResponseData<>(dtoPage);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("El recurso solicitado no existe"));
    }

    public User updateUser(UpdateDataUser data){
        User user = userRepository.findById(data.id())
                .orElseThrow(()->new ResourceNotFoundException("El recurso solicitado no existe"));

        String encodedPassword = null;
        if (data.password() != null) {
            encodedPassword = passwordEncoder.encode(data.password());
        }

        user.update(
                data.email(),
                encodedPassword,
                data.profile()
        );

        return user;
    }

    public String desactivateUser(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("El recurso solicitado no existe"));

        user.desactivate();

        return "Usuario desactivado.";
    }
}


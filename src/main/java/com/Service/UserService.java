package com.Service;

import com.database.entites.ActivationCode;
import com.database.entites.User;
import com.database.other.Role;
import com.database.repos.ActivationCodeRepository;
import com.database.repos.UserRepository;
import com.mail.MailSender;
import com.mail.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final ActivationCodeRepository activationCodeRepository;
    private final MailSender mailSender;

    @Autowired
    public UserService(UserRepository userRepository, ActivationCodeRepository activationCodeRepository, MailSender mailSender) {
        this.userRepository = userRepository;
        this.activationCodeRepository = activationCodeRepository;
        this.mailSender = mailSender;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException { // для hasAuthority
        return userRepository.findByUsername(s);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Iterable<User> sortByAuthority(Iterable<User> users) {
        Iterator<User> iterator = users.iterator();
        List<User> list = new ArrayList<>();
        iterator.forEachRemaining(list::add);
        list.sort(Comparator.reverseOrder());
        return list;
    }

    public boolean delete(User user) {
        if (user != null && !user.isAdmin()) {
            userRepository.delete(user);
            return true;
        }
        return false;
    }

    public void save(User user, String username, Role role) {
        if (role != null) {
            user.setRole(role);
        }
        user.setUsername(username);
        userRepository.save(user);
    }

    public boolean sendActivationCode(ActivationCode code) {
        if (code.getUser().getEmail() != null && !code.getUser().isEmailConfirmed()) {
            mailSender.send(
                    code.getUser().getEmail(),
                    "Activation",
                    String.format(
                            Messages.ACTIVATION_MESSAGE,
                            code.getUser().getUsername(),
                            "http://localhost:8080/activate/" + code.getCode()));
            return true;
        }
        return false;
    }

    public boolean register(User user) {
        User byUsername = userRepository.findByUsername(user.getUsername());
        User byEmail = userRepository.findByEmail(user.getEmail());
        if (byUsername != null || byEmail != null) {
            return false;
        } else {
            user.setActivity(true);
            user.setRole(Role.GUEST);
        }
        ActivationCode activationCode = new ActivationCode();
        activationCode.setUser(user);
        sendActivationCode(activationCode);
        activationCodeRepository.save(activationCode);
        userRepository.save(user);

        return true;
    }

    public boolean checkActivationCode(String code) {
        return activationCodeRepository.findByCode(code) != null;
    }

    public boolean activate(String code) {
        ActivationCode byCode = activationCodeRepository.findByCode(code);
        if (byCode != null) {
            activationCodeRepository.delete(byCode);
            if (byCode.getUser() != null) {
                byCode.getUser().setEmailConfirmed(true);
                byCode.getUser().setRole(Role.USER);
                userRepository.save(byCode.getUser());
                return true;
            }
        }
        return false;
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    public Iterable<User> searchUserByUsername(String search) {
        if (search != null && !search.isEmpty()) {
            ArrayList<User> users = new ArrayList<>();
            User byUsername = userRepository.findByUsername(search);
            if (byUsername != null) {
                users.add(byUsername);
            }
            return users;
        } else {
            return getAllUsers();
        }
    }
}

package pl.maciejowsky.banksystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.maciejowsky.banksystem.dao.UserDAOImpl;
import pl.maciejowsky.banksystem.model.User;


@Service
public class UserPrincipalDetailsService implements UserDetailsService {
    @Autowired
    private UserDAOImpl userDAO;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDAO.findUserByEmail(email);

        if (user == null)
            throw new UsernameNotFoundException("User 404");
        else {

            return new UserPrincipal(user);
        }

    }
}

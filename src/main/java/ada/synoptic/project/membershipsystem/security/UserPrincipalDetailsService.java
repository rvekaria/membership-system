package ada.synoptic.project.membershipsystem.security;

import ada.synoptic.project.membershipsystem.db.EmployeeRepository;
import ada.synoptic.project.membershipsystem.model.Employee;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {
    private EmployeeRepository employeeRepository;

    public UserPrincipalDetailsService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String cardId) throws UsernameNotFoundException {
        Employee employee = this.employeeRepository.findByCardId(cardId);
        return new UserPrincipal(employee);
    }
}

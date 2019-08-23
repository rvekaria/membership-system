package ada.synoptic.project.membershipsystem.security;

import ada.synoptic.project.membershipsystem.controller.exception.EmployeeNotFoundException;
import ada.synoptic.project.membershipsystem.controller.resource.EmployeeResource;
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
        Employee employee = employeeRepository.findByCardId(cardId);

        if (employee == null) {
            try {
                throw new EmployeeNotFoundException();
            } catch (EmployeeNotFoundException e) {
                e.printStackTrace();
                return new UserPrincipal(null);
            }
        } else {
            return new UserPrincipal(employee);
        }
    }


}

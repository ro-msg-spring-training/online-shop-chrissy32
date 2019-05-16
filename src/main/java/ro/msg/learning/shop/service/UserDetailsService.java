package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.UsernamePasswordDTO;
import ro.msg.learning.shop.repository.ICustomerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserDetailsService {
    private ICustomerRepository customerRepository;

    public List<UsernamePasswordDTO> retrieveUsernameAndPasswords() {
        List<Object[]> objects = customerRepository.findUsernameAndPasswords();
        List<UsernamePasswordDTO> usernamePasswordDTOS = new ArrayList<>();

        objects.forEach(obj -> usernamePasswordDTOS.add(new UsernamePasswordDTO(obj[0].toString(), obj[1].toString())));

        return usernamePasswordDTOS;
    }
}

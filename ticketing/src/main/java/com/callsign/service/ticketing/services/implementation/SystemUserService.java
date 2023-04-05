package com.callsign.service.ticketing.services.implementation;



import com.callsign.service.ticketing.exception.SystemUserAlreadyRegisteredException;
import com.callsign.service.ticketing.models.jwt.JWTUserDetail;
import com.callsign.service.ticketing.models.jwt.SystemUser;
import com.callsign.service.ticketing.models.request.SystemUserRegistrationRequest;
import com.callsign.service.ticketing.repos.SystemUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Junaid Shakeel
 * @project Exercise
 */
@Service
public class SystemUserService implements UserDetailsService {

    @Autowired
    SystemUserRepo userRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        SystemUser systemUser = userRepo.findByUserEmail(email).orElseThrow(()->
                new UsernameNotFoundException("User Not Found with username: " + email));
            return JWTUserDetail.build(systemUser);
    }

    @Transactional(rollbackFor = Exception.class)
    public SystemUser saveSystemUser(SystemUserRegistrationRequest userDto) throws SystemUserAlreadyRegisteredException{
        if(userRepo.existsByUserEmail(userDto.getEmail().trim()))
            throw new SystemUserAlreadyRegisteredException("User Already Registered in database with this email "+ userDto.getEmail());
        return userRepo.save(new SystemUser(userDto.getUserName(),bCryptPasswordEncoder.encode(userDto.getPwd()) , userDto.getEmail()));
    }
    }

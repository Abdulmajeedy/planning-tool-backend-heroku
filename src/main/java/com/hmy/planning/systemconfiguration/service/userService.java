package com.hmy.planning.systemconfiguration.service;

import java.util.List;
import java.util.Optional;
import lombok.Data;

import com.hmy.planning.systemconfiguration.dto.usersResponseDto;
import com.hmy.planning.systemconfiguration.models.users;
import com.hmy.planning.systemconfiguration.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import lombok.Data;

@Data
@Service
public class userService {

    @Autowired
    private JavaMailSender mailSender;
    private ModelMapper modelMapper;
    private UserRepository userRepository;
    private final int workload = 12;

    @Autowired
    public userService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    // private void sendPlainTextEmail(String toEmail, UUID uuid) {
    // String from = "hmysoftdev@gmail.com";
    // String to = toEmail;
    // SimpleMailMessage message = new SimpleMailMessage();
    // message.setFrom(from);
    // message.setTo(to);
    // message.setSubject("Profile Id");
    // message.setText("This is your prifile id :" + uuid);
    // mailSender.send(message);
    // }

    // public Map<String, Boolean> verfyCode(UUID profileCode) throws Exception {

    // List<Profiles> profile = profilerRepository.findAll();
    // Map response = new HashMap<String, Boolean>();
    // int c = 0;
    // for (Profiles profiles : profile) {
    // if (profileCode.equals(profiles.getProfilCode())) {
    // c = 1;
    // }
    // }
    // switch (c) {
    // case 1:
    // response.put("response", Boolean.TRUE);
    // break;
    // case 0:
    // response.put("response", Boolean.FALSE);
    // }
    // return response;
    // }

    public List<users> findAllUsers(PageRequest pageRequest) {
        return userRepository.findAll(pageRequest).getContent();
    }

    public void deleteUser(String userCode) {

        userRepository.deleteById(userCode);
    }

    public Optional<users> getUserById(String userCode) {
        return userRepository.findById(userCode);
    }

    public ResponseEntity<usersResponseDto> addNewUser(usersResponseDto reqUser) {
        users use = new users();
        use.setFirstname(reqUser.getFirstName());
        use.setMiddlename(reqUser.getMiddleName());
        use.setLastname(reqUser.getLastName());
        use.setEmail(reqUser.getEmail());
        use.setRoles(reqUser.getRoles());
        use.setStatus(reqUser.getStatus());
        userRepository.save(use);

        usersResponseDto userDto = new usersResponseDto();
        userDto.setFirstName(use.getFirstname());
        userDto.setMiddleName(reqUser.getMiddleName());
        userDto.setLastName(reqUser.getLastName());
        userDto.setEmail(reqUser.getEmail());
        userDto.setRoles(reqUser.getRoles());
        userDto.setStatus(reqUser.getStatus());
        userDto.setCreatedDate(reqUser.getCreatedDate());
        userDto.setCreatedBy(reqUser.getCreatedBy());
        userDto.setModifiedDate(reqUser.getModifiedDate());
        userDto.setModifiedBy(reqUser.getModifiedBy());
        return ResponseEntity.ok(userDto);
    }

    public void updateUserDetails(String userCode, users reqUsers) {
        userRepository.findById(userCode)
                .orElseThrow(() -> new IllegalStateException(
                        "User with Code " + userCode + " does not exist"));

        reqUsers.setUserCode(userCode);
        users u = new users();
        u.setCreatedBy(reqUsers.getCreatedBy());
        u.setCreatedDate(reqUsers.getCreatedDate());
        u.setModifiedBy(reqUsers.getModifiedBy());
        userRepository.save(reqUsers);

    }

}

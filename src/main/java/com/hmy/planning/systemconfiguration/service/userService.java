package com.hmy.planning.systemconfiguration.service;

import java.util.List;

import com.hmy.planning.systemconfiguration.models.users;
import com.hmy.planning.systemconfiguration.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service
public class userService {

    @Autowired
    private JavaMailSender mailSender;
    private final ProfileRepository profilerRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final int workload = 12;

    public List<ProfilerResponseDto> getData(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Profiles> profiler = profilerRepository.findAll(pageable);
        ProfilerResponseDto profilerDto = null;
        List list = new ArrayList();
        for (Profiles hj : profiler) {
            profilerDto = modelMapper.map(hj, ProfilerResponseDto.class);
            list.add(profilerDto);
        }
        return list;
    }

public Map<String, Boolean> updateStatus(int profileId) {
Profiles profiler = profilerRepository.findById(profileId)
.orElseThrow(() -> new ResourceAccessException("Profiler not found of this
id::" + profileId));
if (profiler.getStatus() == 1)
profiler.setStatus(0);
else
profiler.setStatus(1);
profilerRepository.save(profiler);
Map<String, Boolean> response = new HashMap<String, Boolean>();
response.put("response", Boolean.TRUE);
return response;
}

    public Map<String, Boolean> post(ProfilerDto profilerDto) {
        Profiles profiler = modelMapper.map(profilerDto, Profiles.class);
        profiler.setStatus(1);
        profiler.setCreatedDate(LocalDateTime.now());
        profiler.setCreatedDate(LocalDateTime.now());
        UUID uuid = UUID.randomUUID();
        profiler.setProfilCode(uuid);
        sendPlainTextEmail(profiler.getEmail(), uuid);
        profilerRepository.save(profiler);
        Map<String, Boolean> response = new HashMap<String, Boolean>();
        response.put("response", Boolean.TRUE);
        return response;
    }

    private void sendPlainTextEmail(String toEmail, UUID uuid) {
        String from = "hmysoftdev@gmail.com";
        String to = toEmail;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject("Profile Id");
        message.setText("This is your prifile id :" + uuid);
        mailSender.send(message);
    }

public Map<String, Boolean> update(int profileId, ProfilerDto profilerDto) {
profilerRepository.findById(profileId)
.orElseThrow(() -> new ResourceAccessException("Profiler not found of this
id::" + profileId));
Profiles profiles = modelMapper.map(profilerDto, Profiles.class);
UUID uuid = UUID.fromString(profilerDto.getProfilCode());
profiles.setPass(profiles.getPass());
profiles.setProfilCode(uuid);
profiles.setProfileId(profileId);
profiles.setModifyDate(LocalDateTime.now());
profilerRepository.save(profiles);
Map<String, Boolean> response = new HashMap<String, Boolean>();
response.put("response", Boolean.TRUE);
return response;
}

public ProfilerDto getdataById(int profileId) {
Profiles profile = profilerRepository.findById(profileId)
.orElseThrow(() -> new ResourceAccessException("Profiler not found of this
id::" + profileId));
;
ProfilerDto profilerDto = modelMapper.map(profile, ProfilerDto.class);
return profilerDto;
}

    public ProfilerResponseDto getByCode(UUID profilCode) {
        Profiles profiles = profilerRepository.findByProfilCode(profilCode);
        ProfilerResponseDto profilerResponseDto = modelMapper.map(profiles,
                ProfilerResponseDto.class);
        return profilerResponseDto;
    }

    public Map<String, Boolean> verfyCode(UUID profileCode) throws Exception {

        List<Profiles> profile = profilerRepository.findAll();
        Map response = new HashMap<String, Boolean>();
        int c = 0;
        for (Profiles profiles : profile) {
            if (profileCode.equals(profiles.getProfilCode())) {
                c = 1;
            }
        }
        switch (c) {
            case 1:
                response.put("response", Boolean.TRUE);
                break;
            case 0:
                response.put("response", Boolean.FALSE);
        }
        return response;
    }

public Map<String, Boolean> authorUpdate(UserdetailsDto userdetailsDto)
throws Exception {
List<Profiles> profile = profilerRepository.findAll();
UUID usercode = UUID.fromString(userdetailsDto.getProfilCode());
Profiles profiles1 = null;
Map response = new HashMap<String, Boolean>();
int c = 0;
for (Profiles profiles : profile) {
if (usercode.equals(profiles.getProfilCode())) {
c = 1;
}
}
switch (c) {
case 1:
User user = new User();
//// String salt = BCrypt.gensalt(workload);
//// String hashed_password = BCrypt.hashpw(userdetailsDto.getPassword(),
salt);
// System.out.println(profiles1.getProfName());
user.setActive(userdetailsDto.isActive());
if (!userdetailsDto.getCompany().equals(null)) {
user.setCompany(userdetailsDto.getCompany());
}
if (!userdetailsDto.getDepartment().equals(null)) {
user.setDepartment(userdetailsDto.getDepartment());
}
if (!userdetailsDto.getUserproid().equals(null)) {
user.setUserProfile(userdetailsDto.getUserproid());
}
user.setRoles(userdetailsDto.getRoles());
user.setUsername(userdetailsDto.getUsername());
user.setPassword(userdetailsDto.getPassword());

userRepository.save(user);
response.put("response", Boolean.TRUE);
break;
case 0:
response.put("response", Boolean.FALSE);

}

return response;
}

    public Map<String, Boolean> author(String users, String pass) {
        List<Profiles> profile = profilerRepository.findAll();
        Profiles profiles1 = null;
        Map response = new HashMap<String, Boolean>();
        int res = 0;
        for (Profiles profiles : profile) {
            if (users.equals(profiles.getUsers()) && pass.equals(profiles.getPass())) {
                profiles1 = profiles;
                res = 1;
            }
        }
        switch (res) {
            case 1:
                response.put("profileCode", profiles1.getProfilCode());
                response.put("role", profiles1.getRoles());
                break;
            case 0:
                response.put("response", Boolean.FALSE);
        }
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

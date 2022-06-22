// package com.hmy.planning.systemconfiguration.service;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.Optional;

// import org.modelmapper.ModelMapper;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Service;
// import org.springframework.web.server.ResponseStatusException;

// import com.hmy.planning.systemconfiguration.dto.loginRequestDto;
// import com.hmy.planning.systemconfiguration.dto.loginResponseDto;
// import com.hmy.planning.systemconfiguration.models.login;
// import com.hmy.planning.systemconfiguration.repository.LoginRepository;

// import lombok.Data;

// @Service
// @Data
// public class loginService {

// @Autowired
// private LoginRepository loginRepo;
// private final ModelMapper modelmapper;

// public Optional<login> login(String email) {
// return loginRepo.findByEmail(email);
// }

// public List<loginResponseDto> findAlllogin(int page, int size) {
// PageRequest pageRequest = PageRequest.of(page, size);
// List<login> logn = loginRepo.findAll(pageRequest).getContent();
// List<loginResponseDto> loginDto = new ArrayList<>();
// for (login lon : logn) {
// loginResponseDto responseDto = modelmapper.map(lon, loginResponseDto.class);
// responseDto.setRoleCode(lon.getRole().getRoleCode());
// loginDto.add(responseDto);
// }
// return loginDto;
// }

// public void deleteObjective(String LoginCode) {

// loginRepo.deleteById(LoginCode);
// }

// public loginResponseDto getLoginById(String LoginCode) {
// Optional<login> login = loginRepo.findById(LoginCode);
// if (!login.isPresent()) {
// throw new ResponseStatusException(HttpStatus.NOT_FOUND,
// "project with this code" + LoginCode + "is not Found");
// } else {
// login obj = login.get();
// loginResponseDto responseDto = modelmapper.map(obj, loginResponseDto.class);
// responseDto.setLoginCode(obj.getLoginCode());
// return responseDto;
// }
// }

// public Optional<login> getLoginCode(String loginCode) {
// return loginRepo.findById(loginCode);
// }

// public Map<String, Boolean> updateStatus(String LoginCode) {
// Optional<login> bp = loginRepo.findById(LoginCode);
// if (!bp.isPresent()) {
// throw new ResponseStatusException(HttpStatus.NOT_FOUND);
// }
// if (bp.get().getStatus() == 1)
// bp.get().setStatus(0);
// else
// bp.get().setStatus(1);
// loginRepo.save(bp.get());
// Map<String, Boolean> response = new HashMap<>();
// response.put("response", Boolean.TRUE);
// return response;
// }

// public List<loginResponseDto> findAllLogin(int page, int size) {
// PageRequest pageRequest = PageRequest.of(page, size);
// List<login> obje = loginRepo.findAll(pageRequest).getContent();
// List<loginResponseDto> objDto = new ArrayList<>();
// for (login objective : obje) {
// loginResponseDto responseDto = modelmapper.map(objective,
// loginResponseDto.class);
// responseDto.setRoleCode(objective.getRole().getRoleCode());
// objDto.add(responseDto);
// }
// return objDto;
// }

// }

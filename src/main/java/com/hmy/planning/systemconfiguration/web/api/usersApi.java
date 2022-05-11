package com.hmy.planning.systemconfiguration.web.api;

import com.hmy.planning.systemconfiguration.dto.userDetailsRequestDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin
@RequestMapping("/Authentication")
public interface usersApi {

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ResponseEntity getData(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size);

    // @RequestMapping(path = "/profilecode/{profilCode}", method =
    // RequestMethod.GET)
    // public ResponseEntity getByCode(@PathVariable("profilCode") UUID profilCode);

    @RequestMapping(path = "/{profileId}", method = RequestMethod.GET)
    public ResponseEntity getDataByid(@PathVariable("profileId") int profileId);

    // @RequestMapping(path = "verfy/{profilecode}", method = RequestMethod.GET)
    // public ResponseEntity verifyCodeProfile(@PathVariable("profilecode") UUID
    // profileCode) throws Exception;

    @RequestMapping(path = "createAccount", method = RequestMethod.GET)
    public ResponseEntity authUpdate(@RequestBody userDetailsRequestDto userdetailsRequesDto) throws Exception;

    @RequestMapping(path = "/status/{profileId}", method = RequestMethod.PUT)
    public ResponseEntity updateStatus(@PathVariable("profileId") int profileId);

    // @RequestMapping(path = "/update/{profileId}", method = RequestMethod.PUT)
    // public ResponseEntity Update(@PathVariable("profileId") int profileId,
    // @RequestBody ProfilerDto profilerDto);

    // @RequestMapping(path = "/post", method = RequestMethod.POST)
    // public ResponseEntity post(@RequestBody ProfilerDto profilerDto);

}

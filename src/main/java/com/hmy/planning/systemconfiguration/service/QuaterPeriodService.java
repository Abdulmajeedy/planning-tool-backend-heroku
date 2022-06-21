package com.hmy.planning.systemconfiguration.service;

import java.util.*;

import com.hmy.planning.systemconfiguration.dto.quaterPeriodRequestDto;
import com.hmy.planning.systemconfiguration.dto.quaterPeriodResponseDto;
import com.hmy.planning.systemconfiguration.models.QuaterPeriod;
import com.hmy.planning.systemconfiguration.repository.QuaterPeriodRepository;

import lombok.Data;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Data
public class QuaterPeriodService {

    @Autowired
    private QuaterPeriodRepository quaterRepo;
    private final ModelMapper modelmapper;

    public List<quaterPeriodResponseDto> findAllQuaterPeriod(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<QuaterPeriod> quaterPeriods = quaterRepo.findAll(pageRequest).getContent();
        List<quaterPeriodResponseDto> QtaerPeriodDto = new ArrayList<>();
        for (QuaterPeriod qp : quaterPeriods) {
            quaterPeriodResponseDto responseDto = modelmapper.map(qp, quaterPeriodResponseDto.class);
            QtaerPeriodDto.add(responseDto);
        }
        return QtaerPeriodDto;
    }

    public ResponseEntity<quaterPeriodResponseDto> addNewQuaterPeriod(quaterPeriodRequestDto reqQuaterPeriodCode) {
        QuaterPeriod quaterPeriods = modelmapper.map(reqQuaterPeriodCode, QuaterPeriod.class);
        quaterRepo.save(quaterPeriods);

        quaterPeriodResponseDto qperiod = modelmapper.map(reqQuaterPeriodCode, quaterPeriodResponseDto.class);
        return ResponseEntity.ok(qperiod);

    }

    public void deleteQuaterPeriod(String quaterPeriodCode) {

        quaterRepo.deleteById(quaterPeriodCode);
    }

    public quaterPeriodResponseDto getQuaterPeriodById(String quaterPeriodCode) {
        Optional<QuaterPeriod> roles = quaterRepo.findById(quaterPeriodCode);
        if (!roles.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "project  with this code" + quaterPeriodCode + "is not Found");
        } else {
            QuaterPeriod qp = roles.get();
            quaterPeriodResponseDto responseDto = modelmapper.map(qp, quaterPeriodResponseDto.class);
            return responseDto;
        }
    }

    public ResponseEntity<quaterPeriodResponseDto> updateQuaterPeriod(String quaterPeriodCode,
            QuaterPeriod reqQuaterPeriod) {

        Optional<QuaterPeriod> roles = quaterRepo.findById(quaterPeriodCode);
        if (!roles.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "project  with this code" + quaterPeriodCode + "is not Found");
        }
        QuaterPeriod qp = modelmapper.map(reqQuaterPeriod, QuaterPeriod.class);
        qp.setQuaterPeriodCode(quaterPeriodCode);
        quaterRepo.save(qp);

        quaterPeriodResponseDto roltDto = modelmapper.map(qp, quaterPeriodResponseDto.class);
        return ResponseEntity.ok(roltDto);

    }

    public Optional<QuaterPeriod> getQuaterPeriodCode(String QuaterPeriodCode) {
        return quaterRepo.findById(QuaterPeriodCode);
    }

    public Map<String, Boolean> updateStatus(String officeID) {
        Optional<QuaterPeriod> bp = quaterRepo.findById(officeID);
        if (!bp.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (bp.get().getStatus() == 1)
            bp.get().setStatus(0);
        else
            bp.get().setStatus(1);
        quaterRepo.save(bp.get());
        Map<String, Boolean> response = new HashMap<>();
        response.put("response", Boolean.TRUE);
        return response;
    }

}

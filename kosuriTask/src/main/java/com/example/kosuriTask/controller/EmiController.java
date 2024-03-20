package com.example.kosuriTask.controller;

import com.example.kosuriTask.dto.EmiPatternDto;
import com.example.kosuriTask.service.EmiPatternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('FI') and hasAuthority('SUADM') and hasAuthority('CR')")
@RequestMapping("/api/emi")
public class EmiController {
    private final EmiPatternService emiPatternService;
    @Autowired
    public EmiController(EmiPatternService emiPatternService){
        this.emiPatternService=emiPatternService;
    }

    @PostMapping("/addDetails/{contactEmail}")
    public ResponseEntity<EmiPatternDto> saveEmiPattern(@RequestBody EmiPatternDto emiPattern,
                                                        @PathVariable String contactEmail) {
        return new ResponseEntity<>(emiPatternService.saveEmiPattern(emiPattern,contactEmail), HttpStatus.CREATED);
    }

    @GetMapping("/getEmiDetails/{contactEmail}")
    public ResponseEntity<List<EmiPatternDto>> getEmiPattern(@PathVariable String contactEmail) {
        return new ResponseEntity<>(emiPatternService.getEmiPatternById(contactEmail),HttpStatus.OK);
    }

    @PutMapping("/updateEmiDetails/{contactEmail}/{emiId}")
    public ResponseEntity<EmiPatternDto> updateEmiPattern (@PathVariable String contactEmail,@PathVariable long emiId,
                                                          @RequestBody EmiPatternDto updatedEmiPattern) {
        return new ResponseEntity<>(emiPatternService.updateEmiPattern(emiId, updatedEmiPattern,contactEmail),HttpStatus.OK);
    }
}

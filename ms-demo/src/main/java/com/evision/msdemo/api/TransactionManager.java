package com.evision.msdemo.api;

import com.evision.msdemo.models.dto.ResponseDTO;
import com.evision.msdemo.models.dto.Transaction;
import com.evision.msdemo.models.dto.ValidationResultDTO;
import com.evision.msdemo.service.FileManagerService;
import com.evision.msdemo.service.TransactionManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/transactions")
public class TransactionManager {

    private Logger LOGGER = LoggerFactory.getLogger(TransactionManager.class);

    private final TransactionManagerService transactionService;
    private final FileManagerService fileService;

    @Autowired
    public TransactionManager(TransactionManagerService transactionService, FileManagerService fileService) {
        this.transactionService = transactionService;
        this.fileService = fileService;
    }

    @PostMapping("/checksum")
    public ResponseEntity<String> processTransactions(@RequestParam("file") MultipartFile file) {
        try {
            String actualCheckSum = fileService.getFileChecksum(file.getInputStream());
            return new ResponseEntity<>(actualCheckSum, HttpStatus.OK);
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseDTO> processTransactions(@RequestParam("file") MultipartFile file,
                                                           @RequestParam("checksum") String checksum,
                                                           @RequestParam(value = "separator", defaultValue = ",", required = false) char separator) {
        ResponseDTO responseDTO = new ResponseDTO();

        try {
            fileService.verifyFile(responseDTO,file, checksum);

            if (!responseDTO.isVerified()) {
                responseDTO.setResponseCode("002");
                responseDTO.setResponseDesc(String.format("Verification errors: %s", responseDTO.getVerificationErrors()));
                return new ResponseEntity(responseDTO, HttpStatus.EXPECTATION_FAILED);
            }

            ValidationResultDTO resultDTO = fileService.validateFile(file, separator);
            fillResponseDTO(responseDTO,resultDTO);
            transactionService.calculateTransactionsByType(responseDTO,resultDTO.getTransactionList());
            
            if (!responseDTO.isValid()) {
                responseDTO.setResponseCode("003");
                responseDTO.setResponseDesc(String.format("File is verified but has validation errors, {Number of valid records: %s} -  {number of invalid records: %s} - {validation errors: %s}", responseDTO.getValidTransactions(), responseDTO.getInvalidTransactions(), responseDTO.getValidationErrors()));
                return new ResponseEntity(responseDTO, HttpStatus.EXPECTATION_FAILED);
            } else {
                responseDTO.setResponseCode("001");
                responseDTO.setResponseDesc(String.format("File is verified and valid - {Total number of records: %s} ", responseDTO.getValidTransactions()));
                return new ResponseEntity(responseDTO, HttpStatus.OK);
            }
        } catch (IOException | NoSuchAlgorithmException e) {
            responseDTO.setResponseCode("004");
            responseDTO.setResponseDesc(String.format("Errors occured during file processing - {exceptions: %s}", e.getMessage()));
            return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
        }

    }

    private void fillResponseDTO(ResponseDTO responseDTO, ValidationResultDTO resultDTO) {

        responseDTO.setValidationErrors(resultDTO.getValidationErrors());
        responseDTO.setValidTransactions(resultDTO.getValidTransactions());
        responseDTO.setInvalidTransactions(resultDTO.getInvalidTransactions());
    }


}




package com.cropdetails.systemcropdetails.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.systemcropdetails.controller.CropsDetailsController;
import com.systemcropdetails.model.CropDetails;
import com.systemcropdetails.repo.CropDetailsRepo;
import com.systemcropdetails.services.CropServices;

public class CropControllerTest {
	
	@InjectMocks
    public CropsDetailsController CropController= Mockito.mock(CropsDetailsController.class);

    @Mock
    public CropDetailsRepo CropRepository;
    
    CropDetails F = new CropDetails("1","1","Apple","fruits",100,5,"address1");

    public ResponseEntity<List<CropDetails>> getlist(){
        CropDetails s = new CropDetails("2","1","Apple","fruits",100,5,"address1");
        CropDetails p = new CropDetails("3","2","Grapes","fruits",100,5,"address2");
        // Creating A Crop List
        List<CropDetails> testCrops = new ArrayList<CropDetails>();
        testCrops.add(F);      // adding a Crop
        testCrops.add(s);
        testCrops.add(p);
        return new ResponseEntity<>(testCrops, HttpStatus.OK);    //generating & Returning Response
    }
    
    @Test
    void getCrops() {
        ResponseEntity<List<CropDetails>> Response = getlist() ;
        when(CropController.getAllCropsDetails()).thenReturn((List<CropDetails>) Response);               //  Setting The Response
        // Getting The Response
        List<CropDetails>  result = CropController.getAllCropsDetails();
        assertThat(((ResponseEntity<List<CropDetails>>) result).getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(((HttpEntity<List<CropDetails>>) result).getBody().toString()).isEqualTo((getlist().getBody().toString()));
    }

    @Test
    void getListByFarmerId() {
        ResponseEntity<List<CropDetails>> Response = getlist() ;
        List<CropDetails> f = Response.getBody().stream().filter(
                crop -> crop.getFarmerId().equals("1")).collect(Collectors.toList());
        when(CropController.getByFarmerId("1")).thenReturn((List<CropDetails>) new ResponseEntity<>(f,HttpStatus.OK));               //  Setting The Response
        when(CropController.getByFarmerId("3")).thenReturn((List<CropDetails>) new ResponseEntity<>(HttpStatus.NO_CONTENT));
        // Getting The Response
        List<CropDetails>  result = CropController.getByFarmerId("1");
        List<CropDetails>  result1 = CropController.getByFarmerId("3");
        assertThat(((ResponseEntity<List<CropDetails>>) result).getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(((ResponseEntity<List<CropDetails>>) result1).getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(((HttpEntity<List<CropDetails>>) result).getBody().toString()).isEqualTo((f.toString()));
    }

//    @Test
//    void getListByname() {
//        ResponseEntity<List<CropDetails>> Response = getlist() ;
//        List<CropDetails> f = Response.getBody().stream().filter(
//                crop -> crop.getCropName().equals("Apple")).collect(Collectors.toList());
//        when(CropController.getCropsDetails("Apple")).thenReturn(new ResponseEntity<>(f,HttpStatus.OK));               //  Setting The Response
//        when(CropController.getCropsDetails("Grapes")).thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));
//        // Getting The Response
//        List<CropDetails>  result = CropController.getCropsDetails("Apple");
//        List<CropDetails>  result1 = CropController.getCropsDetails("Grapes");
//        assertThat(((ResponseEntity<List<CropDetails>>) result).getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(((ResponseEntity<List<CropDetails>>) result1).getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
//        assertThat(((HttpEntity<List<CropDetails>>) result).getBody().toString()).isEqualTo((f.toString()));
//    }
    

    @Test
    void findById() {
        ResponseEntity<List<CropDetails>> Response = getlist() ;
        List<CropDetails> f = getlist().getBody();
        CropDetails f1 = f.stream()
                .filter(Crop -> "2".equals(Crop.getId()))
                .findAny().orElse(null);
        assertThat(Response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(f1).isNotSameAs(F);
        assertThat(f1.getId()).isNotNull();
    }

    @Test
    void addCrop() {
        CropDetails s = new CropDetails("4","2","Apple","Fruits",1000,5,"address10");
        when(CropController.addCropDetails(s)).thenReturn((List<CropDetails>) new ResponseEntity<>(s,HttpStatus.OK));
        when(CropController.addCropDetails(F)).thenReturn((List<CropDetails>) new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        List<CropDetails> result1 = CropController.addCropDetails(F);
        List<CropDetails> result = CropController.addCropDetails(s);
        assertThat(((ResponseEntity<List<CropDetails>>) result1).getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(((ResponseEntity<List<CropDetails>>) result).getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(((ResponseEntity<List<CropDetails>>) result).getBody().toString()).isEqualTo((s.toString()));
    }

    @Test
    void updateCrop() {
        CropDetails s = new CropDetails("4","2","Apple","Fruits",100,3,"address20");
        when(CropController.updateCropsDetails("3", s)).thenReturn((List<CropDetails>) new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        when(CropController.updateCropsDetails("4", F)).thenReturn((List<CropDetails>) new ResponseEntity<>(F,HttpStatus.OK));
        List<CropDetails> result1 = CropController.updateCropsDetails("3", F);
        List<CropDetails> result = CropController.updateCropsDetails("4", s);
        assertThat(((ResponseEntity<List<CropDetails>>) result).getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(((ResponseEntity<List<CropDetails>>) result1).getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(((ResponseEntity<List<CropDetails>>) result).getBody()).isEqualTo(F);
    }

//    @Test
//    void deleteCrop() {
//        when(CropController.deleteCropsDetails("1")).thenReturn(new ResponseEntity<>("Deleted SuccessFully",HttpStatus.OK));
//        when(CropController.deleteCropsDetails("2")).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//        ResponseEntity<String> result1 = CropController.deleteCropsDetails("1");
//        ResponseEntity<String> result = CropController.deleteCropsDetails("2");
//        assertThat(result1.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
//    }

}

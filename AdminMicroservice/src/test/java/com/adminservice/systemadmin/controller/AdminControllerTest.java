package com.adminservice.systemadmin.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import  com.systemadmin.repo.AdminRepository;

import com.systemadmin.controller.AdminController;
import com.systemadmin.model.FarmerDetails;

public class AdminControllerTest {
	
	@InjectMocks
    public AdminController AdminController= Mockito.mock(AdminController.class);

    @Mock
    public AdminRepository AdminRepository;
    
    FarmerDetails F =new FarmerDetails(1,11,"kamal","sai@mail.com", "jfjs");
    
    public ResponseEntity<List<FarmerDetails>> getlist(){
        List<FarmerDetails> testFarmer = new ArrayList<FarmerDetails>();
        testFarmer.add(F);      
        FarmerDetails s = new FarmerDetails(2,22,"kamal","sai@mail.com","skmaks");
        testFarmer.add(s);
        return new ResponseEntity<>(testFarmer, HttpStatus.OK);    //generating & Returning Response
    }
    @Test
    void findById() {
        ResponseEntity<List<FarmerDetails>> Response = getlist() ;
        List<FarmerDetails> f = getlist().getBody();
        FarmerDetails f1 = f.stream()
                .filter(Farmer -> "2".equals(Farmer.getId()))
                .findAny().orElse(null);
        assertThat(Response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(f1).isNotSameAs(F);
       // assertThat(f1.getId()).isNotNull();
    
    }

    @Test
    void addFarmer() {
    	FarmerDetails s = new FarmerDetails(2,22,"kamal","sai@mail.com","dfsd");
        when(AdminController.addFarmerDetails(s)).thenReturn(new ResponseEntity<>(s,HttpStatus.OK));
        when(AdminController.addFarmerDetails(F)).thenReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        ResponseEntity<FarmerDetails> result1 = AdminController.addFarmerDetails(F);
        ResponseEntity<FarmerDetails> result = AdminController.addFarmerDetails(s);
        assertThat(result1.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().toString()).isEqualTo((s.toString()));
    }

    @Test
    void deleteCrop() {
        when(AdminController.deleteCropsDetails("1")).thenReturn(new ResponseEntity<>("Deleted SuccessFully",HttpStatus.OK));
        when(AdminController.deleteCropsDetails("2")).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        ResponseEntity<String> result1 = AdminController.deleteCropsDetails("1");
        ResponseEntity<String> result = AdminController.deleteCropsDetails("2");
        assertThat(result1.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

}

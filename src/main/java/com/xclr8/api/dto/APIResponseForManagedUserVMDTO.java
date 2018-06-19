/**
 * APIResponseForManagedUserVM
 *
 * version :1.0
 *
 * Created on 8/2/17.
 *
 * Copyright 2017 XCLR8
 *
 */
package com.xclr8.api.dto;

import com.xclr8.api.web.rest.vm.ManagedUserVM;

import java.util.List;;

public class APIResponseForManagedUserVMDTO extends APIResponseDTO {


    private List<ManagedUserVM> data;

    public List<ManagedUserVM> getData() {
        return data;
    }

    public void setData(List<ManagedUserVM> data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "APIResponseForManagedUserVM{" +
            ", data='" + data + '\'' +
            "}"  + super.toString();
    }
}



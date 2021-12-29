package com.university.email.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class emailDeleteParams {
    private String folderName;
    private int index;
    private String username;
}

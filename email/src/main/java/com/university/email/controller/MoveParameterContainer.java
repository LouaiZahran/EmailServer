package com.university.email.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoveParameterContainer {
    private String oldFolderName;
    private String newFolderName;
    private int index;
    private String username;
}

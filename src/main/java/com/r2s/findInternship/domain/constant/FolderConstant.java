package com.r2s.findInternship.domain.constant;

import java.util.Set;

public class FolderConstant {
    public static final String CV_FOLDER = "CV";
    public static final String IMAGE_FOLDER = "images";
    public static final String EXCEL_FOLDER = "Students";
    public static final Set<String> IMAGE_EXTENSIONS = Set.of("jpg", "jpeg", "png", "gif", "bmp", "tiff");
    public static final Set<String> EXCEL_EXTENSIONS = Set.of("xls", "xlsx");
    public static final Set<String> ALL_FOLDERS = Set.of(CV_FOLDER, IMAGE_FOLDER, EXCEL_FOLDER);
}
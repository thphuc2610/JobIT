package com.r2s.findInternship.domain.common.util;

import com.r2s.findInternship.domain.common.FileUpload;

public interface UpdateFile {
	void update(FileUpload fileUp);

	void uploadCV(FileUpload fileUpload);

	void uploadCVApplication(FileUpload fileUpload);

	void uploadExcel(FileUpload fileUp);

	void deleteFile(String url);
}
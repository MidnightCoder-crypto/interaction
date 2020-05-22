package com.example.interacting;

public class Notes {
    String file_name,file_url;
    Long file_upload_date;

    public Notes() {
    }

    public Notes(String file_name, String file_url, Long file_upload_date) {
        this.file_name = file_name;
        this.file_url = file_url;
        this.file_upload_date = file_upload_date;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public Long getFile_upload_date() {
        return file_upload_date;
    }

    public void setFile_upload_date(Long file_upload_date) {
        this.file_upload_date = file_upload_date;
    }
}




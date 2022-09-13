package com.ironhack.vbnk_dataservice.data.http.response;

import com.ironhack.vbnk_dataservice.utils.VBError;

import java.util.List;

public class DataResponse {
    boolean isOk;
    List<VBError> error;
}

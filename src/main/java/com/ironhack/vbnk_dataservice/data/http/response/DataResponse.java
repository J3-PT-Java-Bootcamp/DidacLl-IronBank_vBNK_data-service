package com.ironhack.vbnk_dataservice.data.http.response;

import com.ironhack.vbnk_dataservice.utils.VBError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataResponse {
    boolean isOk;
    List<VBError> error;
}

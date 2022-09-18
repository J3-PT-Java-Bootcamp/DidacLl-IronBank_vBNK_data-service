package com.ironhack.vbnk_dataservice.data.http.response;

import com.ironhack.vbnk_dataservice.data.http.request.TransferRequest;
import com.ironhack.vbnk_dataservice.utils.VBError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferResponse {

    private TransferRequest request;
    private boolean source, destination, srcAccountAvailable,dstAccountAvailable, enoughFounds;
    private BigDecimal srcBalance,dstBalance;
    private List<VBError> errors;

}

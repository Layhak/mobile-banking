package co.istad.jbsdemo.mobilebanking.utilities;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

@Accessors(chain = true)
@Data
public class BaseResponse<T> {
    private T payload;
    private String message;
    private Object metadata;//? relate to pagination
    private int status;
    public  static  <T> BaseResponse<T>  createSuccess(){
        return  new BaseResponse<T>()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Success");
    }
}

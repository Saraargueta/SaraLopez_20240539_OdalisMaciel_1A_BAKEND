package SystemITR.JosueGuinea1A.Response;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ApiResponse<T> {

    private int status;
    private String message;
    private T data;

    // Respuestas que contienen data
    public ApiResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    // Respuestas que NO contienen data
    public ApiResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}

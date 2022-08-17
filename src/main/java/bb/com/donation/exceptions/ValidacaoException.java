package bb.com.donation.exceptions;


import lombok.Getter;


public class ValidacaoException extends RuntimeException {
    public ValidacaoException(String msg) {
        super(msg);
    }
    public ValidacaoException(ErrorMessages msg) {
        super(msg.getMsg ());
    }

}

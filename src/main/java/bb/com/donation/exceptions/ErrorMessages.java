package bb.com.donation.exceptions;

import lombok.Getter;

@Getter
public enum ErrorMessages {

    DONATION_NOT_FOUND("Donation not found"),
    DONATION_ALREADY_EXISTS("Donation already exists"),
    DONATION_NOT_FOUND_BY_ID("Donation not found by id"),

    PERSON_NOT_FOUND("Person not found"),
    PERSON_NOT_FOUND_BY_ID("Person not found by id"),

    MESSAGE_NOT_FOUND("Message not found"),
    PERSON_NOT_ALLOWED("Person not allowed"),
    MESSAGE_NOT_ALLOWED_SELF_REFERENCE ("Last message can't be the same as message id"),
    MESSAGE_NOT_ALLOWED_PERSONTO_SELF_REFERENCE ("Person to can't be the same as person by");

    private final String msg;

    ErrorMessages(String msg) {
        this.msg = msg;
    }
}

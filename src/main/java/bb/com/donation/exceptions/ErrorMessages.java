package bb.com.donation.exceptions;

import lombok.Getter;

@Getter
public enum ErrorMessages {

    DONATION_NOT_FOUND("Donation not found"),
    DONATION_ALREADY_EXISTS("Donation already exists"),
    DONATION_NOT_FOUND_BY_ID("Donation not found by id"),
    DONATION_NOT_FOUND_BY_ID_AND_USER("Donation not found by id and user"),
    DONATION_NOT_FOUND_BY_ID_AND_USER_AND_STATUS("Donation not found by id and user and status"),
    DONATION_NOT_FOUND_BY_ID_AND_USER_AND_STATUS_AND_TYPE("Donation not found by id and user and status and type"),
    DONATION_NOT_FOUND_BY_ID_AND_USER_AND_STATUS_AND_TYPE_AND_CATEGORY("Donation not found by id and user and status and type and category"),
    DONATION_NOT_FOUND_BY_ID_AND_USER_AND_STATUS_AND_TYPE_AND_CATEGORY_AND_LOCATION("Donation not found by id and user and status and type and category and location"),
    DONATION_NOT_FOUND_BY_ID_AND_USER_AND_STATUS_AND_TYPE_AND_CATEGORY_AND_LOCATION_AND_DATE("Donation not found by id and user and status and type and category and location and date"),
    DONATION_NOT_FOUND_BY_ID_AND_USER_AND_STATUS_AND_TYPE_AND_CATEGORY_AND_LOCATION_AND_DATE_AND_TIME("Donation not found by id and user and status and type and category and location and date and time"),
    DONATION_NOT_FOUND_BY_ID_AND_USER_AND_STATUS_AND_TYPE_AND_CATEGORY_AND_LOCATION_AND_DATE_AND_TIME_AND_DESCRIPTION("Donation not found by id and user and status and type and category and location and date and time and description"),
    DONATION_NOT_FOUND_BY_ID_AND_USER_AND_STATUS_AND_TYPE_AND_CATEGORY_AND_LOCATION_AND_DATE_AND_TIME_AND_DESCRIPTION_AND_IMAGE("Donation not found by id and user and status and type and category and location and date and time and description and image"),

    PERSON_NOT_FOUND("Person not found"),
    PERSON_NOT_FOUND_BY_ID("Person not found by id"),

    ;

    private final String msg;

    ErrorMessages(String msg) {
        this.msg = msg;
    }
}

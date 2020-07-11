package ua.testing.repairagency.util;

import java.util.Locale;

public final class Constants {

    private Constants(){};

    public static final String REQUEST_ATTRIBUTE = "request";
    public static final String USER_ATTRIBUTE = "user";
    public static final String USERS_ATTRIBUTE = "users";
    public static final String REQUESTS_REP_ATTRIBUTE = "requestRep";

    public static final String ADMIN_TABLES_VIEW = "admin/adminTables";
    public static final String ADMIN_ACCEPT_VIEW = "admin/adminAccept";
    public static final String ADMIN_CANCEL_VIEW = "admin/adminCancel";

    public static final String MASTER_VIEW = "master/master";
    public static final String MASTER_ARCHIVE_VIEW = "master/masterArchive";

    public static final String USER_VIEW = "user/user";
    public static final String REQUEST_REGISTRATION_VIEW = "user/requestRegistration";
    public static final String USER_COMMENT_VIEW = "user/userComment";


    public static final String LOGIN_VIEW = "login";
    public static final String REGISTRATION_VIEW = "registration";
    public static final String SUCCESSFUL_REQUEST_CREATION_VIEW = "successfulRequestCreation";


    public static final String ADMIN_PAGE_PATH_REDIRECT = "redirect:/admin";
    public static final String MASTER_PAGE_PATH_REDIRECT = "redirect:/master";
    public static final String USER_PAGE_PATH_REDIRECT = "redirect:/user";
    public static final String LOGIN_PAGE_PATH_REDIRECT = "redirect:/login";
    public static final String REGISTRATION_PAGE_PATH_REDIRECT = "redirect:/registration";


    public static final String USERNAME_VALIDATION_PROPERTY = "{size.userForm.username}";
    public static final String COMMENT_VALIDATION_PROPERTY = "{form.comment}";
    public static final String PASSWORD_VALIDATION_PROPERTY = "{size.userForm.password}";
    public static final String DESCRIPTION_VALIDATION_PROPERTY = "{size.userForm.password}";
    public static final String ADDRESS_VALIDATION_PROPERTY = "{form.validate.address}";
    public static final String PHONE_NUMBER_VALIDATION_PROPERTY = "{form.validate.phone.number}";
    public static final String EMPTY_PRICE_VALIDATION_PROPERTY = "{form.validation.price.null.or.char}";
    public static final String POSITIVE_PRICE_VALIDATION_PROPERTY = "{form.validation.price.null.or.char}";
    public static final String MAX_PRICE_VALUE_VALIDATION_PROPERTY = "{form.validation.price.max.value}";
    public static final String CANCELLATION_REASON_VALIDATION_PROPERTY = "{form.validation.cancellation.reason}";


    public static final String ALREADY_REGISTERED_USER_VALIDATION_PROPERTY = "duplicate.userForm.username";
    public static final String MATCHING_PASSWORD_VALIDATION_PROPERTY = "diff.userForm.passwordConfirm";


    public static final String PASSWORD_VALIDATION_REGEX = "^(?=.*[0-9]).{8,15}$";
    public static final String USERNAME_VALIDATION_REGEX = "^[a-zA-Z0-9._-]{6,32}$";
    public static final String COMMENT_VALIDATION_REGEX = "^.([^\"]){1,150}$";
    public static final String DESCRIPTION_VALIDATION_REGEX = "^.([^\"]){1,150}$";
    public static final String ADDRESS_VALIDATION_REGEX = "^[А-Яа-яЁЇїІіЄєҐґ\\w\\s',-\\\\/.()#]{1,75}$";
    public static final String PHONE_NUMBER_VALIDATION_REGEX = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]{1,16}$";
    public static final String CANCELLATION_REASON_VALIDATION_REGEX = "^.([^\"]){1,100}$";


    public static final String PRICE_MAX_VALUE = "9999";
    public static final long REPAIR_PRICE_DEFAULT_VALUE = 0L;
    public static final String CANCELLATION_REASON_DEFAULT_VALUE = "not canceled";
    public static final String USER_COMMENT_DEFAULT_VALUE = "no comment";

    public static final String USER_ROLE = "ROLE_USER";
    public static final String ADMIN_ROLE = "ROLE_ADMIN";
    public static final String MASTER_ROLE = "ROLE_MASTER";

    public static final int DEFAULT_PAGE_SIZE = 3;

    public static final String USERNAME_FIELD_NAME = "username";
    public static final String MATCHING_PASSWORD_FIELD_NAME = "matchingPassword";

    public final static long CURRENCY_CONSTANT = 30;

    public static final Locale UA_LOCALE = new Locale("ua");
    public static final Locale EN_LOCALE = Locale.US;
    public static final String DEFAULT_CHAR_ENCODING = "UTF-8";
    public static final String DEFAULT_LOCALE_INTERCEPTOR_PARAM = "lang";

    public static final String CYRILLIC_TO_LATIN = "Ukrainian-Latin/BGN";

    public static final String USERS_BY_USERNAME_QUERY = "select username, password, enabled " +
            " from users where username = ?";
    public static final String AUTHORITIES_BY_USERNAME_QUERY = "select username, authority" +
            " from authorities where username = ?";



}

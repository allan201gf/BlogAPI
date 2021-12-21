package br.com.allangf.BlogAPI.rest;

public class Errors {

    public static final String INVALID_DATE = "Invalid date, correct is DD/MM/AAAA";
    public static final String EMAIL_OR_LOGIN_ALREADY_REGISTERED = "The email or login is already registered";
    public static final String EMAIL_IS_REQUIRED = "Email is required";
    public static final String NAME_IS_REQUIRED = "Name is required";
    public static final String BIRTH_DATE_IS_REQUIRED = "BirthDate is required";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String EMAIL_NOT_FOUND = "Email not found";
    public static final String TAG_ALREADY_REGISTERED = "The tag is already exist";
    public static final String NO_POSTS_FOUND = "No posts found";
    public static final String TAG_IS_REQUIRED = "Tag is required";
    public static final String POST_BODY_IS_REQUIRED = "PostBody is required";
    public static final String TITLE_IS_REQUIRED = "Title is required";
    public static final String TAG_NOT_FOUND = "Tag not found";
    public static final String INVALID_POST_ID = "Invalid post id";
    public static final String POST_ID_IN_USE = "Post id in use, delete the post before";
    public static final String LOGIN_IS_REQUIRED = "Login is required";
    public static final String PASSWORD_IS_REQUIRED = "Password is required";
    public static final String PASSWORD_IS_WRONG = "Password is wrong";
    public static final String POST_ANOTHER_USER = "Post was written by another user";
    public static final String EMAIL_ERROR = "Failed to send some email, but post was saved";
    public static final String EMAIL_LOGIN_ERROR = "Failed to connect SMTP";
    public static final String CURRENT_PASSWORD_INVALID = "Current password invalid";
    public static final String NEW_PASSWORD_DONT_CHECK = "New password and confirm password don't check";
}

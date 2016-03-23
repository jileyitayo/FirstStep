package com.example.jil.Chat;


public class Util {

    public static final String EXTRA_MESSAGE = "message";
    public static final String PROPERTY_REG_ID = "registration_id";
    public static final String PROPERTY_APP_VERSION = "appVersion";
    public static final String EMAIL = "email";
    public static final String ROLE = "role";
    public static final String USER_NAME = "user_name";

    public final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    //public final static String SENDER_ID = "967868283752";
    public final static String SENDER_ID = "452894393183";
    //public static String base_url = "http://192.168.1.30/gcm_demo/";
    public static String base_url = "http://172.16.67.190/gcmChat/";
    public final static String  register_url=base_url+"register.php";
    public final static String  send_chat_url=base_url+"sendChatmessage.php";
    public final static String  fetchUsers = base_url+"fetchUsersRoles.php";



}

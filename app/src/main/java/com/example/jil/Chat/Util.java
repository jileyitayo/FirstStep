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
<<<<<<< HEAD
    public final static String URL = "http://172.16.66.60/";
=======
    public final static String URL = "http://172.16.156.10/";
>>>>>>> newFeatures
    //public final static String URL = "http://localhost/";

    //public static String base_url = "http://192.168.1.30/gcm_demo/";
    public static String base_url = URL + "gcmChat/";
    public final static String  register_url=base_url+"register.php";
    public final static String  send_chat_url=base_url+"sendChatmessage.php";
    public final static String  fetchUsers = base_url+"fetchUsers.php";
    public final static String  backupUsers = base_url+"backupData.php";
    public final static String  backupChild = base_url+"backUpchild.php";
    public final static String getAllUsers = base_url+"getAllUsers.php";
}

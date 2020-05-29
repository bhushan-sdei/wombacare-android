package com.app.womba.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface InterConsts {

    @NotNull
    String EXTRA = "extra";

    @NotNull
    String AUTH_TOKEN = "authorization";

    @NotNull
    String USER_TYPE  = "Patient";
    @NotNull
    String DEVICE_TYPE  = "Android";
    @NotNull
    String HEALTH_REPORT = "HEALTH_REPORT";

    @NotNull
    String USER_DATA = "USER_DATA";
    @NotNull
    String MATCH_SETTINGS_DATA = "MATCH_SETTINGS_DATA";
    @NotNull
    String DEALS_DISTANCE= "DEALS_DISTANCE";
    @NotNull
    String LAT = "LAT";
    @NotNull
    String LONG = "LONG";

    @NotNull
    String PHONE_NUMBER = "PHONE_NUMBER";

    @NotNull
    String NAME = "NAME";
    @NotNull
    String EMAIL = "EMAIL";
    @NotNull
    String MALE = "Male";
    @NotNull
    String FEMALE = "Female";
    @NotNull
    String BINARY = "Non Binary";

    @NotNull
    int DOG = 0x1F436;
    @NotNull
    int CAT = 0x1F63A;
    @NotNull
    int BIRD = 0x1F425;
    @NotNull
    int WOLF = 0x1F43A;
    @NotNull
    int HORSE = 0x1F434;
    @NotNull
    int REPTILE = 0x1F40D;
    @NotNull
    int FISH = 0x1F420;
    @NotNull
    int FROG = 0x1F438;
    @NotNull
    int RABBIT = 0x1F430;


    int FRAG_NULL = 10;
    int FRAG_HOME = 0;
    int FRAG_APPOINTMENTS = 1;
    int FRAG_PAYMENTS = 2;
    int FRAG_PROFILE = 3;


    @Nullable String SOCKET_CONNECTION = "socketConnection";
    @Nullable String SEND_MESSAGE = "sendMessage";
    @Nullable String GET_MESSAGES = "getMessage";
    @NotNull String CONVERSATION_ID = "conversation_id";
    @Nullable String IMAGE = "image";
    @Nullable String USER_ID = "user_id";
    @Nullable String USER_NAME = "user_name";
    @Nullable String MATCHED_ON = "matched_on";
    @NotNull String ANDROID = "1";
    @NotNull String EMPTY = "--";
    @Nullable String PETS="pets";
    @NotNull String IMAGE_PETS="images_pets";
    @Nullable String UNBLOCK="unblock";
    @Nullable String DOCTOR_INFO="doc_info";
    @Nullable String FROM="from";
    @Nullable String PRESCRIPTION ="pres_info";
    @Nullable String PAST="PAST";
}
